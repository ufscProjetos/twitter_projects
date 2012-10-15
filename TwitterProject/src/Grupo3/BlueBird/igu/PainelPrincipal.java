package Grupo3.BlueBird.igu;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PainelPrincipal extends JPanel {
	
	Twitter twitter;
	
	public PainelPrincipal(Twitter twitter){
		this.twitter = twitter;
		atualizaStatus();
		JOptionPane.showMessageDialog(this, "Está Funcionando!", "PQP", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void atualizaStatus(){
		try {
			twitter.updateStatus("Novo teste!");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
