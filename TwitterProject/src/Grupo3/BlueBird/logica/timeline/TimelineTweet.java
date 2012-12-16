package Grupo3.BlueBird.logica.timeline;

import java.net.URL;
import java.text.SimpleDateFormat;

import twitter4j.Status;

public class TimelineTweet {
	
	private Status _status;
	
	public TimelineTweet(Status status) {
		_status = status;
	}
	
	public Status getStatus() {
		return _status;
	}
	public String getUserName()	{
		return _status.getUser().getName();
	}
	
	public String getUserScreenName()	{
		return _status.getUser().getScreenName();
	}
	public String getText()	{
		return _status.getText();
	}
	
	public URL getIconURL(){
		return _status.getUser().getProfileImageURL();
	}
	
	public String getTime(){
		return (new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy")).format(_status.getCreatedAt());  
	}
	
	public String toString() {
	    return("<html>"+getUserName() + " - " + "@" +  getUserScreenName() + "  " + getTime() + "<br>" +"<p style=\"width:300px; font-size:8px;\">" + getText()+ "</p>"+"</html>");
	 }
}
