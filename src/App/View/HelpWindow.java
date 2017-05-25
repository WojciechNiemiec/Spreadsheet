package App.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

/**
 * This class is responsible for presenting help message dialog.
 * @author Wojciech Niemiec
 * @version 1.0.0
 * Date: 2017.05.10
 *
 */
public class HelpWindow extends JDialog {
	/**
	 * A version from serializable.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logs the informations
	 */
	static Logger log = Logger.getLogger(HelpWindow.class);
	
	/*
	 * Creates window with simple how to use information.
	 */
	public HelpWindow() {
		log.info("Help presented");
		
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setSize(new Dimension(400, 600));

		super.setLayout(new BorderLayout());
		super.setTitle("Help");
		JLabel label = new JLabel("<html><body style=\"padding:20px;text-align: justify\">"
				+ "<div>"
				+ "<h1>Help</h1>"
					+ "<ol>"
						+ "<li>"
						+ "<h3>Data input</h3>"
							+ "<ul>"
								+ "<li>Put data inside input box</li>"
								+ "<li>Ensure that box is not empty and the value is a number</li>"
								+ "<li>Select the row and column next that box</li>"
								+ "<li>Click add</li>"
							+ "</ul>"
						+ "</li>"
						+ "<li>"
						+ "<h3>Data manipulation</h3>"
							+ "<ul>"
								+ "<li>Select operation from the combobox</li>"
								+ "<li>Each change made on table will be visible on the result box</li>"
								+ "<li>You can also choose icon from toolbar to archive your goal</li>"
							+ "</ul>"
						+ "</li>"
						+ "<li>"
						+ "<h3>Saving</h3>"
							+ "<ul>"
								+ "<li>Click \"save\" or disc icon to save table to file</li>"
								+ "<li>Data will be saved to file.txt</li>"
								+ "<li>Everything is also availible from menu bar</li>"
							+ "</ul>"
						+ "</li>"
					+ "</ol>"
				+ "</div>"
				+ "</body></html>");
		super.add(label);
		super.setVisible(true);
	}
}
