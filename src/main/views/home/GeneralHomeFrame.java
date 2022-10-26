package main.views.home;

import main.views.employee.GeneralViewEmployeeFrame;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec GeneralHomeFrame is an implementation of AbstractHomeFrame for basic use  
 *
 */
public class GeneralHomeFrame extends AbstractHomeFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the application.
	 */
	public GeneralHomeFrame() {
		viewEmployeeFrame = (GeneralViewEmployeeFrame) viewEmployeeFrame;
		setTitle("Employee Information");
		initialize();
	}

}
