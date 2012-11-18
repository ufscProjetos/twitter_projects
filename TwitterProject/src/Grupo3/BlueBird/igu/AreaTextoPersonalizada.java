package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;

public class AreaTextoPersonalizada extends JTextArea {
	
	public AreaTextoPersonalizada(int largura, int altura) {
		defineComponente(largura, altura);
	}
	
	private void defineComponente(int largura, int altura) {
		setMinimumSize(new Dimension(largura, altura));
		setMaximumSize(new Dimension(largura, altura));
		setBackground(Color.WHITE);
	}

}
