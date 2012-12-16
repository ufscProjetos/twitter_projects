package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JanelaAjuda extends JDialog {
	
	JPanel painelPrincipal, painelEsquerda, painelDireita;
	JTextArea texto, titulo;
	
	public JanelaAjuda(String tituloAjuda, String textoAjuda){
		super (new JFrame(), "Ajuda", false);
		defineComponentes(tituloAjuda, textoAjuda);
		organizaComponentes();		
	}

	private void defineComponentes(String tituloAjuda, String textoAjuda) {
		setMinimumSize(new Dimension(505, 250));
		setMaximumSize(new Dimension(505, 250));
		setLocationRelativeTo(null);
		setVisible(true);
		painelPrincipal = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setContentPane(painelPrincipal);
		defineTitulo(tituloAjuda);
		defineAreatexto(textoAjuda);
		definePainelEsquerda();
		definePainelDireita();
	}

	private void definePainelEsquerda(){
		painelEsquerda = new JPanel();
		painelEsquerda.setLayout(new BoxLayout(painelEsquerda, BoxLayout.PAGE_AXIS));
		painelEsquerda.setMinimumSize(new Dimension(60, 250));
		painelEsquerda.setMaximumSize(new Dimension(60, 250));
		painelEsquerda.setPreferredSize(new Dimension(60, 250));
		painelEsquerda.setBackground(new Color(0, 195, 248));
		painelEsquerda.add(new JLabel(new ImageIcon(getClass().getResource("/imagens/twitter-bird2.png")), SwingConstants.CENTER));
		painelEsquerda.setBorder(new EmptyBorder(0, 12, 0, 0));
	}
				
		private void definePainelDireita(){		
		painelDireita = new JPanel();
		painelDireita.setLayout(new BoxLayout(painelDireita, BoxLayout.PAGE_AXIS));
		painelDireita.setMinimumSize(new Dimension(440, 250));
		painelDireita.setMaximumSize(new Dimension(440, 250));
		painelDireita.setPreferredSize(new Dimension(440, 250));
	}
	
	private void defineTitulo(String tituloAjuda) {
		this.titulo = new AreaTextoPersonalizada(465, 50);
		this.titulo.setForeground(new Color(0, 195, 248));
		this.titulo.setText(tituloAjuda);
		this.titulo.setEditable(false);
		this.titulo.setFont(new Font("Sans Serif", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		this.titulo.setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	private void defineAreatexto(String texto){
		this.texto = new AreaTextoPersonalizada(465, 300);
		this.texto.setText(texto);
		this.texto.setEditable(false);
		this.texto.setBorder(new EmptyBorder(10, 10, 30, 10));
		this.texto.setLineWrap(true);
	}
	
	private void organizaComponentes() {
		painelDireita.add(titulo);
		painelDireita.add(texto);		
		painelPrincipal.add(painelEsquerda);
		painelPrincipal.add(painelDireita);
	}

	

}
