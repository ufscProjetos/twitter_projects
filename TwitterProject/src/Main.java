
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // The factory instance is re-useable and thread safe.
	    Twitter twitter = new TwitterFactory().getInstance();
	    Status status = twitter.updateStatus(latestStatus);
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}

}
