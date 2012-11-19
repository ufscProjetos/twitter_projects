package Grupo3.BlueBird.logica.timeline;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Timeline {

	private Twitter _twitter;
	private TweetedCollection _collection;
	private TimelineListModel _listModel;
	private JList<Object> _timelineList;
	private long _currentId;
//	private TimelineListSelection _listSelection;

	public Timeline(Twitter twitter) {
		_twitter = twitter;
		_collection = new TweetedCollection();
		_listModel = new TimelineListModel(_collection);
		_timelineList = new JList<>(_listModel);
//		_listSelection = new TimelineListSelection(_timelineList);
	}

	public JList<Object> refreshHomeTimeline() throws TwitterException {
		_collection.updateCollection(_twitter.getHomeTimeline());
		_listModel.setCollection(_collection);
		_timelineList = new JList<>(_listModel); //.setModel(_listModel); //
		_timelineList.setCellRenderer(new TimelineRenderer());
		_timelineList.setVisibleRowCount(6);
		_timelineList.addListSelectionListener(new TimelineListSelection(
				_timelineList));
		return _timelineList;
	}
	
	public void retweetFocusedMessage() throws TwitterException{
		if (_currentId != 0){
			_twitter.retweetStatus(_currentId);
		}
	}
	
	private class TimelineListSelection implements ListSelectionListener {

		JList<Object> _list;
		public TimelineListSelection(JList<Object> list){
			_list = list;
		}
		
		@Override
		public void valueChanged(ListSelectionEvent event) {
			 if (!event.getValueIsAdjusting()){ 
				 TimelineTweet tweet = (TimelineTweet) _list.getSelectedValue();
				 _currentId = tweet.getStatus().getId();
				 //System.out.println(tweet.getStatus().getId());
			 }
		}

	}
}