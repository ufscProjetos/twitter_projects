package Grupo3.BlueBird.igu.paineis;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import twitter4j.Tweet;
import twitter4j.TwitterException;
import Grupo3.BlueBird.igu.BotaoPersonalizado;
import Grupo3.BlueBird.logica.MeuTwitter;

public class PainelResultadoBusca extends JPanel {
	
	MeuTwitter mt;
	String texto;
	JLabel imagem, usuario;
	List<Tweet> tweet;
	
	public PainelResultadoBusca(MeuTwitter mt, String texto) {
		this.mt = mt;
		this.texto = texto;
		defineComponentes();
	}
	
	private void defineComponentes() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		defineExibicaoResultado();
	}
	
	private void defineExibicaoResultado(){
		try {			
			mt.executaPesquisaTexto(texto);
			tweet = mt.getResultPesquisaTexto();
			
			for (final Tweet tweet_retornado : tweet) {
				JPanel painelIndividual = definePainelIndividual();
				JLabel imagem;
				try {
					imagem = new JLabel(new ImageIcon(new URL(tweet_retornado.getProfileImageUrl())));
				} catch (MalformedURLException e) {
					JOptionPane.showMessageDialog(this, "Ocorreu um problema  no carregamento\n das imagens de perfil.\n " +
							"Uma imagem padrão será exibida.", "Falha no carregamento das imagens", JOptionPane.INFORMATION_MESSAGE);
					imagem = new JLabel(new ImageIcon(getClass().getResource("/imagens/img_padrao.png")));
				}
				painelIndividual.add(imagem);
				JPanel painelInfo = new JPanel();
				painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
				painelInfo.setBackground(Color.WHITE);
				painelInfo.add(new JLabel("<html><p style=\"font-size:8px;\">@" + tweet_retornado.getFromUserName() +
								"</p><p style=\"width:300px; font-size:8px;\">"
								+ tweet_retornado.getText() + "<span style=\"color:#008B00; font-size:7px;\"> - " +
								getTime(tweet_retornado)+ "</span></p></html>"));
				JButton btSeguir = new BotaoPersonalizado("Seguir", 14, 13);				
				btSeguir.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							mt.seguir(tweet_retornado.getFromUserId());
							JOptionPane.showMessageDialog(null, "Você está seguindo " + tweet_retornado.getFromUserName() +
									" !" , "Sucesso", JOptionPane.INFORMATION_MESSAGE);
							PainelEsquerda.updateAmigos();
						} catch (TwitterException e1) {
							JOptionPane.showMessageDialog(null, "Não foi possível seguir " + tweet_retornado.getFromUserName() +
									" !", "Erro", JOptionPane.ERROR_MESSAGE);			
						}							
					}
				});
				painelInfo.add(btSeguir);
				painelIndividual.add(painelInfo);
				this.add(painelIndividual);
			}
			
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um problema na sua busca.\n" +
					" Tente novamente mais tarde!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private JPanel definePainelIndividual() {
		JPanel painelIndividual = new JPanel();
		painelIndividual.setLayout(new FlowLayout(FlowLayout.LEFT));
		painelIndividual.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 195, 248)));
		painelIndividual.setBackground(Color.WHITE);
		return painelIndividual;
	}
	
	private String getTime(Tweet tweet){
		return (new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy")).format(tweet.getCreatedAt());  
	}
	
}
