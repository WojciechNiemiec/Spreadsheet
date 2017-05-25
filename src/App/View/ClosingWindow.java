package App.View;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

/**
 * This class defines the closing Window witch asks a client if he is shure he wants to close.
 * The calling window becoms disabled while this Window is presenting.
 * @author Wojciech Niemiec
 * @version 1.0.0
 * Date: 2017.05.10
 *
 */
public class ClosingWindow extends JFrame {

	/**
	 * A version from serializable.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logs the informations
	 */
	static Logger log = Logger.getLogger(ClosingWindow.class);
	
	JFrame windowHandler;
	
	/**
	 * Defines the content of window with the closing question and appropirate buttons.
	 * @param windowHandler - a handle to JFrame
	 */
	public ClosingWindow(JFrame windowHandler) {
		super.frameInit();
		super.setLayout(null);
		
		log.info("Showing window");
		
		JLabel label = new JLabel();
		JButton button;
		label.setText("<html><h1><center>Warning</center></h1>"
				+ "<p><center>Are you sure that you want to close this application? "
				+ "Any unsaved changes will be lost</center></p>"
				+ "</html>");
		label.setBounds(new Rectangle(20, 20, 200, 200));
		super.add(label);
		button = new JButton("Yes");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("Closing with success");
				
				System.exit(NORMAL);
			}
		});
		button.setBounds(new Rectangle(20, 220, 90, 30));
		super.add(button);
		button = new JButton("No");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("Going back to application");
				
				ClosingWindow.this.setVisible(false);
				windowHandler.setEnabled(true);
				ClosingWindow.this.dispose();
			}
		});
		button.setBounds(new Rectangle(130, 220, 90, 30));
		super.add(button);
		setSize(260, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setWindowHandler(windowHandler);
		windowHandler.setEnabled(false);
		super.setResizable(false);
		setVisible(true);
	}

	/**
	 * Sets handle to a JFrame and sets position relatively to the center of specified JFrame
	 * @param windowHandler - a JFrame used for positioning
	 */
	private void setWindowHandler(JFrame windowHandler) {
		setLocationRelativeTo(windowHandler);
		this.windowHandler = windowHandler;
	}
}