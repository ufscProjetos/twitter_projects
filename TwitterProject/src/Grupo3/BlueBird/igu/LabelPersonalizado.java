package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import Grupo3.BlueBird.igu.paineis.PainelDetalheAmigos;
import Grupo3.BlueBird.logica.MeuTwitter;

public class LabelPersonalizado extends JLabel implements MouseListener {
	
	private MeuTwitter mt;

	public LabelPersonalizado(String texto, int alinhamento, MeuTwitter mt) {
		super(texto, alinhamento);
		this.mt = mt;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		defineJanelaDetalhe();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setForeground(Color.BLACK);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setForeground(Color.WHITE);		
	}
	
	private void defineJanelaDetalhe(){
		JDialog janela;
		janela = new JDialog(new JFrame(), "Detalhe", false);
		janela.setMinimumSize(new Dimension(300, 300));
		janela.setMaximumSize(new Dimension(300, 300));
		janela.setLocationRelativeTo(null);
		janela.setContentPane(new JScrollPane(new PainelDetalheAmigos(mt)));
		janela.setVisible(true);
	}


}
