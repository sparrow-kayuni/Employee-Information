package main.views.factories;

import main.employeesystem.App;
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
		}else if(App.getDepartment("ACCOUNTS").isLoggedIn || 
				App.getDepartment("EXECUTIVE").isLoggedIn ||
				App.getDepartment("FINANCE").isLoggedIn ||
				App.getDepartment("SALES").isLoggedIn){
			return new GeneralHomeFrame();
		}
		
		return null;
	}
}
