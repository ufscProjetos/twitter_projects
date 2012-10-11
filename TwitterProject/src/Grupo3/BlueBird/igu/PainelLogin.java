package Grupo3.BlueBird.igu;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import twitter4j.TwitterException;
import Grupo3.BlueBird.igu.menu.Opcao;
import Grupo3.BlueBird.logica.AbreBrowser;
import Grupo3.BlueBird.logica.Autenticacao;

public class PainelLogin extends JPanel implements ActionListener{
	
	/**
	 * Classe responsável pela montagem e exibição do painel de login. Nele estão englobados todos os
	 * elementos visuais da tela de login bem como as suas funções.
	 */
	
	Autenticacao autenticacao; 						// Atributo que encapsula uma instância da classe Autenticacao
	AbreBrowser ab; 								// Atributo que encapsula uma instância da classe AbreBrowser
	JTextField codigo; 								// Campo de texto - onde será digitado o código de autenticação
	JButton botaoAutenticar, botaoEntrar; 			// Botões
	JLabel labelImagem; 							// JLabel responsável por armazenar a imagem - pássaro azul
	String expRegCodigo; 							// Variável que armazena a expressão regular de validação do código digitado pelo usuário
	
	public PainelLogin(Autenticacao autenticacao){ 	// Construtor
		this.autenticacao = autenticacao; 
		expRegCodigo = "\\d{7}"; 					// Exprssão regular de autenticação do código
		defineComponentes(); 
		organizaComponentes();	
		setBorder(BorderFactory.createEtchedBorder()); // Define uma borda no painel
	}

	private void defineComponentes() { 				// Método que define (cria) os componentes visuais
		labelImagem = new JLabel(new ImageIcon(getClass().getResource("/imagens/img.png")));		
		botaoAutenticar = new JButton("Autenticar");
		botaoAutenticar.addActionListener(this);
		codigo = new JTextField(20);
		codigo.addKeyListener(new Validador());
		botaoEntrar = new JButton("Entrar");
		botaoEntrar.setEnabled(false);
		botaoEntrar.addActionListener(this);	
	}

	private void organizaComponentes() { 			// Método que organiza os componentes na tela		
		GroupLayout layout = new GroupLayout(this); // stancia um objeto GroupLayout - gerenciador de layout
		layout.setAutoCreateGaps(true); 
		layout.setAutoCreateContainerGaps(true);
		setLayout(layout);
		/**
		 * Utilizando o GroupLayout é necessário definir a disposição dos componentes nas dimensões 
		 * horizontal e vertical, de forma que o layout seja definido
		 */
		// Dimensão Horizontal
		{
			SequentialGroup sg = layout.createSequentialGroup(); 	// cria um grupo sequencial
			sg.addComponent(labelImagem); 							// adiciona a imagem no local específico
			ParallelGroup pg = layout.createParallelGroup(Alignment.LEADING);			// cria um grupo paralelo
			pg.addComponent(botaoAutenticar); 						// adiciona o botão Autenticar
			SequentialGroup sg2 = layout.createSequentialGroup();   // cria outro grupo sequencial
			sg2.addComponent(codigo);	// adiciona o campo de texto
			sg2.addComponent(botaoEntrar); // adiciona o botao Entrar
			pg.addGroup(sg2); // adiciona o grupo armazenado na variável sg2 ao grupo pg
			sg.addGroup(pg);	// adiciona o grupo pg ao layout sg
			layout.setHorizontalGroup(sg); // define o layout no painel
		}
		
		// Dimensão Vertical
		
		/**
		 * Na dimensão vertical há algumas diferenças quanto ao local onde serão adicionados os botõs e campos de texto e imagem
		 * bem como pode haver mais ou menos grupos.
		 */
		
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

	@Override // significa que este método está sendo sobreescrito de uma interface
	public void actionPerformed(ActionEvent e) { // método da interface ActionListener, responsável por tratar os eventos gerados pelos botões e munus
		if(e.getActionCommand() == "Autenticar"){
			try {
				autenticacao.autentica(); // se o botão Autenticar for clicado, chama o método autentica da classe Autenticação
			} catch (TwitterException e1) {
				e1.printStackTrace();
			}
		} else {
			// mensagem exibida ao clicar em Entrar - isso será modificado, pois aqui será tratado o código fornecido pelo usuário
			JOptionPane.showMessageDialog(this, "Espere um pouco!\n A partir deste momento " +    
					"as informações contidas neste\n programa são restritas aos desenvolvedores."
					, "Atenção", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/imagens/cavalo.jpeg")));						
		}
	}
	/**
	 * método que monitora o campo de texto e habilita ou desabilita o botão Entrar
	 */
	class Validador extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			if (codigo.getText().matches(expRegCodigo))
				botaoEntrar.setEnabled(true);
			else
				botaoEntrar.setEnabled(false);
		}
	}
	
	/**
	 * Método que trata os eventos do menu, vindos da classe Janela. 
	 */
	
	public void trataOpcoesMenu(ActionEvent e){
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
