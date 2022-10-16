package main.views.factories;

import main.models.Employee;
import main.views.employee.AbstractEmployeeFrame;
import main.views.employee.AddEmployeeFrame;
import main.views.employee.EditEmployeeFrame;
import main.views.employee.GeneralViewEmployeeFrame;
import main.views.employee.HumanResourceViewEmployeeFrame;
import main.views.home.AbstractHomeFrame;

public class EmployeeFrameFactory {
	
	public static AbstractEmployeeFrame createViewEmployeeFrame(AbstractHomeFrame view, Employee emp) {
		if(view.getClass().toString().equals("class main.views.home.GeneralHomeFrame") && emp != null) {
			return new GeneralViewEmployeeFrame(emp);
		}else if(view.getClass().toString().equals("class main.views.home.HumanResourceHomeFrame")) {
			return new HumanResourceViewEmployeeFrame(emp);
		}
		return null;
	}
	
	public static EditEmployeeFrame createEditEmployeeFrame(Employee emp) {
		if(emp != null) {
			return new EditEmployeeFrame(emp);
		}
		return null;
	}

	public static AddEmployeeFrame createAddEmployeeFrame() {
//		
		return new AddEmployeeFrame();
	}
}
