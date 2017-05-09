package App;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.DoubleSummaryStatistics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JMenu fileMenu;
	private JMenu optionsMenu;
	private JMenu helpMenu;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JPanel statusBar;
	private JLabel statusLabel;
	private FrontPanel panelHandler;
	
	private void initMenu() {
		menuBar = new JMenuBar();
		
		initFileMenu();
		initOptionsMenu();
		initHelpMenu();
		
		menuBar.add(fileMenu);
		menuBar.add(optionsMenu);
		menuBar.add(helpMenu);
	}

	private void initFileMenu() {
		JMenuItem item;
		fileMenu = new JMenu("File");
		
		item = new JMenuItem("New table");
		item.addActionListener(e->{
			clearTable();
		});
		fileMenu.add(item);
		item = new JMenuItem("Fill table with randoms");
		item.addActionListener(e->{
			randomTable();
		});
		fileMenu.add(item);
		item = new JMenuItem("Save table");
		item.addActionListener(e->{
			panelHandler.saveToFile();
		});
		fileMenu.add(item);
	}

	private void initOptionsMenu() {
		JMenuItem item;
		optionsMenu = new JMenu("Options");
		
		item = new JMenuItem("Find min and max");
		item.addActionListener(e->{
			findMinMax();
		});
		optionsMenu.add(item);
		item = new JMenuItem("Count total amount");
		item.addActionListener(e->{
			findAmount();
		});
		optionsMenu.add(item);
		item = new JMenuItem("Count average");
		item.addActionListener(e->{
			findAverage();
		});
		optionsMenu.add(item);
	}
	
	private void initHelpMenu() {
		JMenuItem item;
		helpMenu = new JMenu("Help");
		
		item = new JMenuItem("Show help box");
		item.addActionListener(e->{
			showHelpDialog();
		});
		helpMenu.add(item);
		
		item = new JMenuItem("Show info about author");
		item.addActionListener(e->{
			showAuthorDialog();
		});
		helpMenu.add(item);
	}
	
	public void clearTable() {
		panelHandler.setForEachCell(()->null);
		panelHandler.updateResultArea();
		statusLabel.setText("Table cleared");
	}

	public void randomTable() {
		panelHandler.setForEachCell(()->Math.floor(Math.random() * 1000) / 10);
		panelHandler.updateResultArea();
		statusLabel.setText("Table filled with random numbers");
	}

	public void findAverage() {
		panelHandler.getResultArea().setText("Average is: " + panelHandler.getSummaryStatistics().getAverage());
		statusLabel.setText("Average found");
	}

	public void findAmount() {
		panelHandler.getResultArea().setText("Amount is: " + panelHandler.getSummaryStatistics().getSum());
		statusLabel.setText("Total amount found");
	}

	public void findMinMax() {
		DoubleSummaryStatistics stats = panelHandler.getSummaryStatistics();
		panelHandler.getResultArea().setText("Min is: " + stats.getMin() + ", max is: " + stats.getMax());
		statusLabel.setText("Min and max found");
	}

	public void showHelpDialog() {
		new HelpWindow().setLocationRelativeTo(Window.this);
		statusLabel.setText("Help displayed");
	}

	public void showAuthorDialog() {
		new InfoWindow().setLocationRelativeTo(Window.this);
		statusLabel.setText("Information about author displayed");
	}
	
	private void initStatusBar() {
		statusLabel = new JLabel("Application start");
		
		statusBar = new JPanel();
		statusBar.add(statusLabel);
		statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
	}
	
	private void initToolBar() {
		toolBar = new JToolBar();
		JButton button = null;
		
		button = new JButton(new ImageIcon("src/Icons/save.png"));
		button.addActionListener(e -> {
			panelHandler.saveToFile();
		});
		toolBar.add(button);
		
		button = new JButton(new ImageIcon("src/Icons/shuffle.png"));
		button.addActionListener(e->{
			randomTable();
		});
		toolBar.add(button);
		
		button = new JButton(new ImageIcon("src/Icons/clear.png"));
		button.addActionListener(e->{
			panelHandler.setForEachCell(()->Double.valueOf(0));
			panelHandler.updateResultArea();
		});
		toolBar.add(button);
		
		button = new JButton(new ImageIcon("src/Icons/minmax.png"));
		button.addActionListener(e -> {
			findMinMax();
		});
		toolBar.add(button);
		
		button = new JButton(new ImageIcon("src/Icons/sum.png"));
		button.addActionListener(e -> {
			findAmount();
		});
		toolBar.add(button);
		
		button = new JButton(new ImageIcon("src/Icons/average.png"));
		button.addActionListener(e -> {
			findAverage();
		});
		toolBar.add(button);
		
		button = new JButton(UIManager.getIcon("OptionPane.informationIcon"));
		button.addActionListener(e-> {
			showAuthorDialog();
		});
		toolBar.add(button);

		button = new JButton(UIManager.getIcon("OptionPane.questionIcon"));
		button.addActionListener(e->{
			showHelpDialog();
		});
		toolBar.add(button);
	}
	
	private void setCustomCloseOperation() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Window.this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new ClosingWindow(Window.this);
			}
		});
	}
	
	public Window() {
		initMenu();
		initStatusBar();
		initToolBar();
		setCustomCloseOperation();
		
		panelHandler = new FrontPanel();
		panelHandler.setStatusLabel(statusLabel);
		
		super.setLayout(new BorderLayout());
		super.setJMenuBar(menuBar);
		super.add(statusBar, BorderLayout.SOUTH);
		super.add(toolBar, BorderLayout.PAGE_START);
		super.getContentPane().add(panelHandler);
		super.setSize(panelHandler.getSize().width, panelHandler.getSize().height + 70);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setVisible(true);
	}
}
