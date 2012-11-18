package Grupo3.BlueBird.igu;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JButton;

public class BotaoPersonalizado extends JButton {
	
	public BotaoPersonalizado (String texto) {
		super (texto);	
	}
	
	public BotaoPersonalizado (String texto, int largura, int altura) {
		super (texto);
		defineComponente(largura, altura);		
	}
	
	public BotaoPersonalizado (String texto, Icon imagem, int largura, int altura) {
		super (texto, imagem);
		defineComponente(largura, altura);		
	}

	private void defineComponente(int largura, int altura) {
		setPreferredSize(new Dimension(largura, altura));
		setBackground(null);
		setBorder(null);
	}

}
