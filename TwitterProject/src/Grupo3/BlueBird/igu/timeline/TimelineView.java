package Grupo3.BlueBird.igu.timeline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import twitter4j.TwitterException;
import Grupo3.BlueBird.logica.timeline.Timeline;
import Grupo3.BlueBird.logica.timeline.TimelineTweet;

public class TimelineView {

	private Container _container;
	private Timeline _timeline;
	private TimelineListModel _listModel;
	private JList<Object> _timelineList;

	public TimelineView(Timeline timeline) throws TwitterException {
		_timeline = timeline;
		updateTimeline();
	}

	public Container getContainer() {
		return _container;
	}

	public void updateTimeline() throws TwitterException {
		_listModel = new TimelineListModel(_timeline.refreshHomeTimeline());
		_timelineList = new JList<>(_listModel);
		_timelineList.setCellRenderer(new TimelineRenderer());
		_timelineList.setVisibleRowCount(6);
		_timelineList.addListSelectionListener(new TimelineListSelection(
				_timelineList));
		JScrollPane listPane = new JScrollPane(_timelineList);
		_container = new JPanel(new BorderLayout());
		_container.setBackground(Color.white);
		_container.add(listPane);
	}

	public void retweet() throws TwitterException {
		_timeline.retweetFocusedMessage();
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
				 _timeline.setCurrentId(tweet.getStatus().getId());
			 }
		}
	}
}