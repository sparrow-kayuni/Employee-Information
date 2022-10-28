package main.views.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.employeesystem.App;
import main.models.Employee;
import main.models.JobPosition;
import main.views.employee.HumanResourceViewEmployeeFrame;
import main.views.events.UpdateEvent;
import main.views.factories.EmployeeFrameFactory;
import main.views.listeners.EmployeeUpdateListener;

public class SaveChangesDialog extends AbstractUpdateChangesDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JobPosition jobPosition = null;
	private Employee oldEmployee = null;

	public SaveChangesDialog(Employee newEmp, Employee oldEmp, JobPosition job) {
		employee = newEmp;
		oldEmployee = oldEmp;
		jobPosition = job;
		initializeDialog("Are you sure you want to update changes made?");
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(okButton)) {
			
			if(!oldEmployee.getFirstName().equals("")) {
				removeEmployee(oldEmployee);
				setNewEmployee();
				updateJobInfo();
				App.getDb().updateEmployee(employee);
			}else {
				setNewEmployee();
				updateJobInfo();
				App.getDb().addNewEmployee(employee);
			}
			
			App.getDb().updateJobPosition(jobPosition);
						
			notifyListeners();

			HumanResourceViewEmployeeFrame frame = EmployeeFrameFactory.returnToViewEmployeeFrame(employee);
			
			frame.setVisible(true);
			this.dispose();
		}
		
		if(e.getSource().equals(cancelButton)) {
			this.dispose();
		}
	}
	
	
	private void setNewEmployee() {
		//get updated/new employee job title and set employee to the job position
		String newJobTitle = App.getDepartments().get(employee.getDepartmentName())
				.getJobTitle(employee.getJobId());
		
		App.getDepartments().get(employee.getDepartmentName())
		.getJobPositions().get(newJobTitle).setEmployee(employee);
		
	}
	
	
	private void updateJobInfo() {
		App.getDepartments().get(employee.getDepartmentName())
		.getJobPositions().get(jobPosition.getJobTitle()).setHourlyPay(jobPosition.getHourlyPay());
	}
	
	
	private void notifyListeners() {
		//notify all update listeners
		UpdateEvent event = new UpdateEvent(this);
		for(int i = 0; i < updateListeners.size(); i++) {
			((EmployeeUpdateListener) updateListeners.get(i)).onEmployeeUpdate(event);
		}
	}

}
