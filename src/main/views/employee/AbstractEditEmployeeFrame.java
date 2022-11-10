package main.views.employee;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
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
import main.models.JobPosition;
import main.views.components.EmployeeActionButton;
import main.views.dialogs.SaveChangesDialog;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec AbstractEditEmployeeFrame adds editable fields to AbstractEmployeeFrame
 *
 */
public abstract class AbstractEditEmployeeFrame extends AbstractEmployeeFrame implements ActionListener {

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
	
	protected EmployeeActionButton saveChangesButton = null;
	
	protected SaveChangesDialog saveChangesDialog = null;
	
	private int selectedDepartmentIndex = 0;
	private int selectedJobPositionIndex = 0;
	
	protected JobPosition selectedJobPosition = null;
	protected Department selectedDepartment = null;
	
	
	protected void initializeEditableFields() {
		
		employeeIdNumber = new JLabel();
		employeeIdNumber.setForeground(new Color(212, 212, 212));
		employeeIdNumber.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(employeeIdNumber, "cell 2 2");
		
		firstNameTextField = new JTextField();
		firstNameTextField.setToolTipText("Enter First Name");
		firstNameTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		firstNameTextField.setBackground(new Color(167, 167, 167));
		firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(firstNameTextField, "cell 2 4 5 1,grow");
		
		surnameTextField = new JTextField();
		surnameTextField.setToolTipText("Enter Surname");
		surnameTextField.setText(currentEmployee.getSurname());
		surnameTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		surnameTextField.setBackground(new Color(167, 167, 167));
		surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(surnameTextField, "cell 2 6 5 1,grow");
		
		departmentNameComboBox = new JComboBox<String>();
		departmentNameComboBox.setToolTipText("Select Department");
		departmentNameComboBox.setBackground(new Color(167, 167, 167));
		departmentNameComboBox.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		departmentNameComboBox.addActionListener(this);
		center_panel.add(departmentNameComboBox, "cell 2 8 5 1,grow");
		
		jobTitleComboBox = new JComboBox<String>();
		jobTitleComboBox.setToolTipText("Select Job Position");
		jobTitleComboBox.setBackground(new Color(167, 167, 167));
		jobTitleComboBox.addActionListener(this);
		jobTitleComboBox.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(jobTitleComboBox, "cell 2 10 5 1,grow");
		
		phoneTextField = new JTextField();
		phoneTextField.setToolTipText("Enter Phone Number");
		phoneTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		phoneTextField.setBackground(new Color(167, 167, 167));
		phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(phoneTextField, "cell 2 12 5 1,grow");
		
		emailTextField = new JFormattedTextField();
		emailTextField.setToolTipText("Enter Email Address");
		emailTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		emailTextField.setBackground(new Color(167, 167, 167));
		emailTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(emailTextField, "cell 2 14 5 1,grow");
		
		hourlyPaySpinner = new JSpinner();
		hourlyPaySpinner.setToolTipText("Enter Hourly Pay");
		hourlyPaySpinner.setBackground(new Color(167, 167, 167));
		hourlyPaySpinner.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(hourlyPaySpinner, "cell 2 16 5 1,grow");
		
		flashMessageLabel = new JLabel("");
		flashMessageLabel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 11));
		flashMessageLabel.setForeground(new Color(255, 100, 100));
		flashMessageLabel.setVisible(false);
		center_panel.add(flashMessageLabel, "cell 0 18");
	}
	
	protected void setTextPaneValues() {
		employeeIdNumber.setText(Integer.toString(currentEmployee.getEmployeeId()));
		firstNameTextField.setText(currentEmployee.getFirstName());
		surnameTextField.setText(currentEmployee.getSurname());
		phoneTextField.setText(currentEmployee.getPhoneNumber());
		emailTextField.setText(currentEmployee.getEmail());
		hourlyPaySpinner.setModel(new SpinnerNumberModel(
				Float.valueOf(currentJobPosition.getHourlyPay()), 
				null, null, Float.valueOf(5)));
		
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
		departmentNameComboBox.setModel(new DefaultComboBoxModel<String>(departmentsList));
		departmentNameComboBox.setSelectedIndex(selectedDepartmentIndex);
		
		//initialize job positions drop-down menu and select employee's current employee's job position,
		String[] jobsList = new String[currentDepartment.getAllJobPositions().size()];
		Iterator<JobPosition> jobsItr = currentDepartment.getAllJobPositions().values().iterator();
		i = 0;
		while(jobsItr.hasNext()) {
			JobPosition job = jobsItr.next();
			
			if(job.getJobId() == currentEmployee.getJobId()) {
				selectedJobPositionIndex = i;
			}else if(currentEmployee.getJobId() == 0) {
				selectedJobPositionIndex = -1;
			}
			jobsList[i] = job.getJobTitle();
			i++;
		}
		jobTitleComboBox.setModel(new DefaultComboBoxModel<String>(jobsList));
		jobTitleComboBox.setSelectedIndex(selectedJobPositionIndex);
	}
	
	
	protected void addSaveButton() {
		saveChangesButton = new EmployeeActionButton("Save", new Color(0, 120, 215));
		saveChangesButton.addActionListener(this);
		saveChangesButton.enableButton();
		center_panel.add(saveChangesButton, "cell 4 18,growx");
		closeButton.addActionListener(this);
	}

	
	protected boolean hasValidFields() {
		
		boolean hasInvalidFields = false;
		int invalidFieldCount = 0;
		
		//check for empty fields
		if(hasEmptyField()) {
			if(firstNameTextField.getText().matches("[\\s]*")) {
				firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				invalidFieldCount++;
			}
			
			if(surnameTextField.getText().matches("[\\s]*")) {
				surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				invalidFieldCount++;
			}
			
			if(phoneTextField.getText().matches("[\\s]*")) {
				phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				invalidFieldCount++;
			}
			
			if(emailTextField.getText().matches("[\\s]*")) {
				emailTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				invalidFieldCount++;
			}
			
			if(jobTitleComboBox.getSelectedIndex() == -1) {
				jobTitleComboBox.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				invalidFieldCount++;
			}
			
			hasInvalidFields = true;
			if(invalidFieldCount > 1) flashMessageLabel.setText("Empty Fields");
			else flashMessageLabel.setText("Empty Field");
			
		}else {
			
			//check if phone number is valid Zambian line
			if(!phoneTextField.getText().matches("((\\+26)|(26))?0[97]?[567]?[0-9]{7}")) {
				phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				flashMessageLabel.setText("Enter Zambian Phone Number");
				hasInvalidFields = true;
				invalidFieldCount++;
			}
			
			//check if email address is valid
			if(!emailTextField.getText().matches("[a-zA-Z0-9_\\-\\.]+@[a-z]+\\.[a-z]{2,3}([\\.][a-z]{2,3})?")) {
				emailTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				
				flashMessageLabel.setText("Invalid Email Address");
				hasInvalidFields = true;
				invalidFieldCount++;
			}
			
			//check if firstname includes letters, hyphen or apostrophe
			if(!firstNameTextField.getText().matches("[A-Za-z\\-']+")) {
				firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				
				flashMessageLabel.setText("Invalid first name");
				hasInvalidFields = true;
				invalidFieldCount++;
			}else {
				if(firstNameTextField.getText().matches("[a-z\\-']+")) {
					//Capitalize first letter
					String caps = firstNameTextField.getText().substring(0, 1).toUpperCase();
					String remainder = firstNameTextField.getText().substring(1);
					firstNameTextField.setText(caps + remainder);
				}
			}
			
			//checks if surname includes letters, hyphen or apostrophe
			if(!surnameTextField.getText().matches("[A-Za-z\\-']+")){
				surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				
				flashMessageLabel.setText("Invalid surname");
				hasInvalidFields = true;
				invalidFieldCount++;
			}else {
				if(surnameTextField.getText().matches("[a-z\\-']+")) {
					String caps = surnameTextField.getText().substring(0, 1).toUpperCase();
					String remaider = surnameTextField.getText().substring(1);
					surnameTextField.setText(caps + remaider);
				}
			}
			
			//check if selected job is already filled
			Department dept = App.getDepartment((String) departmentNameComboBox.getSelectedItem());
			JobPosition job = dept.getJobPosition((String) jobTitleComboBox.getSelectedItem());
			if(job.isFilled && currentEmployee.getJobId() != job.getJobId()) {
				
				//reset text fields and to default color
				firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
				surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
				phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
				emailTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
				
				//set job title field to red
				jobTitleComboBox.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
				flashMessageLabel.setText("Job Position is Already Filled");
				
				hasInvalidFields = true;
				invalidFieldCount++;
			}
			
			if(invalidFieldCount > 1) {
				flashMessageLabel.setText("Invalid Fields");
			}
		}
		
		return !hasInvalidFields;
	}
	
	//checks if any field has only empty space
	protected boolean hasEmptyField() {
		return firstNameTextField.getText().matches("[\\s]*") ||
				surnameTextField.getText().matches("[\\s]*") ||
				phoneTextField.getText().matches("[\\s]*") ||
				emailTextField.getText().matches("[\\s]*") ||
				jobTitleComboBox.getSelectedIndex() == -1;
	}

	//sets field borders to default
	protected void resetTextFields() {
		//reset text fields and to default color
		firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		emailTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		hourlyPaySpinner.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		
		flashMessageLabel.setText("");
		flashMessageLabel.setVisible(false);
	}
	
	//
	protected void setSelectedDepartment() {
		selectedDepartment = App.getDepartment((String) departmentNameComboBox.getSelectedItem());
		
		//Look up selected department and iterate throuh its job positions
		String[] jobsList = new String[selectedDepartment.getAllJobPositions().size()];
		Iterator<JobPosition> jobsItr = selectedDepartment.getAllJobPositions().values().iterator();
		
		int i = 0;
		while(jobsItr.hasNext()) {
			JobPosition job = jobsItr.next();
			jobsList[i] = job.getJobTitle();
			i++;
		}
		
		selectedJobPosition = null;
		
		//set the values of job titles to the jobs in the deprtment
		if(jobTitleComboBox != null) {
			jobTitleComboBox.setModel(new DefaultComboBoxModel<String>(jobsList));
			selectedJobPosition = selectedDepartment.getJobPosition((String) jobTitleComboBox.getSelectedItem());
			hourlyPaySpinner.setValue(selectedJobPosition.getHourlyPay());
		}
	}
	
	
	protected void setSelectedJobPosition(Department dept) throws NullPointerException {
		try {
			selectedJobPosition = dept.getJobPosition((String) jobTitleComboBox.getSelectedItem());
			if(selectedJobPosition != null) {
				hourlyPaySpinner.setValue(selectedJobPosition.getHourlyPay());
				
				//if selected job is filled && isn't the same current job, show flash message
				if(selectedJobPosition.isFilled && currentEmployee.getJobId() != selectedJobPosition.getJobId()) {
					
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
			}			
			
		}catch(NullPointerException err) {
			err.printStackTrace();
		}
	}
}
