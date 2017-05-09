package App;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ClosingWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	JFrame windowHandler;
	
	public ClosingWindow(JFrame windowHandler) {
		super.frameInit();
		super.setLayout(null);
		
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
				System.exit(NORMAL);
			}
		});
		button.setBounds(new Rectangle(20, 220, 90, 30));
		super.add(button);
		button = new JButton("No");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
	
	private void setWindowHandler(JFrame windowHandler) {
		setLocationRelativeTo(windowHandler);
		this.windowHandler = windowHandler;
	}
}