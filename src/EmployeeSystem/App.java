package EmployeeSystem;

import Views.LoginView;

public class App {
	
	public static void run() {
		showLoginView();
	}
	
	public static void showLoginView() {
		try {
			LoginView window = new LoginView();
			window.setVisible(true);
			if(window.isLoggedIn) {
				window.dispose();
				System.out.println("Done");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

