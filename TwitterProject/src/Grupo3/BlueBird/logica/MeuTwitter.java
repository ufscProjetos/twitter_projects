package Grupo3.BlueBird.logica;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import twitter4j.IDs;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class MeuTwitter {
	
	Autenticador autenticador;
	Twitter twitter;
	Pesquisador pesquisador;
	Ajuda ajuda;
	
	public MeuTwitter(){
		autenticador = new Autenticador();	
		ajuda = new Ajuda();
	}

	public void instanciaTwitter() throws TwitterException, URISyntaxException, IOException{
		autenticador.instanciaObjetoTwitter();		
	}

	public boolean getIntanciouTwitter() {
		return autenticador.getIntanciouObjetoTwitter();
	}

	public Twitter efetuaAutenticacao(String codigo) throws TwitterException {
		twitter = autenticador.autentica(codigo);
		return twitter;
	}
	
	public int getNumeroSeguidores() throws TwitterException{
		int cont = 0;
		IDs ids = twitter.getFollowersIDs(-1);
		cont = ids.getIDs().length;	
		return cont;
	}
	
	public int getNumeroAmigos() throws TwitterException{
		int cont = 0;
		IDs ids = twitter.getFriendsIDs(-1);
		cont = ids.getIDs().length;	
		return cont;
	}
	
	public void executaPesquisa(String texto) throws TwitterException{
		pesquisador = new Pesquisador(twitter);
		pesquisador.pesquisaTweet(texto);
	}
	
	public List<Tweet> getResultPesquisa(){
		return pesquisador.getTweetPesquisado();
	}
	
	public String getComoVisualizarTimeline(){
		return ajuda.getComoVisualizarTimeline();
	}

	public String getComoTweetar() {
		return ajuda.getComoTweetar();
	}

	public String getComoPesquisar() {
		return ajuda.getComoPesquisar();
	}

	public String getQuantosMeSeguem() {
		return ajuda.getQuantosMeSeguem();
	}
	
	public String getQuantosEuSigo(){
		return ajuda.getQuantosEuSigo();
	}

	public String getSobreMim() {
		return ajuda.getSobreMim();
	}

	public String getFazerRefreshTimeline() {
		return ajuda.getFazerRefreshTimeline();
	}

}
