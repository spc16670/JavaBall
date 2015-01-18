//import the DataSource file from the n.db package which contains the list of Referee objects
import n.db.DataSource;
import n.gui.Container;

/**
 * This is the main method which instantiates a DataSource object and the main 
 * Gui class
 */
public class Main {
	public static void main(String[] args) {
		//DataSource object is instantiated with the reference name fileStore
		DataSource dataSource = new DataSource();
		
		/* Container object is instantiated with the identifier 'gui', this has been created 
		 * within an invoke later method which ensures that any changes to be made to the GUI
		 * on the EDT to ensure thread safety
		 */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Container gui = new Container(dataSource);
              //set the gui visibility to true as soon as the program runs
                gui.setVisible(true);
            }
        });
	}
}
