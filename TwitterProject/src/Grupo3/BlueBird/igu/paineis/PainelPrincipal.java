package Grupo3.BlueBird.igu.paineis;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Grupo3.BlueBird.igu.AreaTextoPersonalizada;
import Grupo3.BlueBird.igu.BotaoPersonalizado;
import Grupo3.BlueBird.igu.Janela;
import Grupo3.BlueBird.igu.timeline.TimelineView;
import Grupo3.BlueBird.logica.MeuTwitter;
import Grupo3.BlueBird.logica.timeline.Timeline;
import Grupo3.BlueBird.logica.UpdateStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PainelPrincipal extends JPanel implements ActionListener{
	
	TimelineView timelineview;
	Twitter twitter;
	UpdateStatus atualizaStatus;
	Container container;
	JButton tweetar;
	JTextArea campoTweet;
	PainelTopo painelTopo;
	PainelEsquerda painelEsquerda;
	JPanel painelBotao;
	MeuTwitter mt;
	Icon img;
	
	public PainelPrincipal(Twitter twitter, Janela janela, MeuTwitter meuTwitter) {
		this.mt = meuTwitter;
		this.twitter = twitter;
		try {
			timelineview = new TimelineView(new Timeline(twitter));
		} catch (TwitterException e1) {
			JOptionPane.showMessageDialog(this, "Ocorreu na criação da 'timeline'!\n" +
					" Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		try {
			executaRefreshTimeline();
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro durante a requisição da 'timeline'!\n" +
					" Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		atualizaStatus = new UpdateStatus(twitter);
		defineComponentes();
		organizaComponentes();
	}


	public void executaRefreshTimeline() throws TwitterException {
		timelineview.updateTimeline();		
	}
	
	private void defineComponentes() {
		campoTweet = new AreaTextoPersonalizada(470, 60);
		campoTweet.setBorder(new EmptyBorder(5, 5, 5, 5));
		campoTweet.setLineWrap(true);
		campoTweet.addKeyListener(new Validador());
		img = new ImageIcon(getClass().getResource("/imagens/tweet.png"));
		tweetar = new BotaoPersonalizado(null, img, 60, 22);
		tweetar.setEnabled(false);
		tweetar.addActionListener(this);	
		painelTopo = new PainelTopo(twitter, mt);
		painelEsquerda = new PainelEsquerda(mt);
		painelBotao = new JPanel();
		painelBotao.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		painelBotao.setMinimumSize(new Dimension(440, 32));
		painelBotao.setMaximumSize(new Dimension(440, 32));
		painelBotao.setBorder(new EmptyBorder(5, 0, 0 ,10));
		painelBotao.add(tweetar);
		container = timelineview.getContainer();	
	}
	
	private void organizaComponentes() {		
		GroupLayout layout = new GroupLayout(this); 
		layout.setAutoCreateGaps(false); 
		layout.setAutoCreateContainerGaps(false);
		setLayout(layout);
		
		// Dimensão Horizontal
		
		{
			ParallelGroup pg = layout.createParallelGroup(Alignment.LEADING);
			pg.addComponent(painelTopo);	
			SequentialGroup sg = layout.createSequentialGroup(); 	
			sg.addComponent(painelEsquerda);
			ParallelGroup pg2 = layout.createParallelGroup(Alignment.LEADING);
			pg2.addComponent(campoTweet);
			pg2.addComponent(painelBotao);
			pg2.addComponent(container);
			sg.addGroup(pg2);
			pg.addGroup(sg);
			layout.setHorizontalGroup(pg);
		}
		
		// Dimensão Vertical
				
		{
			SequentialGroup sg = layout.createSequentialGroup(); 
			sg.addComponent(painelTopo);
			ParallelGroup pg = layout.createParallelGroup(Alignment.TRAILING);
			SequentialGroup sg2 = layout.createSequentialGroup();
			pg.addComponent(painelEsquerda);
			sg2.addComponent(campoTweet);
			sg2.addComponent(painelBotao);
			pg.addGroup(sg2);
			sg2.addComponent(container);
			sg.addGroup(pg);
			layout.setVerticalGroup(sg);
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			atualizaStatus.atualizaStatus(campoTweet.getText());
			campoTweet.setText("");
			tweetar.setEnabled(false);
			executaRefreshTimeline();
		} catch (TwitterException te) {
			if (te.getStatusCode() == 403){
				JOptionPane.showMessageDialog(this, "Status não atualizado.\n Tente novamente mais tarde!. ", 
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Ação não realizada!\n Verifique sua conexão com a internet.", 
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class Validador extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			if (campoTweet.getText().length() > 0 && campoTweet.getText().length() <= 140)
				tweetar.setEnabled(true);
			else
				tweetar.setEnabled(false);
		}
	}
}
