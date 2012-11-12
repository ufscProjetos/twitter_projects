package Grupo3.BlueBird.igu;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JTextArea;

import Grupo3.BlueBird.logica.MeuTwitter;
import Grupo3.BlueBird.logica.Timeline;
import Grupo3.BlueBird.logica.UpdateStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PainelPrincipal extends JPanel implements ActionListener{
	
	Timeline timeline;
	TimelineView timelineview;
	Twitter twitter;
	UpdateStatus atualizaStatus;
	Container container, contAreaTexto, contAreaBotao;
	JLabel nome;
	JLabel info;	
	JButton tweetar;
	JTextArea campoTweet;
	PainelTopo painelTopo;
	PainelEsquerda painelEsquerda;
	MeuTwitter meuTwitter;
	
	public PainelPrincipal(Twitter twitter, Janela janela) {
		this.twitter = twitter;
		timeline = new Timeline(twitter);
		timeline.setCurrentTimelineView(new TimelineView(8,30));
		try {
			timeline.refreshHomeTimeline();
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro durante a requisição da 'timeline'!\n" +
					" Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		atualizaStatus = new UpdateStatus(twitter);
		defineComponentes();
		organizaComponentes();
	}
	
	private void defineComponentes() {
		campoTweet = new JTextArea(5, 25);
		campoTweet.setLineWrap(true);
		campoTweet.addKeyListener(new Validador());
		contAreaTexto = new JPanel();
		contAreaTexto.setPreferredSize(new Dimension(50, 0));
		tweetar = new JButton("Tweetar");
		tweetar.setEnabled(false);
		tweetar.addActionListener(this);
		container = timeline.getCurrentTimelineView().getContainer();
		painelTopo = new PainelTopo(twitter, this);
		painelEsquerda = new PainelEsquerda();
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
			ParallelGroup pg2 = layout.createParallelGroup(Alignment.LEADING);	
			pg2.addComponent(painelEsquerda);
			//pg2.addComponent(tweetar);
			//pg2.addComponent(contAreaTexto);
			sg.addGroup(pg2);
			sg.addComponent(container);
			pg.addGroup(sg);
			layout.setHorizontalGroup(pg);
		}
		
		// Dimensão Vertical
				
		{
			SequentialGroup sg = layout.createSequentialGroup(); 
			sg.addComponent(painelTopo);
			ParallelGroup pg = layout.createParallelGroup(Alignment.TRAILING);
			SequentialGroup sg2 = layout.createSequentialGroup();
			sg2.addComponent(painelEsquerda);
			//sg2.addComponent(tweetar);
			//sg2.addComponent(contAreaTexto);
			pg.addGroup(sg2);
			pg.addComponent(container);
			sg.addGroup(pg);
			layout.setVerticalGroup(sg);
		}
		
	}
	
//	public void mostraPainelResultadoBusca(String texto){
//		container = new PainelResultadoBusca(twitter, texto);
//		organizaComponentes();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Tweetar")
			try {
				atualizaStatus.atualizaStatus(campoTweet.getText());
				JOptionPane.showMessageDialog(this, "Status atualizado! ", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				campoTweet.setText("");
				tweetar.setEnabled(false);
			} catch (TwitterException te) {
				if (te.getStatusCode() == 403){
					JOptionPane.showMessageDialog(this, "Status não atualizado.\n Tente novamente mais tarde!. ", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Ação não realizada!\n Verifique sua conexão com a internet.", "Erro", JOptionPane.ERROR_MESSAGE);
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
