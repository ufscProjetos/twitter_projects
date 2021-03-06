package Grupo3.BlueBird.igu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import Grupo3.BlueBird.igu.menu.BarraMenu;
import Grupo3.BlueBird.igu.menu.Opcao;
import Grupo3.BlueBird.igu.paineis.PainelLogin;
import Grupo3.BlueBird.igu.paineis.PainelPrincipal;
import Grupo3.BlueBird.logica.MeuTwitter;
import Grupo3.BlueBird.logica.timeline.UnknownUserTwitterException;

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
	
	@SuppressWarnings("incomplete-switch")
	public void opcoesDeAjuda(Opcao opcao){
		@SuppressWarnings("unused")
		JanelaAjuda pa;
		
		switch (opcao) {
		case REFRESHTIMELINE:
			try {
				painelPrincipal.setUserTimeline(false);
				painelPrincipal.executaRefreshTimeline();
			}catch (UnknownUserTwitterException e) {
				JOptionPane.showMessageDialog(this, "Nenhum Tweeter selecionado!\n Selecione um Tweeter, por favor!'!\n",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}catch (TwitterException e) {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro durante a requisição da 'timeline'!\n" +
						" Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case REFRESHUSERTIMELINE:
			try {
				painelPrincipal.setUserTimeline(true);
				painelPrincipal.executaRefreshTimeline();
			}catch (UnknownUserTwitterException e) {
				JOptionPane.showMessageDialog(this, "Nenhum Tweet selecionado!\n Selecione um Tweet, por favor!\n",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}catch (TwitterException e) {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro durante a requisição da 'timeline'!\n" +
						" Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case RETWEET:
			try {
				painelPrincipal.retweet();
			}catch (UnknownUserTwitterException e) {
				JOptionPane.showMessageDialog(this, "Nenhum Tweet selecionado!\n Selecione um Tweet, por favor!\n",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}catch (TwitterException e) {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro durante retweet'!\n" +
				" Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case VERTIMELINE:
			pa = new JanelaAjuda("Como ver minha timeline?", mt.getComoVisualizarTimeline());
			break;
		case TWEETAR:
			pa = new JanelaAjuda("Como Tweetar alguma coisa?", mt.getComoTweetar());
			break;
		case PESQUISAR:
			pa = new JanelaAjuda("Como pesquisar pessoas ou Tweets?", mt.getComoPesquisar());
			break;
		case QUANTOSMESEGUEM:
			pa = new JanelaAjuda("Como saber quantos me seguem?", mt.getQuantosMeSeguem());
			break;
		case QUANTOSSIGO:
			pa = new JanelaAjuda("Como saber quantos eu sigo?", mt.getQuantosEuSigo());
			break;
		case AJREFRESHTIMELINE:
			pa = new JanelaAjuda("Como fazer o \"refresh\" da timeline", mt.getFazerRefreshTimeline());
			break;
		case SEGUIR:
			pa = new JanelaAjuda("Como seguir alguém?", mt.getSeguir());
			break;
		case RETWEETAR:
			pa = new JanelaAjuda("Como retweetar um status?", mt.getRetweetar());
			break;
		case VERTIMELINEAMIGO:
			pa = new JanelaAjuda("Como ver a timeline de um amigo?", mt.getVerTimelineAmigo());
			break;
		case DESFAZERAMIZADE:
			pa = new JanelaAjuda("Como deixar de seguir um amigo?", mt.getDeixarDeSeguir());
			break;
		case SOBRE:
			pa = new JanelaAjuda("Sobre o Blue Bird", mt.getSobreMim());
			break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Opcao opcao = Opcao.valueOf(e.getActionCommand());		
		if (opcao == Opcao.SAIR){
			System.exit(0);
		} else {
			opcoesDeAjuda(opcao);
		}
	}
	
	public void definePainelPrincipal(Twitter twitter){
		painelPrincipal = new PainelPrincipal(twitter, this, mt);
		setContentPane(painelPrincipal);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void defineMenu(){
		br = new BarraMenu(this); 
		setJMenuBar(br); 
	}
}
