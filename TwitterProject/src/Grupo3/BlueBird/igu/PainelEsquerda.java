package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelEsquerda extends JPanel {
	
	private JButton botaoSeguir;
	private JLabel teste;
	
	public PainelEsquerda(){
		defineComponentes();
		//organizaComponentes();
	}

	private void defineComponentes() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(50, 480));
		this.setMaximumSize(new Dimension(50, 480));
		this.setBackground(new Color(0, 195, 248));
		this.setOpaque(true);
	}

	private void organizaComponentes() {
		this.add(botaoSeguir);		
		this.add(teste);		
	}

}
