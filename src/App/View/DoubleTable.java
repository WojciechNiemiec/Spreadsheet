package App.View;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import App.Model.DoubleTableModel;

/**
 * This class defines the Custom table for presenting double values.
 * @author Wojciech Niemiec
 * @version 1.0.0
 * Date: 2017.05.10
 * 
 */
public class DoubleTable extends JTable{
	/**
	 * A version from serializable.
	 */
	private static final long serialVersionUID = 1L;
	
	private DoubleTableModel tableModel;
	
	/**
	 * Constructs the table with specified tableModel
	 * @param tableModel: instance of DoubleTableModel
	 */
	public DoubleTable(DoubleTableModel tableModel) {
		super(tableModel);
		this.tableModel = tableModel;
		
		setFillsViewportHeight(true);
		setRightRendering();
	}
	
	/**
	 * Returns a handle to DoubleTableModel witch defines all data manipulating methods
	 * @return a handle to DoubleTableModel
	 */
	public DoubleTableModel getTableModel() {
		return tableModel;
	}
	
	/**
	 * Sets cell rendering to the right.
	 */
	private void setRightRendering() {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}
	}
}
