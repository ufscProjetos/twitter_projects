package Grupo3.BlueBird.logica;

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
	
	Twitter twitter;
	String codigo;
    AccessToken accessToken = null;
    RequestToken requestToken;
	
	public Autenticacao(){
		twitter = new TwitterFactory().getInstance(); // instancia um objeto Twitter		
	}
	
	public void autentica() throws TwitterException{ 
		// define, na classe twitter, os códigos gerados quando a aplicação é registrada no twitter 
		twitter.setOAuthConsumer("8uOlads04QlSHOmMkbPKZw", "bFRFwXhWouJJaxtFnS2nAK8ZpnxyRRWUtT0a2QRldI");
		requestToken = twitter.getOAuthRequestToken();    // requisita o RequestToken
	    String url = requestToken.getAuthorizationURL();	// requisita a url
        AbreBrowser abreBrowser = new AbreBrowser(); // chama o método abreBrowser da classe AbreBrowser para abrir o browser
        abreBrowser.abreBrowser(url);         
	}
	
	/*public void setCodigo(String codigo){
		this.codigo = codigo;
		try{
            if(codigo.length() > 0){
              accessToken = twitter.getOAuthAccessToken(requestToken, codigo);
              new MeuTwitter(twitter);
            }else{
              accessToken = twitter.getOAuthAccessToken();
            }
         } catch (TwitterException te) {
           if(401 == te.getStatusCode()){
             System.out.println("Unable to get the access token.");
           }else{
             te.printStackTrace();
           }		
         }
	}*/
	
}
