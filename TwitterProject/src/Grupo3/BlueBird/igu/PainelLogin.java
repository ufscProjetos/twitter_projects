package Grupo3.BlueBird.igu;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import twitter4j.TwitterException;
import Grupo3.BlueBird.logica.AbreBrowser;
import Grupo3.BlueBird.logica.Autenticacao;

public class PainelLogin extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Autenticacao autenticacao;
	AbreBrowser ab;
	JTextField codigo;
	JButton botaoAutenticar, botaoEntrar;
	JLabel labelImagem;
	String expRegCodigo;
	
	public PainelLogin(Autenticacao autenticacao){
		this.autenticacao = autenticacao;
		expRegCodigo = "\\d{7}";
		defineComponentes();
		organizaComponentes();	
		setBorder(BorderFactory.createEtchedBorder());
	}

	private void defineComponentes() { 
		labelImagem = new JLabel(new ImageIcon(getClass().getResource("/imagens/img.png")));		
		botaoAutenticar = new JButton("Autenticar");
		botaoAutenticar.addActionListener(this);
		codigo = new JTextField(20);
		codigo.addKeyListener(new Validador());
		botaoEntrar = new JButton("Entrar");
		botaoEntrar.setEnabled(false);
		botaoEntrar.addActionListener(this);	
	}

	private void organizaComponentes() {
		
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		setLayout(layout);
		
		// Dimensão Horizontal
		{
			SequentialGroup sg = layout.createSequentialGroup();
			sg.addComponent(labelImagem);
			ParallelGroup pg = layout.createParallelGroup(Alignment.LEADING);			
			pg.addComponent(botaoAutenticar);
			SequentialGroup sg2 = layout.createSequentialGroup();
			sg2.addComponent(codigo);
			sg2.addComponent(botaoEntrar);
			pg.addGroup(sg2);
			sg.addGroup(pg);
			layout.setHorizontalGroup(sg);
		}
		
		// Dimensão Vertical
		
		{
			SequentialGroup sg = layout.createSequentialGroup();
			ParallelGroup pg = layout.createParallelGroup(Alignment.CENTER);
			pg.addComponent(labelImagem);
			SequentialGroup sg2 = layout.createSequentialGroup();
			sg2.addComponent(botaoAutenticar);
			ParallelGroup pg2 = layout.createParallelGroup(Alignment.BASELINE);
			pg2.addComponent(codigo);
			pg2.addComponent(botaoEntrar);
			sg2.addGroup(pg2);
			pg.addGroup(sg2);
			sg.addGroup(pg);
			layout.setVerticalGroup(sg);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Autenticar"){
			try {
				autenticacao.autentica();
			} catch (TwitterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Espere um pouco!\n A partir deste momento " +
					"as informações contidas neste\n programa são restritas aos desenvolvedores."
					, "Atenção", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/imagens/cavalo.jpeg")));						
		}
	}
	
	class Validador extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			if (codigo.getText().matches(expRegCodigo))
				botaoEntrar.setEnabled(true);
			else
				botaoEntrar.setEnabled(false);
		}
	}

}
