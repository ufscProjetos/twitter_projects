package Grupo3.BlueBird.logica;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class Autenticador {
	
	Twitter twitter;
    AccessToken accessToken;
    RequestToken requestToken;
    boolean instanciou;
	
	public Autenticador(){
		
	}
	
	public void instanciaObjetoTwitter() throws TwitterException, URISyntaxException, IOException {	
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer("8uOlads04QlSHOmMkbPKZw", "bFRFwXhWouJJaxtFnS2nAK8ZpnxyRRWUtT0a2QRldI");
		requestToken = twitter.getOAuthRequestToken();
	    String url = requestToken.getAuthorizationURL();
	    abreBrowser(url);
        instanciou = true;
	}
	
	public Twitter autentica(String codigo) throws TwitterException {
        accessToken = twitter.getOAuthAccessToken(requestToken, codigo);
		return this.twitter;
	}
	
	public boolean getIntanciouObjetoTwitter(){
		return instanciou;
	}
	
	private void abreBrowser(String url) throws URISyntaxException, IOException{  
		Desktop desktop = Desktop.getDesktop();  
		URI uri = new URI(url);  
		desktop.browse(uri);
	}	
}
