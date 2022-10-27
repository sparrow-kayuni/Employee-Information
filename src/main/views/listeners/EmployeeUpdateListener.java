package main.views.listeners;

import main.views.events.UpdateEvent;

public interface EmployeeUpdateListener {
	
	//Triggered when an employee has been updated
	public void onEmployeeUpdate(UpdateEvent e);
}
