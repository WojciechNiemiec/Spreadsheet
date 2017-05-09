package App;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

class MyTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public String toString() {
		return this.getDataVector().toString();
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Double> getData() {
		Vector<Double> output = new Vector<>();
		for (Vector<Double> i : (Vector<Vector<Double>>)getDataVector()) {
			output.addAll(i);
		}
		return output;
	}
	
	public MyTableModel(Object[][]data, Object[] names) {
		super(data, names);
	}
};