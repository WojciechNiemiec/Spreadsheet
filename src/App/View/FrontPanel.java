package App.View;

import App.Controller.*;
import App.Model.*;


import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.apache.log4j.Logger;
import org.freixas.jcalendar.JCalendarCombo;

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

/**
 * This class defines the panel in the middle of an application witch
 * contains majority of the application content.
 * @author Wojciech Niemiec
 * @version 1.0.0
 * Date: 2017.05.10
 *
 */
public class FrontPanel extends JPanel {
	/**
	 * Version from serializable.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Logs the informations
	 */
	static Logger log = Logger.getLogger(FrontPanel.class);
	/**
	 * Default height for a label.
	 */
	private static final int LabelHeight = 15;
	/**
	 * Default height for a button.
	 */
	private static final int buttonHeight = 30;
	/**
	 * Default width for a button.
	 */
	private static final int buttonWidth = 100;
	
	private JTextField inputField;
	private JTextArea resultArea;
	private JSpinner spinnerX;
	private JSpinner spinnerY;
	private DoubleTable table;
	private DoubleTableModel tableModel;
	private JButton clearButton;
	private JButton addButton;
	private JButton saveButton;
	private JButton randButton;
	private JLabel statusLabel;
	private JComboBox<String> comboBox;
	
	private JTaskPane taskPane;
	private JCalendarCombo calendar;
	
	/**
	 * Initializes the JCalendarCombo component and defines actions
	 * for it.
	 */
	public void initCalendar() {
		calendar = new JCalendarCombo();
		calendar.getModel().addListDataListener(new ListDataListener() {
			
			@Override
			public void intervalRemoved(ListDataEvent e) {}
			
			@Override
			public void intervalAdded(ListDataEvent e) {}
			
			@Override
			public void contentsChanged(ListDataEvent e) {
				Object selected = calendar.getModel().getSelectedItem();
				resultArea.setText(selected.toString());
			}
		});
		
		log.info("Calendar initialized");
	}
	
	/**
	 * Initializes the JTaskPane component.
	 */
	private void initTaskPane() {
		JButton button;
		JTaskPaneGroup group;
		taskPane = new JTaskPane();
		
		group = new JTaskPaneGroup();
		group.setTitle("Operations");
		
		button = new JButton("Clear");
		button.addActionListener(e -> table.getTableModel().reset());
		group.add(button);
		taskPane.add(group);
		taskPane.setVisible(true);
		
		log.info("Task pane initialized");
	}
	
	/**
	 * Initializes the spinner.
	 */
	private void initSpinners() {
		Integer[] numbers = {1, 2, 3, 4, 5};
		spinnerX = new JSpinner(new SpinnerListModel(numbers));
		spinnerY = new JSpinner(new SpinnerListModel(numbers));
		
		log.info("Spinners initialized");
	}

	/**
	 * Initializes the combo box.
	 */
	private void initComboBox() {
		String[] data = {"Amount", "Average", "Min", "Max"};
		ComboModel model = new ComboModel(data);
		ComboController controller = new ComboController(()->updateResultArea());
		model.addListDataListener(controller);
		comboBox = new JComboBox<>(model);
		
		log.info("Combobox initialized");
	}
	
	/**
	 * Defines the data to present on result area depending on selected option
	 */
	public void updateResultArea() {
		String selector = (String)comboBox.getSelectedItem();
		String text;
		
		if (selector.equals("Amount")) {
			text = "Amount is: " + tableModel.amount();
		} else if (selector.equals("Average")) {
			text = "Average is: " + tableModel.average();
		} else if (selector.equals("Min")) {
			text = "Min is: " + tableModel.min();
		} else if (selector.equals("Max")) {
			text = "Max is: " + tableModel.max();
		} else {
			text = "";
		}
		
		resultArea.setText(text);
		log.info("Result area updated with: " + text);
	}

	/**
	 * Returns a handle to Result area.
	 * @return a handle to Result area
	 */
	public JTextArea getResultArea() {
		return resultArea;
	}
	
	/**
	 * Initializes the table component with DoubleTableModel and five columns.
	 */
	private void initTable() {
		String[] names = {"Column 1", "Column 2", "Column 3", "Column 4", "Column 5"};
		Double[][] data = new Double[5][5];
		tableModel = new DoubleTableModel(names, data);
		table = new DoubleTable(tableModel);
		table.setFillsViewportHeight(true);
		
		log.info("table initialized");
	}
	
	/**
	 * Returns a table model to get access to data and data manipulation methods
	 * @return a handle to tableModel
	 */
	public DoubleTableModel getTableModel() {
		if (tableModel == null)
			log.error("Uninitilized variable return");
		else
			log.info("table model returned");
		
		return tableModel;
	}

	/**
	 * Initializes the clear button with its action.
	 */
	private void initClearButton() {
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getTableModel().reset();
				updateResultArea();
				statusLabel.setText("Table is clear");
			}
		});
		
		log.info("Clear button initialized");
	}
	
	/**
	 * Initializes the rundom button with its action.
	 */
	private void initRandButton() {
		randButton = new JButton("Random");
		randButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getTableModel().random();
				updateResultArea();
				statusLabel.setText("Table filled with random numbers");
			}
		});
	}

	/**
	 * Initializes the add button with its action.
	 */
	private void initAddButton() {
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int x = Integer.parseInt(spinnerX.getValue().toString()) - 1;
					int y = Integer.parseInt(spinnerY.getValue().toString()) - 1;
					double value = Double.parseDouble(inputField.getText());
					
					table.getModel().setValueAt(value, x, y);
					statusLabel.setText("Value " + value + " has been added to column: " + (x + 1)
							+ ", row: " + (y + 1));
					
					log.info("Result area updated");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(addButton, "Incorrect value");
					log.error("Number format exception");
				} finally {
					updateResultArea();
				}
			}
		});
		
		log.info("Add button initialized");
	}

	/**
	 * Initializes the save button with its action: saving the data.
	 */
	private void initSaveButton() {
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveToFile();
				statusLabel.setText("Data saved");
			}
		});
		
		log.info("Save button initialized");
	}
	
	/**
	 * Runs the table saving process.
	 */
	public void saveToFile() {
		try {
			String fileName = "file.txt";
			JFileChooser chooser = new JFileChooser();
		    int returnVal = chooser.showSaveDialog(FrontPanel.this);
		    if(returnVal == JFileChooser.SAVE_DIALOG) {
		            fileName = chooser.getSelectedFile().getName();
		    }
		    File file = new File(fileName);
			if (file.exists() == false) {
				file.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(table.getModel().toString());
			writer.close();
			
			log.info("Saved to file");
		} catch (IOException ex) {
			ex.printStackTrace();
			log.error("IOException");
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			log.error("Exception " + ex.getMessage());
		}
	}
	
	/**
	 * Sets the status label.
	 * @param statusLabel - label to be set
	 */
	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
		log.info("status label setted");
	}

	/**
	 * Runs all ini methods and creates a new instance of FrontPanel.
	 */
	public FrontPanel() {
		super.setSize(new Dimension(750, 290));
		super.setLayout(null);
		super.setBackground(new Color(210, 210, 220));
		initTable();
		initSpinners();
		initComboBox();
		initClearButton();
		initAddButton();
		initSaveButton();
		initRandButton();
		initTaskPane();
		initCalendar();
		
		calendar.setBounds(new Rectangle(560, 30, 170, 30));
		super.add(calendar);
		
		taskPane.setBounds(new Rectangle(560, 80, 170, 130));
		super.add(taskPane);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(new Rectangle(10, 60, 400, 103));
		super.add(scrollPane);
		
		JLabel label = new JLabel("Input");
		label.setBounds(new Rectangle(10, 15, 150, LabelHeight));
		super.add(label);
		
		getTableModel().reset();
		inputField = new JTextField();
		inputField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		inputField.setBounds(10, 30, 150, 20);
		super.add(inputField);
		label = new JLabel("Row");
		label.setBounds(new Rectangle(205, 15, 50, LabelHeight));
		super.add(label);
		label = new JLabel("Column");
		label.setBounds(new Rectangle(330, 15, 50, LabelHeight));
		super.add(label);
		spinnerX.setBounds(new Rectangle(205, 30, 80, 20));
		super.add(spinnerX);
		spinnerY.setBounds(new Rectangle(330, 30, 80, 20));
		super.add(spinnerY);
		label = new JLabel("Result");
		label.setBounds(new Rectangle(205, 165, 150, LabelHeight));
		super.add(label);
		resultArea = new JTextArea();
		resultArea.setBounds(205, 180, 205, buttonHeight);
		resultArea.setEditable(false);
		super.add(resultArea);
		label = new JLabel("Choose operation");
		label.setBounds(new Rectangle(10, 165, 150, LabelHeight));
		super.add(label);
		comboBox.setBounds(new Rectangle(10, 180, 150, 30));
		super.add(comboBox);
		
		addButton.setBounds(440, 30, buttonWidth, buttonHeight);
		super.add(addButton);
		clearButton.setBounds(440, 80, buttonWidth, buttonHeight);
		super.add(clearButton);
		randButton.setBounds(440, 130, buttonWidth, buttonHeight);
		super.add(randButton);
		saveButton.setBounds(440, 180, buttonWidth, buttonHeight);
		super.add(saveButton);
		
		table.setVisible(true);
		
		log.info("Front panel setted");
	}
}
