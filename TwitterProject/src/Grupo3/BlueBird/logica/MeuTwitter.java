package Grupo3.BlueBird.logica;

import java.io.IOException;
import java.net.URISyntaxException;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class MeuTwitter {
	
	Autenticador autenticacao;
	
	public MeuTwitter(){
		autenticacao = new Autenticador();
	}

	public void instanciaTwitter() throws TwitterException, URISyntaxException, IOException{
		autenticacao.instanciaObjetoTwitter();	
	}

	public boolean getIntanciouTwitter() {
		return autenticacao.getIntanciouObjetoTwitter();
	}

	public Twitter efetuaAutenticacao(String codigo) throws TwitterException {
		return autenticacao.autentica(codigo);
	}
	

}
