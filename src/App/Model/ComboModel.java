package App.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Custom ComboModel
 * @author Wojciech Niemiec
 * @version 1.0.0
 * Date: 2017.05.10
 *
 */
public class ComboModel implements ComboBoxModel<String> {

	private List<String> data;
	private String selected;
	private List<ListDataListener> listeners; 
	
	/**
	 * Constructs a combo presenting the data from the parameter
	 * @param data - content to be putted into the combo
	 */
	public ComboModel(String[] data) {
		this.data = Arrays.asList(data);
		listeners = new ArrayList<>();
		selected = data[0];
	}
	
	/**
	 * Adds listeners
	 */
	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	/**
	 * Returns specified value
	 * @return selected value
	 */
	@Override
	public String getElementAt(int index) {
		return data.get(index);
	}

	/**
	 * Returns number of elements in combo
	 * @return number of elements
	 */
	@Override
	public int getSize() {
		return data.size();
	}

	/**
	 * Removes specified listener
	 * @param l - listener to be removed
	 */
	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	/**
	 * returns item marced as selected
	 * @return selected value
	 */
	@Override
	public String getSelectedItem() {
		return selected;
	}

	/**
	 * Marks an item as selected
	 * @param anItem - item to be marked
	 */
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
