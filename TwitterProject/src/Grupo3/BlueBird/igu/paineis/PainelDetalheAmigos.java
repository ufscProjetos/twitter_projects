package Grupo3.BlueBird.igu.paineis;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import twitter4j.TwitterException;
import twitter4j.User;
import Grupo3.BlueBird.igu.BotaoPersonalizado;
import Grupo3.BlueBird.logica.MeuTwitter;

public class PainelDetalheAmigos extends JPanel {
	
	MeuTwitter mt;
	List<User> user;

	public PainelDetalheAmigos(MeuTwitter mt) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.mt = mt;	
		defineComponentes();
	}
	
	private void defineComponentes() {
		try {
			for (final User user : mt.mostraUsuario()) {
				JPanel painelIndividual = definePainelIndividual();
				JLabel imagem;
				imagem = new JLabel(new ImageIcon(user.getProfileImageURL()));
				painelIndividual.add(imagem);
				JPanel painelInfo = new JPanel();
				painelInfo.setPreferredSize(new Dimension(200,40));
				painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
				painelInfo.setBackground(Color.WHITE);
				JLabel nome = new JLabel(user.getScreenName());
				nome.setBorder(new EmptyBorder(0, 0, 10, 0));
				painelInfo.add(nome);
				JButton btExcluir = new BotaoPersonalizado("Escluir", 14, 13);
				btExcluir.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							mt.excluiAmigo(user.getId());
							JOptionPane.showMessageDialog(null, "Você excluiu " + user.getScreenName() + "!" 
									, "Amigo excluído", JOptionPane.INFORMATION_MESSAGE);
							PainelEsquerda.updateAmigos();
						} catch (TwitterException te) {
							JOptionPane.showMessageDialog(null, "Não foi possível excluir " + user.getScreenName() + "!",
									"Erro", JOptionPane.ERROR_MESSAGE);				
						}							
					}
				});
				painelInfo.add(btExcluir);
				painelIndividual.add(painelInfo);
				this.add(painelIndividual);
			}
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro. Tente novamente mais tarde!",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	private JPanel definePainelIndividual() {
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout(FlowLayout.LEFT));
		painel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 195, 248)));
		painel.setBackground(Color.WHITE);
		return painel;
	}

}
