package Grupo3.BlueBird.logica;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class UpdateStatus {
	
	private Twitter twitter;
	
	public UpdateStatus(Twitter twitter){
		this.twitter = twitter;
	}	
		
	public void atualizaStatus(String novoStatus) throws TwitterException{
			twitter.updateStatus(novoStatus);
	}
}

