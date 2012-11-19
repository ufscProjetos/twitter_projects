//package Grupo3.BlueBird.logica;
//
//import java.util.List;
//
//import Grupo3.BlueBird.igu.TimelineView;
//
//import twitter4j.Status;
//import twitter4j.Twitter;
//import twitter4j.TwitterException;
//
//public class Timeline {
//
//	private Twitter _twitter;
//	private List<Status> _statuses;
//	private TimelineView _currentTimelineView;
//
//		public Timeline(Twitter twitter) {
//			_twitter = twitter;
//		}
//
//		public void refreshHomeTimeline() throws TwitterException{
//	            _statuses = _twitter.getHomeTimeline();
//	            _currentTimelineView.printStatus(_statuses);
//	    }
//
//		public List<Status> getTimeline(){
//			return _statuses;
//		}
//
//		public void setCurrentTimelineView(TimelineView tlv){
//			_currentTimelineView = tlv;
//		}
//
//		public TimelineView getCurrentTimelineView(){
//			return _currentTimelineView;
//		}
//
//
//}