package Grupo3.BlueBird.igu;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JButton;

public class BotaoPersonalizado extends JButton {
	
	public BotaoPersonalizado (String texto) {
		super (texto);	
	}
	
	public BotaoPersonalizado (String texto, int largura, int altura) {
		super (texto);
		this.setFont(new Font(null, Font.PLAIN, 10));
		defineComponente(largura, altura);		
	}
	
	public BotaoPersonalizado (String texto, Icon imagem, int largura, int altura) {
		super (texto, imagem);
		setBackground(null);
		setBorder(null);
		defineComponente(largura, altura);		
	}

	private void defineComponente(int largura, int altura) {
		setPreferredSize(new Dimension(largura, altura));
	}

}
