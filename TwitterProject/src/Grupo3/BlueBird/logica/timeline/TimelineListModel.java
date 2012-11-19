package Grupo3.BlueBird.logica.timeline;
import javax.swing.ListModel;
import javax.swing.event.*;

public class TimelineListModel implements ListModel<Object> {
  private TweetedCollection _collection;
  
  public TimelineListModel(TweetedCollection collection) {
	  _collection = collection;
  }

  public Object getElementAt(int index) {
    return(_collection.getCollection()[index]);
  }

  public int getSize() {
    return(_collection.getNumCollection());
  }
  
  public void setCollection(TweetedCollection collection) {
	  _collection = collection;
  }

  public void addListDataListener(ListDataListener l) {}

  public void removeListDataListener(ListDataListener l) {}
}
