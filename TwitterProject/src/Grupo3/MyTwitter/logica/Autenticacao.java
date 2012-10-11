package Grupo3.MyTwitter.logica;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class Autenticacao {
	
	Twitter twitter;
	String codigo;
    AccessToken accessToken = null;
    RequestToken requestToken;
	
	public Autenticacao(){
		twitter = new TwitterFactory().getInstance();		
	}
	
	public void autentica() throws TwitterException{
		twitter.setOAuthConsumer("mucuDvrXjCvWPsWXA588zw", "0191Z6rMjEMOAOwBDCZgARDYE1fxET89FVCrpSueM");
		requestToken = twitter.getOAuthRequestToken();    
	    String url = requestToken.getAuthorizationURL();
        AbreBrowser abreBrowser = new AbreBrowser();
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
