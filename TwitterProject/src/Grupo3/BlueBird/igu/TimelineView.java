package Grupo3.BlueBird.igu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import twitter4j.Status;

public class TimelineView {
	
	private Container _container;
	private JTextArea _textArea;
	
	public TimelineView(int linha, int coluna) {
		
		_container = new JPanel(new BorderLayout());
		_textArea = new JTextArea(linha, coluna);
		JScrollPane scrollPane = new JScrollPane(_textArea);
		_container.add(scrollPane, BorderLayout.CENTER);
		_textArea.setEditable(false);
	}
	
	public Container getContainer(){
		return _container;
	}

	public void printStatus(List<Status> statuses){
		_textArea.setEditable(true);
		_textArea.setText("");
		for (Status status : statuses) {
          _textArea.append("@" + status.getUser().getScreenName() + "\n");
          String text = status.getText();
          int limite = 50;
          int limite2 = 100;
          if (text.length() < 50) {
        	  _textArea.append( text + "\n\n");
          }else if (text.length() < 100){
        	  while(text.substring(limite-1,limite).compareTo(" ") != 0){
        		  limite--;
        	  }
        	  _textArea.append( text.substring(0,limite) + "\n");
        	  _textArea.append( text.substring(limite,text.length()-1) + "\n\n");
          }else {
        	  while(text.substring(limite-1,limite).compareTo(" ") != 0){
        		  limite--;
        	  }
        	  while(text.substring(limite2-1,limite2).compareTo(" ") != 0){
        		  limite2--;
        	  }
        	  _textArea.append( text.substring(0,limite) + "\n");
        	  _textArea.append( text.substring(limite,limite2) + "\n");
        	  _textArea.append( text.substring(limite2,text.length()-1) + "\n\n");
          }
		}
		_textArea.setEditable(false);
	}
}
