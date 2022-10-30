package main.views.employee;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;

import main.employeesystem.App;
import main.models.Department;
import main.models.Employee;
import main.models.JobPosition;
import main.views.components.EmployeeActionButton;
import main.views.dialogs.DeleteEmployeeDialog;
import main.views.dialogs.SaveChangesDialog;
import main.views.factories.EmployeeFrameFactory;
import main.views.listeners.EditActionListener;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec AbstractEditEmployeeFrame adds editable fields to AbstractEmployeeFrame
 *
 */
public class AbstractEditEmployeeFrame extends AbstractEmployeeFrame implements EditActionListener{

	private static final long serialVersionUID = 1L;
	
	protected JLabel employeeIdNumber;
	protected JLabel flashMessageLabel;
	protected JTextField firstNameTextField;
	protected JTextField surnameTextField;
	protected JTextPane jobTitleTextField;
	protected JComboBox<String> departmentNameComboBox;
	protected JComboBox<String> jobTitleComboBox;
	protected JTextField phoneTextField;
	protected JFormattedTextField emailTextField;
	protected JSpinner hourlyPaySpinner;
	
	protected EmployeeActionButton deleteEmployeeButton = null;
	protected EmployeeActionButton saveChangesButton = null;
	
	private int selectedDepartmentIndex = 0;
	private int selectedJobPositionIndex = 0;
	
	
	protected void addEditableFields() {
		
		employeeIdNumber = new JLabel(Integer.toString(employee.getEmployeeId()));
		employeeIdNumber.setForeground(new Color(212, 212, 212));
		employeeIdNumber.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(employeeIdNumber, "cell 2 2");
		
		firstNameTextField = new JTextField();
		firstNameTextField.setToolTipText("Enter First Name");
		firstNameTextField.setText(employee.getFirstName());
		firstNameTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		firstNameTextField.setBackground(new Color(167, 167, 167));
		firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(firstNameTextField, "cell 2 4 5 1,grow");
		
		surnameTextField = new JTextField();
		surnameTextField.setToolTipText("Enter Surname");
		surnameTextField.setText(employee.getSurname());
		surnameTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		surnameTextField.setBackground(new Color(167, 167, 167));
		surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(surnameTextField, "cell 2 6 5 1,grow");
		
		//initialize department options values and select current employee's department
		String[] departmentsList = new String[App.getAllDepartments().size()];
		Iterator<Department> deptItr = App.getAllDepartments().values().iterator();
		int i = 0;
		while(deptItr.hasNext()) {
			Department dept = deptItr.next();
			if(dept.getDepartmentName().equals(currentDepartment.getDepartmentName())) {
				selectedDepartmentIndex = i;
			}
			departmentsList[i] = dept.getDepartmentName();
			i++;
		}
		
		departmentNameComboBox = new JComboBox<String>();
		departmentNameComboBox.setToolTipText("Select Department");
		departmentNameComboBox.setBackground(new Color(167, 167, 167));
		departmentNameComboBox.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		departmentNameComboBox.setModel(new DefaultComboBoxModel<String>(departmentsList));
		departmentNameComboBox.addActionListener(this);
		departmentNameComboBox.setSelectedIndex(selectedDepartmentIndex);
		center_panel.add(departmentNameComboBox, "cell 2 8 5 1,grow");
		
		//initialize job positions drop-down menu and select employee's current employee's job position,
		String[] jobsList = new String[currentDepartment.getAllJobPositions().size()];
		Iterator<JobPosition> jobsItr = currentDepartment.getAllJobPositions().values().iterator();
		i = 0;
		while(jobsItr.hasNext()) {
			JobPosition job = jobsItr.next();
//			System.out.println(job.getJobId());
			
			if(job.getJobId() == employee.getJobId()) {
				selectedJobPositionIndex = i;
			}else if(employee.getJobId() == 0) {
				selectedJobPositionIndex = -1;
			}
			jobsList[i] = job.getJobTitle();
			i++;
		}
		
		jobTitleComboBox = new JComboBox<String>();
		jobTitleComboBox.setToolTipText("Select Job Position");
		jobTitleComboBox.setModel(new DefaultComboBoxModel<String>(jobsList));
		jobTitleComboBox.setSelectedIndex(selectedJobPositionIndex);
		jobTitleComboBox.setBackground(new Color(167, 167, 167));
		jobTitleComboBox.addActionListener(this);
		jobTitleComboBox.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(jobTitleComboBox, "cell 2 10 5 1,grow");
		
		phoneTextField = new JTextField();
		phoneTextField.setToolTipText("Enter Phone Number");
		phoneTextField.setText(employee.getPhoneNumber());
		phoneTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		phoneTextField.setBackground(new Color(167, 167, 167));
		phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(phoneTextField, "cell 2 12 5 1,grow");
		
		emailTextField = new JFormattedTextField();
		emailTextField.setToolTipText("Enter Email Address");
		emailTextField.setText(employee.getEmail());
		emailTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		emailTextField.setBackground(new Color(167, 167, 167));
		emailTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(emailTextField, "cell 2 14 5 1,grow");
		
		hourlyPaySpinner = new JSpinner();
		hourlyPaySpinner.setToolTipText("Enter Hourly Pay");
		hourlyPaySpinner.setModel(new SpinnerNumberModel(
				Float.valueOf(jobPosition.getHourlyPay()), 
				null, null, Float.valueOf(5)));
		hourlyPaySpinner.setBackground(new Color(167, 167, 167));
		hourlyPaySpinner.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(hourlyPaySpinner, "cell 2 16 5 1,grow");
		
		flashMessageLabel = new JLabel("");
		flashMessageLabel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 11));
		flashMessageLabel.setForeground(new Color(255, 100, 100));
		flashMessageLabel.setVisible(false);
		center_panel.add(flashMessageLabel, "cell 0 18");
	}
	
	protected void addSaveButton() {
		saveChangesButton = new EmployeeActionButton("Save", new Color(0, 120, 215));
		saveChangesButton.addActionListener(this);
		saveChangesButton.enableButton();
		center_panel.add(saveChangesButton, "cell 4 18,growx");
		closeButton.addActionListener(this);
	}
	
	protected void addDeleteButton() {
		deleteEmployeeButton = new EmployeeActionButton("Delete Employee", new Color(215, 120, 120));
		deleteEmployeeButton.addActionListener(this);
		deleteEmployeeButton.enableButton();
		center_panel.add(deleteEmployeeButton, "cell 6 0,growx");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Close button clicked
		if(e.getSource().equals(closeButton)) {
			AbstractEmployeeFrame frame = null;
			
			if(!hasEmptyField()) {
				frame = EmployeeFrameFactory.returnToViewEmployeeFrame(employee);
				frame.setVisible(true);
			}
			this.dispose();
		}
		
		Department dept = App.getDepartment((String) departmentNameComboBox.getSelectedItem());
		
		//Department selection handling
		if(e.getSource().equals(departmentNameComboBox)) {
			
			//Look up selected department and iterate throuh its job positions
			String[] jobsList = new String[dept.getAllJobPositions().size()];
			Iterator<JobPosition> jobsItr = dept.getAllJobPositions().values().iterator();
			
			int i = 0;
			while(jobsItr.hasNext()) {
				JobPosition job = jobsItr.next();
				jobsList[i] = job.getJobTitle();
				i++;
			}
			
			JobPosition job = null;
			//set the values of job titles to the jobs in the deprtment
			if(jobTitleComboBox != null) {
				jobTitleComboBox.setModel(new DefaultComboBoxModel<String>(jobsList));
				job = dept.getJobPosition((String) jobTitleComboBox.getSelectedItem());
				hourlyPaySpinner.setValue(job.getHourlyPay());
			}
		}
		
		//Job title changed
		if(e.getSource().equals(jobTitleComboBox)) {
			try {
				JobPosition job = dept.getJobPosition((String) jobTitleComboBox.getSelectedItem());
				
				hourlyPaySpinner.setValue(job.getHourlyPay());
				
				//if selected job is filled && isn't the same current job, show flash message
				if(job.isFilled && employee.getJobId() != job.getJobId()) {
					
					//reset text fields and to default color
					firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
					surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
					phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
					emailTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
					
					//set job title field to red
					jobTitleComboBox.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
					flashMessageLabel.setText("Job Position is Already Filled");
					flashMessageLabel.setVisible(true);
				}else {
					jobTitleComboBox.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
					flashMessageLabel.setText("");
					flashMessageLabel.setVisible(false);
				}
				
			}catch(NullPointerException err) {
				err.printStackTrace();
			}
		}
		
		//Save button clicked
		if(e.getSource().equals(saveChangesButton)) {
			
			resetFieldBorders();
			
			if(hasValidFields()) {
				//get previous employee
				Department prevDept = null;
				prevDept = App.getDepartment(employee.getDepartmentName());
				
				JobPosition prevJob = null;
				
				if(prevDept == null) prevJob = new JobPosition(0, "", 0, null);
				else {
					String prevJobTitle = prevDept.getJobTitle(employee.getJobId());
					prevJob = prevDept.getJobPosition(prevJobTitle);
				}
				
				Department newDept = null;
				newDept = App.getDepartment((String) departmentNameComboBox.getSelectedItem());
				JobPosition newJob = dept.getJobPosition((String) jobTitleComboBox.getSelectedItem());
				int newJobId = 0;
				
				if(newJob != null) newJobId = newJob.getJobId();
				
				//create new employee
				Employee newEmployee = new Employee(
						firstNameTextField.getText(), surnameTextField.getText(),
						employee.getEmployeeId(), emailTextField.getText(), 
						phoneTextField.getText(), newJobId
						);
				newEmployee.setDepartmentName(newDept.getDepartmentName());
				
				//check if changes where made
				if((!employee.isIdenticalTo(newEmployee) || newJob.getJobId() != prevJob.getJobId()
						|| !hourlyPaySpinner.getValue().equals(prevJob.getHourlyPay()))) {
					newJob.setHourlyPay((Float) hourlyPaySpinner.getValue());
					
					//show save dialog
					saveChangesDialog = new SaveChangesDialog(newEmployee, employee, newJob);
					saveChangesDialog.setVisible(true);
					
					saveChangesDialog.addUpdateListener(this);
					saveChangesDialog.addUpdateListener(homeView);
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
			saveChangesDialog = new DeleteEmployeeDialog(employee);
			saveChangesDialog.setVisible(true);
			saveChangesDialog.addUpdateListener(this);
			saveChangesDialog.addUpdateListener(homeView);
		}
		
	}

	
	private boolean hasValidFields() {
		
		boolean hasInvalidFields = false;
		
		//check for empty fields
		if(hasEmptyField()) {
			if(firstNameTextField.getText().matches("[\\s]*")) {
				firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				hasInvalidFields = true;
			}
			
			if(surnameTextField.getText().matches("[\\s]*")) {
				surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				hasInvalidFields = true;
			}
			
			if(phoneTextField.getText().matches("[\\s]*")) {
				phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				hasInvalidFields = true;
			}
			
			if(emailTextField.getText().matches("[\\s]*")) {
				emailTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				hasInvalidFields = true;
			}
			
			if(jobTitleComboBox.getSelectedItem() == null) {
				jobTitleComboBox.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				hasInvalidFields = true;
			}
			
			if(hasInvalidFields) {
				flashMessageLabel.setText("Empty Field");
			}
		}
		
		//check if phone number is valid Zambian line
		if(!phoneTextField.getText().matches("((\\+26)|(26))?0[97]?[567]?[0-9]{7}")) {
			phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));

			flashMessageLabel.setText("Enter Zambian Phone Number");
			hasInvalidFields = true;
		}
		
		//check if email address is valid
		if(!emailTextField.getText().matches("[a-zA-Z0-9_\\-\\.]+@[a-z]+\\.[a-z]{2,3}([\\.][a-z]{2,3})?")) {
			emailTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
			
			flashMessageLabel.setText("Invalid Email Address");
			hasInvalidFields = true;
		}
		
		//check if firstname includes letters, hyphen or apostrophe
		if(!firstNameTextField.getText().matches("[A-Za-z\\-']+")) {
			firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
			
			flashMessageLabel.setText("Invalid first name");
			hasInvalidFields = true;
		}else {
			if(firstNameTextField.getText().matches("[a-z\\-']+")) {
				String caps = firstNameTextField.getText().substring(0, 1).toUpperCase();
				String remainder = firstNameTextField.getText().substring(1);
				firstNameTextField.setText(caps + remainder);
			}
		}
		
		//checks if surname includes letters, hyphen or apostrophe
		if(!surnameTextField.getText().matches("[A-Za-z\\-']+")){
			
			//checks if there are previous invalid fields
			if(!hasInvalidFields) {
				surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				
				flashMessageLabel.setText("Invalid surname");
				hasInvalidFields = true;
			}else {
				//multiple tested fields are invalid are invalid
				flashMessageLabel.setText("Invalid Fields");
			}
		}else {
			if(surnameTextField.getText().matches("[a-z\\-']+")) {
				String caps = surnameTextField.getText().substring(0, 1).toUpperCase();
				String remaider = surnameTextField.getText().substring(1);
				surnameTextField.setText(caps + remaider);
			}
		}
		
		return !hasInvalidFields;
	}
	
	private boolean hasEmptyField() {
		return firstNameTextField.getText().matches("[\\s]*") ||
				surnameTextField.getText().matches("[\\s]*") ||
				phoneTextField.getText().matches("[\\s]*") ||
				emailTextField.getText().equals("[\\s]*") ||
				jobTitleComboBox.getSelectedIndex() != -1? true: false;
	}

	private void resetFieldBorders() {
		//reset text fields and to default color
		firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		emailTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		hourlyPaySpinner.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		
		flashMessageLabel.setText("");
		flashMessageLabel.setVisible(false);
	}
}
