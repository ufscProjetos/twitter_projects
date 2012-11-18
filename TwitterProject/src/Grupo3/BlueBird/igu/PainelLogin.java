package Grupo3.BlueBird.igu;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import Grupo3.BlueBird.logica.MeuTwitter;

public class PainelLogin extends JPanel implements ActionListener{
	
	/**
	 * Classe responsável pela montagem e exibição do painel de login. Nele estão englobados todos os
	 * elementos visuais da tela de login bem como as suas funções.
	 */
	
	MeuTwitter mt;
	Janela janela;
	Twitter twitter;
	JTextField codigo; 								// Campo de texto - onde será digitado o código de autenticação
	JButton botaoAutenticar, botaoEntrar; 			// Botões
	JLabel labelImagem, labelInfo; 					// JLabel responsável por armazenar a imagem - pássaro azul
	String expRegCodigo; 							// Variável que armazena a expressão regular de validação do código digitado pelo usuário
	
	public PainelLogin(MeuTwitter mt, Janela janela){ 	// Construtor
		this.mt = mt; 
		this.janela = janela;
		expRegCodigo = "\\d{7}"; 					// Exprssão regular de autenticação do código
		defineComponentes(); 
		organizaComponentes();	
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
		GroupLayout layout = new GroupLayout(this); // instancia um objeto GroupLayout - gerenciador de layout
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
			ParallelGroup pg2 = layout.createParallelGroup(Alignment.LEADING);			// cria um grupo paralelo
			pg2.addComponent(botaoAutenticar); 						// adiciona o botão Autenticar
			SequentialGroup sg2 = layout.createSequentialGroup();   // cria outro grupo sequencial
			sg2.addComponent(codigo);	// adiciona o campo de texto
			sg2.addComponent(botaoEntrar); // adiciona o botao Entrar
			pg2.addGroup(sg2); // adiciona o grupo armazenado na variável sg2 ao grupo pg
			sg.addGroup(pg2);	// adiciona o grupo pg ao layout sg
			layout.setHorizontalGroup(sg); // define o layout no painel
		}
		
		// Dimensão Vertical
		
		/**
		 * Na dimensão vertical há algumas diferenças quanto ao local onde serão adicionados os botões e campos de texto e imagem
		 * bem como pode haver mais ou menos grupos.
		 */
		
		{
			SequentialGroup sg = layout.createSequentialGroup();
			ParallelGroup pg = layout.createParallelGroup(Alignment.CENTER);
			pg.addComponent(labelImagem);
			SequentialGroup sg2 = layout.createSequentialGroup();
			sg2.addComponent(botaoAutenticar);					// adiciona o botão Autenticar
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
	public void actionPerformed(ActionEvent e) { // método da interface ActionListener, responsável por tratar os eventos gerados pelos botões e munus
		if(e.getActionCommand() == "Autenticar"){
			try {
				mt.instanciaTwitter(); // se o botão Autenticar for clicado, chama o método autentica da classe Autenticação
			} catch (TwitterException te) {
				JOptionPane.showMessageDialog(this, "Não foi possível estabelecer a conexão com o " +
						"servidor,\n ou o serviço do Twitter está indiponível!\n Por favor, verifique sua conexão com a internet e\n tente novamente " +
						"mais tarde. ", "Erro", JOptionPane.ERROR_MESSAGE);
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(this, "Ocorreu um problema na tentativa de abrir o navegador de internet.\n" +
						" Tente novamente e se o problema persistir, contate o administrador do sistema. ", "Erro", JOptionPane.ERROR_MESSAGE);
				
			} catch (URISyntaxException ure) {
				JOptionPane.showMessageDialog(this, "Ocorreu um problema com a URL solicitada.\n Talvez a URL" +
						" esteja incorreta.\n Por favor, tente novamente! ", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			if(mt.getIntanciouTwitter()){
				try {
					twitter = mt.efetuaAutenticacao(codigo.getText());
					janela.definePainelPrincipal(twitter);
					janela.defineMenu();
					janela.setResizable(false);
				} catch (TwitterException te) {
					if (te.getStatusCode() != 403) {
						JOptionPane.showMessageDialog(this, "Não foi possível validar o código " +
								"fornecido!\n Você deverá gerar um novo código de autenticação. ", "Erro", JOptionPane.ERROR_MESSAGE);
						codigo.setText("");
						botaoEntrar.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(this, "Solicitação negada!\n Verifique se você tem permição " +
								"para acessar a página solicitada.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Você precisa clicar no botão 'Autenticar' e\n gerar o código " +
						"de autenticação!", "Atenção", JOptionPane.INFORMATION_MESSAGE);		
				codigo.setText("");
				botaoEntrar.setEnabled(false);
			}
		}
	}
	/**
	 * Classe interna que monitora o campo de texto e habilita ou desabilita o botão Entrar
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
}
