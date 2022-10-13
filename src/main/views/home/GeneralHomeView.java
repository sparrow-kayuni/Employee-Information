package main.views.home;

import main.views.employee.GeneralEmployeeView;

public class GeneralHomeView extends AbstractHomeView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the application.
	 */
	public GeneralHomeView() {
		setResizable(false);

		employeeDetailsView = (GeneralEmployeeView) employeeDetailsView;
		setTitle("Employee Information System");
		initialize();
	}

}
