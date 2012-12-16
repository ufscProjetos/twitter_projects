package Grupo3.BlueBird.igu.timeline;

import javax.swing.*;

import Grupo3.BlueBird.logica.timeline.TimelineTweet;

import java.awt.*;
import java.net.URL;
import java.util.*;

public class TimelineRenderer extends DefaultListCellRenderer {
	private Hashtable<Object, ImageIcon> _iconTable = new Hashtable<Object, ImageIcon>();

	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean hasFocus) {
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value,
				index, isSelected, hasFocus);
		if (value instanceof TimelineTweet) {
			TimelineTweet status = (TimelineTweet) value;
			ImageIcon icon = (ImageIcon) _iconTable.get(value);
			if (icon == null) {
				URL url = status.getIconURL();
				try{
					icon = new ImageIcon(url);
				}catch (Exception e) {
					icon = new ImageIcon(getClass().getResource("/imagens/img_padrao.png"));
				}
				_iconTable.put(value, icon);
			}
			label.setIcon(icon);
		} else {
			label.setIcon(null);
		}
		return (label);

	}
}
