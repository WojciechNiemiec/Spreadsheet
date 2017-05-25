package App.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

import javax.swing.table.AbstractTableModel;

/**
 * Custom TableModel class optimalized for presenting and manipulating Double values. This class
 * defines counting and filling table methods.
 * @author Wojciech Niemiec
 * @version 1.0.0
 * Date: 2017.05.10
 *
 */
public class DoubleTableModel extends AbstractTableModel {
	/**
	 * A version from serializable
	 */
	private static final long serialVersionUID = 1L;

	String[] names;
	List<List<Double>> data;

	/**
	 * Constructs table on specified names and data
	 * @param names - column names
	 * @param data - 2D array with data
	 */
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

	/**
	 * Counts average from each cell in table.
	 * @return average
	 */
	public Double average() {
		return getDataStream().average().getAsDouble();
	}

	/**
	 * Counts amount from each cell in table.
	 * @return amount
	 */
	public Double amount() {
		return getDataStream().sum();
	}

	/**
	 * Finds minimal number from each cell in table.
	 * @return minimal value
	 */
	public Double min() {
		return getDataStream().min().getAsDouble();
	}

	/**
	 * Finds maximal number from each cell in table.
	 * @return maximal value
	 */
	public Double max() {
		return getDataStream().max().getAsDouble();
	}

	/**
	 * Fills table with zeros.
	 */
	public void reset() {
		fillWith(() -> Double.valueOf(0));
	}

	/**
	 * Fills table with random numbers.
	 */
	public void random() {
		fillWith(() -> Math.floor(Math.random() * 10000) / 100);
	}
	
	/**
	 * Goes to each cell of the table and inputs the value returned from supplier
	 * @param supplier - a functional interface to inject values to table. Allows to
	 * use lambda expressions.
	 */
	private void fillWith(Supplier<Double> supplier) {
		for (int y = 0; y < data.size(); y++) {
			for (int x = 0; x < data.get(y).size(); x++) {
				data.get(y).set(x, supplier.get());
			}
		}
		fireTableDataChanged();
	}

	/**
	 * Streams a one division double stream from the data table to allow to use
	 * fast java 1.8 stream operations. 
	 * @return 1D double data stream
	 */
	private DoubleStream getDataStream() {
		return data.stream()
				.flatMap(e-> e.stream())
				.filter(e -> e != null)
				.mapToDouble(e -> e);
	}

	/**
	 * Returns number of columns in table.
	 * @return number of columns in table
	 */
	@Override
	public int getColumnCount() {
		return names.length;
	}

	/**
	 * Returns number of rows in table.
	 * @return number of rows in table
	 */
	@Override
	public int getRowCount() {
		return data.size();
	}

	/**
	 * Returns value from specified cell. Zero is returned as a String becouse double representation
	 * suggests specific format like: 0.0
	 * @param rowIndex - index of row
	 * @param columnIndex - index of column
	 * @return value under selected indexes
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Double retval = data.get(rowIndex).get(columnIndex);
		return (retval != 0.0) ? retval : "0";
	}
	
	/**
	 * Sets a specified value under specified cell
	 * @param aValue - value to be putted in selected cell
	 * @param rowIndex - index of row
	 * @param columnIndex - index of column
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data.get(rowIndex).set(columnIndex, Double.parseDouble(aValue.toString()));
		fireTableDataChanged();
	}
};