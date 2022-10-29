package main.views.factories;

import java.util.HashMap;

import main.employeesystem.App;
import main.models.Department;
import main.views.home.AbstractHomeFrame;
import main.views.home.GeneralHomeFrame;
import main.views.home.HumanResourceHomeFrame;

/**
 * 
 * @author Mwiinga Kayuni
 * @version 1.1
 *
 */
public class HomeFrameFactory {
	
	/**
	 * 
	 * @param deptList
	 * @return AbstractHomeView 
	 */
	public static AbstractHomeFrame createHomeView() {
		
		if(App.getDepartment("HUMAN RESOURCES").isLoggedIn) {
			return new HumanResourceHomeFrame();
		}else if(App.getDepartment("ACCOUNTS").isLoggedIn || App.getDepartment("EXECUTIVE").isLoggedIn){
			return new GeneralHomeFrame();
		}
		
		return null;
	}
}
