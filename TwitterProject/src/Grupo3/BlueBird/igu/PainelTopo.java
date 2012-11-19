package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Grupo3.BlueBird.logica.MeuTwitter;

import twitter4j.Twitter;

public class PainelTopo extends JPanel implements ActionListener{
	
	private JLabel imagem;
	private CampoTextoPersonalizado busca;
	private BotaoPersonalizado botaoBusca;
	private JPanel painelBusca, painelIcone;
	Twitter twitter;
	MeuTwitter mt;
	
	
	public PainelTopo(Twitter twitter, MeuTwitter mt){
		this.twitter = twitter;
		this.mt = mt;
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
		painelBusca.setBorder(new EmptyBorder(5, 0, 0, 0));
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
		defineJanelaResultadoBusca();
	}
	
	private void defineJanelaResultadoBusca(){
		JDialog janela;
		janela = new JDialog(new JFrame(), "Resultado da Busca", false);
		janela.setMinimumSize(new Dimension(500, 580));
		janela.setMaximumSize(new Dimension(500, 580));
		janela.setLocationRelativeTo(null);
		janela.setContentPane(new JScrollPane(new PainelResultadoBusca(mt, busca.getText())));
		janela.setVisible(true);
	}

}
