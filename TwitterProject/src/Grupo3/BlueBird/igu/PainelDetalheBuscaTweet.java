package Grupo3.BlueBird.igu;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PainelDetalheBuscaTweet extends JPanel implements MouseListener {
		
	public PainelDetalheBuscaTweet(){
		defineComponentes();
	}
	
	private void defineComponentes(){
		//setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 195, 248)));
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// chama método que executa função de seguir alguém
		JOptionPane.showMessageDialog(this, "Sou um demonho!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Quando o mouse é precionado - não precisa acontecer nada
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Quando o mouse é largado - não precisa acontecer nada

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Quando o mouse está sobre o JLabel - mudar a cor da fonte

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Quando o mouse sai do JLabel - volta a cor original

	}

}
