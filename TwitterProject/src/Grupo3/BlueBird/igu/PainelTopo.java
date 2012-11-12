package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import twitter4j.Twitter;

public class PainelTopo extends JPanel implements ActionListener{
	
	PainelPrincipal pp;
	private JLabel imagem;
	private CampoTextoPersonalizado busca;
	private BotaoPersonalizado botaoBusca;
	private JPanel painelBusca, painelIcone;
	Twitter twitter;
	
	
	public PainelTopo(Twitter twitter, PainelPrincipal pp){
		this.pp = pp;
		this.twitter = twitter;
		defineComponentes();
		organizaComponentes();
	}

	private void defineComponentes(){
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.setMinimumSize(new Dimension(500, 40));
		this.setMaximumSize(new Dimension(500, 40));
		busca = new CampoTextoPersonalizado(100, 20);
		botaoBusca = new BotaoPersonalizado(null, new ImageIcon(getClass().getResource("/imagens/icone_lupa.png")), 20, 20);
		botaoBusca.addActionListener(this);
		imagem = new JLabel(new ImageIcon(getClass().getResource("/imagens/twitter-bird2.png")));
		painelIcone = new JPanel();
		painelIcone.setLayout(new FlowLayout(FlowLayout.LEFT));
		painelIcone.setPreferredSize(new Dimension(370, 40));
		painelIcone.setBackground(new Color(49, 49, 49));
		painelBusca = new JPanel();
		painelBusca.setLayout(new FlowLayout(FlowLayout.RIGHT));
		painelBusca.setPreferredSize(new Dimension(130, 40));
		painelBusca.setBackground(new Color(49, 49, 49));
	}
	
	private void organizaComponentes(){
		this.add(painelIcone);
		painelIcone.add(imagem);
		this.add(painelBusca);
		painelBusca.add(busca);
		painelBusca.add(botaoBusca);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		//janela.mostraPainelResultadoBusca();
		//defineDialogBusca();
		//pp.mostraPainelResultadoBusca(busca.getText());
	}
	
	private void defineDialogBusca(){
//		JDialog dialogo;
//		dialogo = new JDialog(new JFrame(), "Teste", false);
//		dialogo.setMinimumSize(new Dimension(250, 450));
//		dialogo.setMaximumSize(new Dimension(250, 450));
//		dialogo.setLocationRelativeTo(null);
//		dialogo.setContentPane(new JScrollPane(new PainelResultadoBusca(twitter, busca.getText())));
//		dialogo.setVisible(true);
	}

}
