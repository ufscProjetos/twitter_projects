package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;

public class CampoTextoPersonalizado extends JTextField {
	
	public CampoTextoPersonalizado (int largura, int altura) {
		defineComponente(largura, altura);
	}

	private void defineComponente(int largura, int altura) {
		setPreferredSize(new Dimension(largura, altura));
		setBackground(Color.WHITE);
		setBorder(null);
	}

}
