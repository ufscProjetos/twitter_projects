package Grupo3.BlueBird.igu;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JTextArea;
import Grupo3.BlueBird.igu.menu.Opcao;
import Grupo3.BlueBird.logica.Timeline;
import Grupo3.BlueBird.logica.UpdateStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PainelPrincipal extends JPanel implements ActionListener{
	
	Timeline timeline;
	TimelineView timelineview;
	UpdateStatus atualizaStatus;
	Container container, contAreaTexto, contAreaBotao;
	JLabel nome;
	JLabel info;	
	JButton tweetar;
	JTextArea campoTweet;
	
	public PainelPrincipal(Twitter twitter) {
		timeline = new Timeline(twitter);
		timeline.setCurrentTimelineView(new TimelineView(30,45));
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
		contAreaTexto.setPreferredSize(new Dimension(300, 300));
		tweetar = new JButton("Tweetar");
		tweetar.setEnabled(false);
		tweetar.addActionListener(this);
		container = timeline.getCurrentTimelineView().getContainer();
	}

	private void organizaComponentes() {		
		GroupLayout layout = new GroupLayout(this); 
		layout.setAutoCreateGaps(true); 
		layout.setAutoCreateContainerGaps(true);
		setLayout(layout);
		
		// Dimensão Horizontal
		
		{			
			SequentialGroup sg = layout.createSequentialGroup(); 	
			ParallelGroup pg = layout.createParallelGroup(Alignment.LEADING);	
			pg.addComponent(campoTweet);
			pg.addComponent(tweetar);
			pg.addComponent(contAreaTexto);
			sg.addGroup(pg);
			sg.addComponent(container);
			layout.setHorizontalGroup(sg);
		}
		
		// Dimensão Vertical
				
		{
			//SequentialGroup sg = layout.createSequentialGroup();
			ParallelGroup pg = layout.createParallelGroup(Alignment.TRAILING);
			SequentialGroup sg2 = layout.createSequentialGroup();
			sg2.addComponent(campoTweet);
			sg2.addComponent(tweetar);
			sg2.addComponent(contAreaTexto);
			pg.addGroup(sg2);
			pg.addComponent(container);
			//sg.addGroup(pg);
			layout.setVerticalGroup(pg);
		}
		
	}

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

	/**
	 * Método que trata os eventos do menu, vindos da classe Janela. 
	 */
	
	public void trataOpcoesMenu(ActionEvent e) {
		Opcao opcao = Opcao.valueOf(e.getActionCommand());		
		if (opcao == Opcao.SAIR){
			System.exit(0);
		}else
		if (opcao == Opcao.ATUALIZATIMELINE){
			try {
				timeline.refreshHomeTimeline();
			} catch (TwitterException te) {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro durante a requisição da 'timeline'!\n" +
						" Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}else
		if (opcao == Opcao.AJATUALIZATIMTLINE){
			JOptionPane.showMessageDialog(this, "Para atualizar a visualização da sua Timeline,\n no menu vá em 'Opções' e escolha a\n opção " +
					"'Atualiza Timeline'", "Atualizando Timeline", JOptionPane.INFORMATION_MESSAGE);
		}else
		if (opcao == Opcao.TIMELINE){
				JOptionPane.showMessageDialog(this, "Fica localizado no painel à direita e\n permite a visualização da sua 'linha do tempo'", "Timeline", JOptionPane.INFORMATION_MESSAGE);
		}else 
		if (opcao == Opcao.TWEETAR){
			JOptionPane.showMessageDialog(this, "No campo localizado à esquerda, é possível digitar\n o seu novo status, com limite de 140 " +
					"caracteres.\n Digite o que quiser e clique em 'Tweetar'",
					"Tweetar", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "Blue Bird\n Software desenvolvido como trabalho" +
					" final da disciplina INE 5605\n Versão 1.0 - 25/10/2012", "Sobre", JOptionPane.INFORMATION_MESSAGE);
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
