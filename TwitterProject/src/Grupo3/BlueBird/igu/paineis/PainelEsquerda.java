package Grupo3.BlueBird.igu.paineis;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import twitter4j.TwitterException;

import Grupo3.BlueBird.igu.LabelPersonalizado;
import Grupo3.BlueBird.logica.MeuTwitter;

public class PainelEsquerda extends JPanel {
	
	private static MeuTwitter mt;
	private JLabel seguidores;
	private static JLabel amigos;
	
	public PainelEsquerda(MeuTwitter meuTwitter){
		PainelEsquerda.mt = meuTwitter;
		defineComponentes();
		organizaComponentes();
	}

	private void defineComponentes() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(60, 600));
		this.setMaximumSize(new Dimension(60, 600));
		this.setBackground(new Color(0, 195, 248));
		this.setOpaque(true);
		defineOpcaoSeguidores();
		defineOpcaoAmigos();
	}
	
	private void defineOpcaoSeguidores(){
		int seguidores;
		try {
			seguidores = mt.getNumeroSeguidores().getIDs().length;			
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Não foi possível obter o\n número de seguidores.",
					"Número de seguidores indisponível", JOptionPane.INFORMATION_MESSAGE);
			seguidores = 0;
		}
		this.seguidores = new JLabel("<html>" + String.valueOf(seguidores) + 
				"<p style=\"font-size:6px;\">Followers</p></html>", SwingConstants.CENTER);
		this.seguidores.setForeground(Color.WHITE);
		this.seguidores.setMinimumSize(new Dimension(60, 60));
		this.seguidores.setMaximumSize(new Dimension(60, 60));
	}
	
	private static void defineOpcaoAmigos(){
		int friends;
		try {
			friends = mt.getNumeroAmigos();
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível obter o\n número de amigos.",
					"Número de amigos indisponível", JOptionPane.INFORMATION_MESSAGE);
			friends = 0;
		}
		PainelEsquerda.amigos = new LabelPersonalizado("<html>" + String.valueOf(friends) + 
				"<p style=\"font-size:6px;\">Following</p></html>", SwingConstants.CENTER, mt);
		PainelEsquerda.amigos.setForeground(Color.WHITE);
		PainelEsquerda.amigos.setMinimumSize(new Dimension(60, 60));
		PainelEsquerda.amigos.setMaximumSize(new Dimension(60, 60));
	}

	private void organizaComponentes() {
		this.add(seguidores);
		this.add(amigos);	
	}
	
	public static void updateAmigos() throws TwitterException {
		String text = "<html>" + String.valueOf(mt.getNumeroAmigos()) + 
				"<p style=\"font-size:6px;\">Following</p></html>";
		PainelEsquerda.amigos.setText(text);
	}

}
