package Grupo3.BlueBird.logica.timeline;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class Timeline {

	private Twitter _twitter;
	private TweetedCollection _collection;
	private long _currentId;
	private User _user;
	
	
	public Timeline(Twitter twitter) {
		_twitter = twitter;
		_collection = new TweetedCollection();
	}

	public TweetedCollection refreshHomeTimeline() throws TwitterException {
		_collection.updateCollection(_twitter.getHomeTimeline());		
		return _collection;
	}
	
	public TweetedCollection refreshUserTimeline() throws UnknownUserTwitterException,TwitterException {
		if (_currentId != 0){
			_collection.updateCollection(_twitter.getUserTimeline(_user.getScreenName()));
		}else{
			throw new UnknownUserTwitterException (new Exception());
		}
		return _collection;
	}
	
	public void destroyFriendship() throws TwitterException {
		if (_currentId != 0){
			_twitter.destroyFriendship(_currentId);
		}
	}
	
	public void retweetFocusedMessage() throws TwitterException{
		if (_currentId != 0){
			_twitter.retweetStatus(_currentId);
		}
	}

	public void setCurrentId(long id) {
		_currentId = id;
	}

	public void setUser(User user) {
		_user = user;
	}
}