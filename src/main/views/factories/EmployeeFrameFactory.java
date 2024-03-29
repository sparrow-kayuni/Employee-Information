package main.views.factories;

import main.models.Employee;
import main.views.employee.AbstractEmployeeFrame;
import main.views.employee.AddEmployeeFrame;
import main.views.employee.EditEmployeeFrame;
import main.views.employee.GeneralViewEmployeeFrame;
import main.views.employee.HumanResourceViewEmployeeFrame;
import main.views.home.AbstractHomeFrame;
import main.views.home.GeneralHomeFrame;
import main.views.home.HumanResourceHomeFrame;

public class EmployeeFrameFactory {
	
	public static AbstractEmployeeFrame createViewEmployeeFrame(AbstractHomeFrame view, Employee emp) {
		if(view instanceof GeneralHomeFrame && emp != null) {
			return new GeneralViewEmployeeFrame(emp);
		}else if(view instanceof HumanResourceHomeFrame) {
			return new HumanResourceViewEmployeeFrame(emp);
		}
		return null;
	}
	
	public static HumanResourceViewEmployeeFrame returnToViewEmployeeFrame(Employee emp) {
		return new HumanResourceViewEmployeeFrame(emp);
	}
	
	
	public static EditEmployeeFrame createEditEmployeeFrame(Employee emp) {
		if(emp != null) {
			return new EditEmployeeFrame(emp);
		}
		return null;
	}

	public static AddEmployeeFrame createAddEmployeeFrame(String deptName) {
//		
		return new AddEmployeeFrame(deptName);
	}
}
