package Grupo3.BlueBird.logica.timeline;

import java.net.URL;

import javax.swing.ImageIcon;

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
	
	public ImageIcon getIcon(){
		URL url = _status.getUser().getProfileImageURL();
		ImageIcon icon;
		try{
			icon = new ImageIcon(url);
		}catch (Exception e) {
			icon = new ImageIcon(getClass().getResource("/imagens/img_padrao.png"));
		}
		return icon;
	}
	
	public String toString() {
	    return("<html>"+getUserName() + " - " + "@" +  getUserScreenName() + "<br>" + "<p style=\"width:300px; font-size:8px;\">" + getText()+ "</p>"+"</html>");
	  }
}
