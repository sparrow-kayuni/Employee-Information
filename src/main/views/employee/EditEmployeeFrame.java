package main.views.employee;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

import main.employeesystem.App;
import main.models.Department;
import main.models.Employee;
import main.models.JobPosition;
import main.views.components.EmployeeActionButton;
import main.views.dialogs.DeleteEmployeeDialog;
import main.views.dialogs.SaveChangesDialog;
import main.views.events.CloseEvent;
import main.views.events.UpdateEvent;
import main.views.listeners.EmployeeUpdateListener;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec EditEmployeeFrame is a concrete implementation of AbstractEditEmployeeFrame 
 * for editing existing employee information
 *
 */
public class EditEmployeeFrame extends AbstractEditEmployeeFrame implements EmployeeUpdateListener{

	private static final long serialVersionUID = 1L;
	
	private EmployeeActionButton deleteEmployeeButton = null;
	
	private DeleteEmployeeDialog deleteEmployeeDialog = null;
	
	public EditEmployeeFrame(Employee emp) {
		setEmployeeInformation(emp);
		setTitle("Edit Employee Details");
		initializePanels();
		setFrameHeader();
		initializeEditableFields();
		setTextPaneValues();
		addSaveButton();
		addDeleteButton();
	}
	
	private void addDeleteButton() {
		deleteEmployeeButton = new EmployeeActionButton("Delete Employee", new Color(215, 120, 120));
		deleteEmployeeButton.addActionListener(this);
		deleteEmployeeButton.enableButton();
		center_panel.add(deleteEmployeeButton, "cell 6 0,growx");
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
				
				//set previous job title to current employee's job title
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
					saveChangesDialog.addUpdateListener(App.getHomeView());
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
		
		//Delete button clicked
		if(e.getSource().equals(deleteEmployeeButton)) {
			deleteEmployeeDialog = new DeleteEmployeeDialog(currentEmployee);
			deleteEmployeeDialog.setVisible(true);
			deleteEmployeeDialog.addUpdateListener(this);
			deleteEmployeeDialog.addUpdateListener(App.getHomeView());
		}	
	}
	
	@Override
	public void onEmployeeUpdate(UpdateEvent event) {
		if(event.getSource().equals(saveChangesDialog)) {
			this.setClosedEvent(CloseEvent.Event.SAVE);
			this.dispose();
		}
		
		if(event.getSource().equals(deleteEmployeeDialog)) {
			this.setClosedEvent(CloseEvent.Event.DELETE);
			this.dispose();
		}
	}
}
