package EmployeeSystem;

import Views.LoginView;

public class App {
	
	public static void run() {
		showLoginView();
	}
	
	public static void showLoginView() {
		try {
			LoginView window = new LoginView();
			window.getFrmEmployeeInformationSystem().setVisible(true);
			if(window.isLoggedIn) {
				window.loginUser();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

