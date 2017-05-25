package App.View;

import org.apache.log4j.Logger;


/**
 * This class defines the entry point of the application. It is used
 * to open a new instance of a App.View.Window class.
 * @author Wojciech Niemiec
 * @version 1.0.0
 * Date: 2017.05.25
 *
 */
public class WindowLauncher {
	
	/**
	 * Logs the informations
	 */
	static Logger log = Logger.getLogger(WindowLauncher.class);
	
	/**
	 * Runs the application
	 * @param args - arguments from CLI
	 */
	public static void main(String[] args) {		
		log.info("application start");
		
		new Window();
	}
}

//FTP 62.108.181.229 p21 l:student p:student
//682 lines