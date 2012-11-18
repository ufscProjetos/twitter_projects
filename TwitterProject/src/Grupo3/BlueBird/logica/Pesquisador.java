package Grupo3.BlueBird.logica;

import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class Pesquisador {
	
	Twitter twitter;
	QueryResult result;
	ResponseList<User> usuarios;
	List<Tweet> tweet;
	
	public Pesquisador(Twitter twitter){
		this.twitter = twitter;
	}

	public void pesquisaTweet(String texto) throws TwitterException {
		Query query = new Query(texto);
	    result = twitter.search(query);
	    tweet = result.getTweets();
	}

	public List<Tweet> getTweetPesquisado(){
		return tweet;
	}
	
}
