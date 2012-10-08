package Grupo3.BlueBird.igu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Grupo3.BlueBird.igu.menu.BarraMenuLogin;
import Grupo3.BlueBird.igu.menu.Opcao;
import Grupo3.BlueBird.logica.Autenticacao;

public class Janela extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	Autenticacao autenticacao;
	BarraMenuLogin br;
	
	public Janela(Autenticacao autenticacao){
		super ("Blue Bird");
		br = new BarraMenuLogin(this);
		setJMenuBar(br);
		this.autenticacao = autenticacao;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		definePainelLogin();
	}	
	
	private void definePainelLogin() {
		PainelLogin painelLogin = new PainelLogin(autenticacao);
		setContentPane(painelLogin);
		pack();
		setLocationRelativeTo(null);		
	}

	public void executa(){
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Opcao opcao = Opcao.valueOf(e.getActionCommand());
		
		if(opcao == Opcao.SAIR){
			System.exit(0);
		}else
		if(opcao == Opcao.SOBRE){
				JOptionPane.showMessageDialog(this, "Blue Bird\n Software desenvolvido como trabalho" +
						" final da disciplina INE 5605\n Versão TF1", "Sobre", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "Para utilizar o programa, clique sobre o botão Autenticar.\n Você será " +
					"redirecionado para uma página Web onde deverá\n informar seu usuário e senha do Twitter.\n Após efetuar o login " +
					"será fornecido um código, que deverá\n ser digitado no campo correspondente nesta aplicação.\n Se o código" +
					" estiver em formato válido, basta clicar no\n botão Entrar para utilizar o programa.",
					"Utilização", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	

}
