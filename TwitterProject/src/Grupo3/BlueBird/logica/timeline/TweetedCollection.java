package Grupo3.BlueBird.logica.timeline;

import java.util.List;

import twitter4j.Status;

public class TweetedCollection {

	private TimelineTweet[] _collection;

	public TweetedCollection(List<Status> list) {
		updateCollection(list);
	}

	public TweetedCollection() {
	}

	public TimelineTweet[] getCollection() {
		return (_collection);
	}

	public int getNumCollection() {
		return (_collection.length);
	}

	public void updateCollection(List<Status> list) {
		_collection = new TimelineTweet[list.size()];
		for (Status status : list)
			_collection[list.indexOf(status)] = new TimelineTweet(status);
	}
}