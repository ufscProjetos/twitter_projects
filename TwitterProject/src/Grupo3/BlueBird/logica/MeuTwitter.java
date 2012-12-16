package Grupo3.BlueBird.logica;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import twitter4j.IDs;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

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
	
	public IDs getNumeroSeguidores() throws TwitterException{
		return twitter.getFollowersIDs(-1);
	}
	
	private long[] obtemNumeroAmigos() throws TwitterException {
		return twitter.getFriendsIDs(-1).getIDs();
	}
	
	public int getNumeroAmigos() throws TwitterException{
		return obtemNumeroAmigos().length;
	}
		
	public void executaPesquisaTexto(String texto) throws TwitterException{
		pesquisador = new Pesquisador(twitter);
		pesquisador.pesquisaTweetTexto(texto);
	}
	
	public List<User> mostraUsuario() throws TwitterException {
		List<User> user = new LinkedList<>();
		for (long id : obtemNumeroAmigos()) {
			pesquisador = new Pesquisador(twitter);
			user.add(twitter.showUser(id));
		}		
		return user;
	}
	
	public void excluiAmigo(long id) throws TwitterException {
		twitter.destroyFriendship(id);
	}
	
	public List<Tweet> getResultPesquisaTexto(){
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
	
	public void seguir(Long id) throws TwitterException {
		 twitter.createFriendship(id);
	}

	public String getSeguir() {
		return ajuda.getSeguir();
	}

	public String getRetweetar() {
		return ajuda.getRetweetar();
	}

	public String getVerTimelineAmigo() {
		return ajuda.getVerTimelineAmigo();
	}

	public String getDeixarDeSeguir() {
		return ajuda.getDeixarDeSeguir();
	}

}
