package Grupo3.BlueBird.igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Grupo3.BlueBird.logica.timeline.Timeline;
import twitter4j.TwitterException;

public class TimelineView {

	private Container _container;
	private Timeline _timeline;

	public TimelineView(Timeline timeline) throws TwitterException {
		_timeline = timeline;
		updateTimeline();
	}

	public Container getContainer() {
		return _container;
	}

	public void updateTimeline() throws TwitterException {
		JScrollPane listPane = new JScrollPane(_timeline.refreshHomeTimeline());
		_container = new JPanel(new BorderLayout());
		_container.setBackground(Color.white);
		_container.add(listPane);
	}

	public void retweet() throws TwitterException {
		_timeline.retweetFocusedMessage();
	}
}