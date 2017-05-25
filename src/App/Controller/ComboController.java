package App.Controller;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Custom controller for ComboBox
 * @author Wojciech Niemiec
 * @version 1.0.0
 * Date: 2017.05.25
 *
 */
public class ComboController implements ListDataListener {
	
	private Function function;
	
	/**
	 * Sets function
	 * @param function - a function to be executed
	 */
	public ComboController(Function function) {
		this.function = function;
	}
	
	/**
	 * Runs the fucntion when contents changed
	 * @param e - event
	 */
	@Override
	public void contentsChanged(ListDataEvent e) {
		function.apply();
	}

	/**
	 * Does nothing
	 */
	@Override
	public void intervalAdded(ListDataEvent e) { }

	/**
	 * Does nothing
	 */
	@Override
	public void intervalRemoved(ListDataEvent e) { }

}
