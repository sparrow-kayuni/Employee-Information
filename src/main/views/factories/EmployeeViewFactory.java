package main.views.factories;

import main.models.Employee;
import main.views.employee.AbstractEmployeeView;
import main.views.employee.GeneralEmployeeView;
import main.views.employee.HumanResourceEmployeeView;
import main.views.home.AbstractHomeView;

public class EmployeeViewFactory {
	
	public static AbstractEmployeeView createEmployeeView(AbstractHomeView view, Employee emp) {
		if(view.getClass().toString().equals("class main.views.home.GeneralHomeView")) {
			return new GeneralEmployeeView(emp);
		}else if(view.getClass().toString().equals("class main.views.home.HumanResourceHomeView")) {
			return new HumanResourceEmployeeView(emp);
		}
		return null;
		
	}
}
