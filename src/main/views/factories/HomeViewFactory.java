package main.views.factories;

import java.util.HashMap;

import main.models.Department;
import main.views.home.AbstractHomeView;
import main.views.home.GeneralHomeView;
import main.views.home.HumanResourceHomeView;

/**
 * 
 * @author Mwiinga Kayuni
 * @version 1.1
 *
 */
public class HomeViewFactory {
	
	/**
	 * 
	 * @param deptList
	 * @return AbstractHomeView 
	 */
	public static AbstractHomeView createHomeView(HashMap<String, Department> deptList) {
		if(deptList.get("HUMAN RESOURCES").isLoggedIn) {
			return new HumanResourceHomeView();
		}else if(deptList.get("ACCOUNTS").isLoggedIn || deptList.get("EXECUTIVE").isLoggedIn){
			return new GeneralHomeView();
		}
		
		return null;
	}
}
