package Grupo3.BlueBird.igu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import twitter4j.Twitter;
import Grupo3.BlueBird.igu.menu.BarraMenu;
import Grupo3.BlueBird.igu.menu.Opcao;
import Grupo3.BlueBird.logica.MeuTwitter;

public class Janela extends JFrame implements ActionListener {
	
	MeuTwitter mt;
	BarraMenu br;
	PainelLogin painelLogin;
	PainelPrincipal painelPrincipal;
	
	public Janela(MeuTwitter mt){ 
		super ("Blue Bird");
		this.mt = mt;
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		definePainelLogin(); 
		pack();
		setLocationRelativeTo(null);
	}	
	
	private void definePainelLogin() {
		painelLogin = new PainelLogin(mt, this);
		setContentPane(painelLogin);		
	}
	
	public void executa(){
		setVisible(true); 
	}
	

	private void executaOpcao(Opcao opcao) {
		
		switch (opcao) {
		case AJATUALIZATIMTLINE:
			JOptionPane.showMessageDialog(this, "Para atualizar a visualização da sua Timeline,\n no menu vá em 'Opções' e escolha a\n opção " +
					"'Atualiza Timeline'", "Atualizando Timeline", JOptionPane.INFORMATION_MESSAGE);
			break;
		
		case TIMELINE:
			JOptionPane.showMessageDialog(this, "Fica localizado no painel à direita e\n permite a visualização" +
					" da sua 'linha do tempo'", "Timeline", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case TWEETAR:
			JOptionPane.showMessageDialog(this, "No campo localizado à esquerda, é possível digitar\n o seu novo status, com limite de 140 " +
					"caracteres.\n Digite o que quiser e clique em 'Tweetar'", "Tweetar", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case SOBRE:
			JOptionPane.showMessageDialog(this, "Blue Bird\n Software desenvolvido como trabalho" +
					" final da disciplina INE 5605\n Versão 1.0 - 25/10/2012", "Sobre", JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Opcao opcao = Opcao.valueOf(e.getActionCommand());		
		if (opcao == Opcao.SAIR){
			System.exit(0);
		} else {
			executaOpcao(opcao);
		}
	}
	
	protected void definePainelPrincipal(Twitter twitter){
		painelPrincipal = new PainelPrincipal(twitter, this);
		setContentPane(painelPrincipal);
		pack();
		setLocationRelativeTo(null);
	}
	
	protected void defineMenu(){
		br = new BarraMenu(this); 
		setJMenuBar(br); 
	}
}
