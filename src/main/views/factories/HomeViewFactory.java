package main.views.factories;

import java.util.HashMap;

import main.models.Department;
import main.views.AbstractHomeView;
import main.views.GeneralHomeView;
import main.views.HumanResourceHomeView;

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
