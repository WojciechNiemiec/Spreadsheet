package App.View;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import App.Model.DoubleTableModel;

public class DoubleTable extends JTable{
	private static final long serialVersionUID = 1L;
	
	private DoubleTableModel tableModel;
	
	public DoubleTable(DoubleTableModel tableModel) {
		super(tableModel);
		this.tableModel = tableModel;
		
		setFillsViewportHeight(true);
		setRightRendering();
	}
	
	public DoubleTableModel getTableModel() {
		return tableModel;
	}
	
	private void setRightRendering() {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}
	}
}
