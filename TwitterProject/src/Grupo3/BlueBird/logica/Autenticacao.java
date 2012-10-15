package Grupo3.BlueBird.logica;

import Grupo3.BlueBird.igu.Janela;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Classe responsável por instanciar um objeto Twitter - o principal da classe Twitter4j -, o qual, 
 * através de seus métodos, fará a requisição dos request tokens e da url.  
 */

public class Autenticacao {
	
	Janela janela;
	Twitter twitter;
    AccessToken accessToken;
    RequestToken requestToken;
    boolean instanciou;
	
	public Autenticacao(){
		
	}
	
	public void instanciaObjetoTwitter() {	
		twitter = new TwitterFactory().getInstance(); // instancia um objeto Twitter	
		// define, na classe twitter, os códigos gerados quando a aplicação é registrada no twitter	
		twitter.setOAuthConsumer("8uOlads04QlSHOmMkbPKZw", "bFRFwXhWouJJaxtFnS2nAK8ZpnxyRRWUtT0a2QRldI");
		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	    String url = requestToken.getAuthorizationURL();	// requisita a url
        AbreBrowser abreBrowser = new AbreBrowser(); // chama o método abreBrowser da classe AbreBrowser para abrir o browser
        abreBrowser.abreBrowser(url); 
        instanciou = true;
	}
	
	public Twitter autentica(String codigo) throws TwitterException {
        accessToken = twitter.getOAuthAccessToken(requestToken, codigo);
		return this.twitter;
	}
	
	public boolean getIntanciouObjetoTwitter(){
		return instanciou;
	}
	
}
