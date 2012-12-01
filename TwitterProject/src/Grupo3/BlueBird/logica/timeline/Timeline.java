package Grupo3.BlueBird.logica.timeline;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Timeline {

	private Twitter _twitter;
	private TweetedCollection _collection;
	private long _currentId;

	public Timeline(Twitter twitter) {
		_twitter = twitter;
		_collection = new TweetedCollection();
	}

	public TweetedCollection refreshHomeTimeline() throws TwitterException {
		_collection.updateCollection(_twitter.getHomeTimeline());		
		return _collection;
	}
	
	public void retweetFocusedMessage() throws TwitterException{
		if (_currentId != 0){
			_twitter.retweetStatus(_currentId);
		}
	}

	public void setCurrentId(long id) {
		_currentId = id;
	}
}