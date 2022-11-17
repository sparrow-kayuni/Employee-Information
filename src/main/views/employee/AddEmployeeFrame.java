package main.views.employee;


import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

import main.employeesystem.App;
import main.models.Department;
import main.models.Employee;
import main.models.JobPosition;
import main.views.dialogs.SaveChangesDialog;
import main.views.events.CloseEvent;
import main.views.events.UpdateEvent;
import main.views.listeners.EmployeeUpdateListener;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec AddEmployeeFrame contains empty AbstractEditEmployeeFrame
 *
 */
public class AddEmployeeFrame extends AbstractEditEmployeeFrame implements EmployeeUpdateListener{

	private static final long serialVersionUID = 1L;
	private static String deptName = null;

	public AddEmployeeFrame(String dept) {
		deptName = dept;
		createNewEmployee();
		setTitle("Add New Employee");
		initializePanels();
		try {
			employeeNameHeader.setText("New Employee");
		}catch(Exception err){
			err.printStackTrace();
		}
		initializeEditableFields();
		setTextPaneValues();
		addSaveButton();
	}

	private void createNewEmployee() {
		int newEmployeeId = App.lastEmployeeId + 1;
		this.currentEmployee = new Employee("", "", newEmployeeId, "", "", 0);
		currentEmployee.setDepartmentName(deptName);
		currentDepartment = App.getDepartment(deptName);
		
		this.currentJobPosition = currentDepartment
				.getAllJobPositions().values().iterator().next();
		
	}

	@Override
	public void onEmployeeUpdate(UpdateEvent event) {
		if(event.getSource().equals(saveChangesDialog)) {
			this.setClosedEvent(CloseEvent.Event.SAVE);
			App.lastEmployeeId++;
			this.dispose();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Close button clicked
		if(e.getSource().equals(closeButton)) {
			notifyClosedListeners(CloseEvent.Event.CANCEL);
			this.dispose();
		}
		
		//Department selection handling
		if(e.getSource().equals(departmentNameComboBox)) {
			setSelectedDepartment();
		}
		
		
		//Job title changed
		if(e.getSource().equals(jobTitleComboBox)) {
			setSelectedJobPosition(App.getDepartment((String) departmentNameComboBox.getSelectedItem()));
		}
		
		//Save button clicked
		if(e.getSource().equals(saveChangesButton)) {
			
			resetTextFields();
			
			if(hasValidFields()) {
				//get previous employee
				Department prevDept = null;
				prevDept = App.getDepartment(currentEmployee.getDepartmentName());
				
				JobPosition prevJob = null;
				
				if(prevDept == null) prevJob = new JobPosition(0, "", 0, null);
				else {
					String prevJobTitle = prevDept.getJobTitle(currentEmployee.getJobId());
					prevJob = prevDept.getJobPosition(prevJobTitle);
				}
				
				Department newDept = null;
				newDept = App.getDepartment((String) departmentNameComboBox.getSelectedItem());
				JobPosition newJob = selectedDepartment.getJobPosition((String) jobTitleComboBox.getSelectedItem());
				int newJobId = 0;
				
				if(newJob != null) newJobId = newJob.getJobId();
				
				//create new employee
				Employee newEmployee = new Employee(
						firstNameTextField.getText(), surnameTextField.getText(),
						currentEmployee.getEmployeeId(), emailTextField.getText(), 
						phoneTextField.getText(), newJobId
						);
				newEmployee.setDepartmentName(newDept.getDepartmentName());
				
				//check if changes where made
				if((!currentEmployee.isIdenticalTo(newEmployee) || newJob.getJobId() != prevJob.getJobId()
						|| !hourlyPaySpinner.getValue().equals(prevJob.getHourlyPay()))) {
					newJob.setHourlyPay((Float) hourlyPaySpinner.getValue());
					
					//show save dialog
					saveChangesDialog = new SaveChangesDialog(newEmployee, currentEmployee, newJob);
					saveChangesDialog.setVisible(true);
					
					saveChangesDialog.addUpdateListener(this);
					saveChangesDialog.addUpdateListener(App.getHomeFrame());
				}else {
					//highlight unchanged fields and show flash message
					firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
					surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
					phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
					emailTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
					hourlyPaySpinner.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
					
					flashMessageLabel.setText("No changes made");
					flashMessageLabel.setVisible(true);
				}
			}else {
				flashMessageLabel.setVisible(true);
			}
		}
	}
}