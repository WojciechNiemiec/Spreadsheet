package App.Controller;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class ComboController implements ListDataListener {
	
	private Function function;
	
	public ComboController(Function function) {
		this.function = function;
	}
	
	@Override
	public void contentsChanged(ListDataEvent e) {
		function.apply();
	}

	@Override
	public void intervalAdded(ListDataEvent e) { }

	@Override
	public void intervalRemoved(ListDataEvent e) { }

}
