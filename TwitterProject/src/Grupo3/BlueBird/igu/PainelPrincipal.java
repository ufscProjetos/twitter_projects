package Grupo3.BlueBird.igu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JTextArea;
import Grupo3.BlueBird.logica.Timeline;
import Grupo3.BlueBird.logica.UpdateStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PainelPrincipal extends JPanel implements ActionListener{
	
	Timeline timeline;
	UpdateStatus atualizaStatus;
	JLabel nome;
	JLabel info;	
	JButton tweetar;
	JTextArea campoTweet;
	List<Status> linhaTempo;
	
	public PainelPrincipal(Twitter twitter){
		timeline = new Timeline(twitter);
		atualizaStatus = new UpdateStatus(twitter);
		defineComponentes();
		organizaComponentes();
	}
	
	private void defineComponentes() {
		campoTweet = new JTextArea(5, 25);
		campoTweet.setLineWrap(true);
		linhaTempo = timeline.getTimeline();
		tweetar = new JButton("Tweetar");
		tweetar.addActionListener(this);
		nome = new JLabel("Jean Fernando Hillesheim");
		info = new JLabel("Teste de visualisação do layout da página. aqui serão exibidas as atualizações dos usuário");
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
			ParallelGroup pg2 = layout.createParallelGroup(Alignment.LEADING);
			pg.addComponent(campoTweet);
			pg.addComponent(tweetar);
			pg2.addComponent(nome);
			pg2.addComponent(info);
			sg.addGroup(pg);
			sg.addGroup(pg2);
			layout.setHorizontalGroup(sg);
		}
		
		// Dimensão Vertical
				
		{
			ParallelGroup pg = layout.createParallelGroup(Alignment.BASELINE);
			SequentialGroup sg = layout.createSequentialGroup();
			sg.addComponent(campoTweet);
			sg.addComponent(tweetar);
			SequentialGroup sg2 = layout.createSequentialGroup();
			sg2.addComponent(nome);
			sg2.addComponent(info);
			pg.addGroup(sg);
			pg.addGroup(sg2);
			layout.setVerticalGroup(pg);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Tweetar")
			try {
				atualizaStatus.atualizaStatus(campoTweet.getText());
				JOptionPane.showMessageDialog(this, "Sucesso! ", "Funcionando", JOptionPane.INFORMATION_MESSAGE);
				campoTweet.setText("");
			} catch (TwitterException te) {
				if (te.getStatusCode() == 403){
					JOptionPane.showMessageDialog(this, "Status não atualizado.\n Tente novamente mais tarde!. ", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Ação não realizada!\n Verifique sua conexão com a internet.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}		
	}
}
