package App.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

import javax.swing.table.AbstractTableModel;

public class DoubleTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	String[] names;
	List<List<Double>> data;

	public DoubleTableModel(String[] names, Double[][] data) {
		this.names = names;
		this.data = new ArrayList<>();
		
		for (int i = 0; i < data.length; i++) {
			this.data.add(new ArrayList<>());
			for (int j = 0; j < data[i].length; j++) {
				this.data.get(i).add((data[i][j] != null) ? data[i][j] : 0.0);
			}
		}
	}

	public Double average() {
		return getDataStream().average().getAsDouble();
	}

	public Double amount() {
		return getDataStream().sum();
	}

	public Double min() {
		return getDataStream().min().getAsDouble();
	}

	public Double max() {
		return getDataStream().max().getAsDouble();
	}

	public void reset() {
		fillWith(() -> Double.valueOf(0));
	}

	public void random() {
		fillWith(() -> Math.floor(Math.random() * 10000) / 100);
	}
	
	private void fillWith(Supplier<Double> supplier) {
		for (int y = 0; y < data.size(); y++) {
			for (int x = 0; x < data.get(y).size(); x++) {
				data.get(y).set(x, supplier.get());
			}
		}
		fireTableDataChanged();
	}

	private DoubleStream getDataStream() {
		return data.stream()
				.flatMap(e-> e.stream())
				.filter(e -> e != null)
				.mapToDouble(e -> e);
	}

	@Override
	public int getColumnCount() {
		return names.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Double retval = data.get(rowIndex).get(columnIndex);
		return (retval != 0.0) ? retval : "0";
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data.get(rowIndex).set(columnIndex, Double.parseDouble(aValue.toString()));
		fireTableDataChanged();
	}
};