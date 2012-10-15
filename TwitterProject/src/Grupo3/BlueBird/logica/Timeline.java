package Grupo3.BlueBird.logica;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class Timeline {
	
	private Twitter twitter;

		public Timeline(Twitter twitter) {
			this.twitter = twitter;
		}
		
		public List<Status> getTimeline(){
	        try {
	            User user = twitter.verifyCredentials();
	            List<Status> statuses = twitter.getHomeTimeline();
	            System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
	            for (Status status : statuses) {
	                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	            }
	            return statuses;
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            return null;
	        }
	    }
}
