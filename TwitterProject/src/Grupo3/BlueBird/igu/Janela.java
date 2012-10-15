package Grupo3.BlueBird.igu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import twitter4j.Twitter;
import Grupo3.BlueBird.igu.menu.BarraMenuLogin;
import Grupo3.BlueBird.logica.Autenticacao;

public class Janela extends JFrame implements ActionListener {
	
	Autenticacao autenticacao;
	BarraMenuLogin br;
	PainelLogin painelLogin;
	PainelPrincipal painelPrincipal;
	
	public Janela(Autenticacao autenticacao){ // construtor
		super ("Blue Bird"); // define o título da janela
		this.autenticacao = autenticacao;
		setDefaultCloseOperation(EXIT_ON_CLOSE); // encerra o programa ao fechar a janela
		definePainelLogin(); // define o painel de login
	}	
	
	private void definePainelLogin() {
		painelLogin = new PainelLogin(autenticacao, this);
		setContentPane(painelLogin);
		pack();
		setLocationRelativeTo(null);		
	}
	
	/**
	 * primeiro método executado do programa, tornando a janela visível ao usuário
	 */
	public void executa(){
		setVisible(true); 
	}

	@Override
	/**
	 * captura os eventos do menu e chama o método responsável por tratá-los
	 */
	public void actionPerformed(ActionEvent e) {
		painelLogin.trataOpcoesMenu(e);
	}
	
	public void definePainelPrincipal(Twitter twitter){
		painelPrincipal = new PainelPrincipal(twitter);
		painelPrincipal.setPreferredSize(new Dimension(600, 300));
		setContentPane(painelPrincipal);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void defineMenu(){
		br = new BarraMenuLogin(this); // instancia a barra de menu
		setJMenuBar(br); // insere a barra de menu na janela
	}
	
	

}
