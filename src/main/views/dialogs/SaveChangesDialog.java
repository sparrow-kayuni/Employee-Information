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
			
			try{
				
				if(!oldEmployee.getFirstName().equals("")) {
					//get previous job title and remove its employee
					String oldJobTitle = App.getDepartments().get(oldEmployee.getDepartmentName())
							.getJobTitle(oldEmployee.getJobId());
					
					App.getDepartments().get(oldEmployee.getDepartmentName())
					.getJobPositions().get(oldJobTitle).removeEmployee();
				}
				
				//get updated/new employee job title and set employee to the job position
				String newJobTitle = App.getDepartments().get(employee.getDepartmentName())
						.getJobTitle(employee.getJobId());
				
				App.getDepartments().get(employee.getDepartmentName())
				.getJobPositions().get(newJobTitle).setEmployee(employee);
				
				App.getDepartments().get(employee.getDepartmentName())
				.getJobPositions().get(jobPosition.getJobTitle()).setHourlyPay(jobPosition.getHourlyPay());
				
			}catch(Exception err) {
				err.printStackTrace();
			}
			
			//notify all update listeners
			UpdateEvent event = new UpdateEvent(this);
			for(int i = 0; i < updateListeners.size(); i++) {
				((EmployeeUpdateListener) updateListeners.get(i)).onEmployeeUpdate(event);
			}

			HumanResourceViewEmployeeFrame frame = EmployeeFrameFactory.returnToViewEmployeeFrame(employee);
			
			frame.setVisible(true);
			this.dispose();
		}
		
		if(e.getSource().equals(cancelButton)) {
			this.dispose();
		}
		
	}

}
