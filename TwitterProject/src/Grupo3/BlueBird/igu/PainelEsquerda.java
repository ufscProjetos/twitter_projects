package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import twitter4j.TwitterException;

import Grupo3.BlueBird.logica.MeuTwitter;

public class PainelEsquerda extends JPanel {
	
	private MeuTwitter mt;
	private JLabel seguidores, amigos;
	
	public PainelEsquerda(MeuTwitter meuTwitter){
		this.mt = meuTwitter;
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
			seguidores = mt.getNumeroSeguidores();			
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
	
	private void defineOpcaoAmigos(){
		int amigos;
		try {
			amigos = mt.getNumeroAmigos();
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Não foi possível obter o\n número de amigos.",
					"Número de amigos indisponível", JOptionPane.INFORMATION_MESSAGE);
			amigos = 0;
		}
		this.amigos = new JLabel("<html>" + String.valueOf(amigos) + 
				"<p style=\"font-size:6px;\">Following</p></html>", SwingConstants.CENTER);
		this.amigos.setForeground(Color.WHITE);
		this.amigos.setMinimumSize(new Dimension(60, 60));
		this.amigos.setMaximumSize(new Dimension(60, 60));
		
	}

	private void organizaComponentes() {	
		this.add(seguidores);
		this.add(amigos);	
	}

}
