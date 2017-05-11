package App.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class ComboModel implements ComboBoxModel<String> {

	private List<String> data;
	private String selected;
	private List<ListDataListener> listeners; 
	
	public ComboModel(String[] data) {
		this.data = Arrays.asList(data);
		listeners = new ArrayList<>();
		selected = data[0];
	}
	
	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	@Override
	public String getElementAt(int index) {
		return data.get(index);
	}

	@Override
	public int getSize() {
		return data.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	@Override
	public String getSelectedItem() {
		return selected;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		for (String element : data) {
			if (element.equals(anItem)) {
				selected = element;
			}
		}
		
		for (ListDataListener l : listeners) {
			l.contentsChanged(new ListDataEvent(0, 0, 0, 0));
		}
	}

}
