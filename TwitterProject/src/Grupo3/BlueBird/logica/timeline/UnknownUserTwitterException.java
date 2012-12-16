package Grupo3.BlueBird.logica.timeline;

import twitter4j.TwitterException;

public class UnknownUserTwitterException extends TwitterException {

	public UnknownUserTwitterException(Exception cause) {
		super(cause);
	}

}
