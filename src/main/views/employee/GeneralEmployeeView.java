package main.views.employee;

import main.models.Employee;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec GeneralEmployeeView is an AbstractEmployeeView with basic functionality
 *
 */
public class GeneralEmployeeView extends AbstractEmployeeView {


	private static final long serialVersionUID = 1L;

	public GeneralEmployeeView(Employee emp) {
		setEmployee(emp);
		setTitle("Employee Details");
		initialize();
	}

}
