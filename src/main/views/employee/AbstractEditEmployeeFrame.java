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
import main.views.dialogs.AbstractUpdateChangesDialog;
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
	
	protected AbstractUpdateChangesDialog saveChangesDialog = null;
	
	private int selectedDepartmentIndex = 0;
	private int selectedJobPositionIndex = 0;
	
	
	protected void addEditableFields() {
		
		employeeIdNumber = new JLabel(Integer.toString(employee.getEmployeeId()));
		employeeIdNumber.setForeground(new Color(212, 212, 212));
		employeeIdNumber.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(employeeIdNumber, "cell 2 2");
		
		firstNameTextField = new JTextField();
		firstNameTextField.setToolTipText("Enter First Name Here");
		firstNameTextField.setText(employee.getFirstName());
		firstNameTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		firstNameTextField.setBackground(new Color(167, 167, 167));
		firstNameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(firstNameTextField, "cell 2 4 5 1,grow");
		
		surnameTextField = new JTextField();
		surnameTextField.setText(employee.getSurname());
		surnameTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		surnameTextField.setBackground(new Color(167, 167, 167));
		surnameTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(surnameTextField, "cell 2 6 5 1,grow");
		
		String[] departmentsList = new String[App.departmentsMap.size()];
		Iterator<Department> deptItr = App.departmentsMap.values().iterator();
		
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
		departmentNameComboBox.setBackground(new Color(167, 167, 167));
		departmentNameComboBox.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		departmentNameComboBox.setModel(new DefaultComboBoxModel<String>(departmentsList));
		departmentNameComboBox.addActionListener(this);
		departmentNameComboBox.setSelectedIndex(selectedDepartmentIndex);
		center_panel.add(departmentNameComboBox, "cell 2 8 5 1,grow");
		
		String[] jobsList = new String[currentDepartment.getJobPositions().size()];
		Iterator<JobPosition> jobsItr = currentDepartment.getJobPositions().values().iterator();
		
		i = 0;
		while(jobsItr.hasNext()) {
			JobPosition job = jobsItr.next();
			if(job.getJobId() == employee.getJobId()) {
				selectedJobPositionIndex = i;
			}
			jobsList[i] = job.getJobTitle();
			i++;
		}
		
		jobTitleComboBox = new JComboBox<String>();
		jobTitleComboBox.setModel(new DefaultComboBoxModel<String>(jobsList));
		jobTitleComboBox.setSelectedIndex(selectedJobPositionIndex);
		jobTitleComboBox.setBackground(new Color(167, 167, 167));
		jobTitleComboBox.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(jobTitleComboBox, "cell 2 10 5 1,grow");
		
		phoneTextField = new JTextField();
		phoneTextField.setText(employee.getPhoneNumber());
		phoneTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		phoneTextField.setBackground(new Color(167, 167, 167));
		phoneTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(phoneTextField, "cell 2 12 5 1,grow");
		
		emailTextField = new JFormattedTextField();
		emailTextField.setText(employee.getEmail());
		emailTextField.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		emailTextField.setBackground(new Color(167, 167, 167));
		emailTextField.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(emailTextField, "cell 2 14 5 1,grow");
		
		hourlyPaySpinner = new JSpinner();
		hourlyPaySpinner.setModel(new SpinnerNumberModel(
				Float.valueOf(jobPosition.getHourlyPay()), 
				null, null, Float.valueOf(5)));
		hourlyPaySpinner.setBackground(new Color(167, 167, 167));
		hourlyPaySpinner.setBorder(BorderFactory.createLineBorder(new Color(167, 167, 167)));
		center_panel.add(hourlyPaySpinner, "cell 2 16 5 1,grow");		
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
			if(!employee.getFirstName().equals("New")) {
				frame = EmployeeFrameFactory.returnToViewEmployeeFrame(employee);
				frame.setVisible(true);
			}
			this.dispose();
		}
		
		//Department selection handling
		if(e.getSource().equals(departmentNameComboBox)) {
			
			//Look up selected department and iterate throuh its job positions
			Department dept = App.departmentsMap.get(departmentNameComboBox.getSelectedItem());
			
			String[] jobsList = new String[dept.getJobPositions().size()];
			Iterator<JobPosition> jobsItr = dept.getJobPositions().values().iterator();
			
			int i = 0;
			while(jobsItr.hasNext()) {
				JobPosition job = jobsItr.next();
				jobsList[i] = job.getJobTitle();
				i++;
			}
			
			//set the values of job titles to the jobs in the deprtment
			if(jobTitleComboBox != null) {
				jobTitleComboBox.setModel(new DefaultComboBoxModel<String>(jobsList));
			}
		}
		
		//Save button clicked
		if(e.getSource().equals(saveChangesButton)) {
			Department dept = App.departmentsMap.get(departmentNameComboBox.getSelectedItem());
			int newJobId = 0;
			JobPosition job = null;
			
			//Iterate through selected department's job positions
			Iterator<JobPosition> jobsItr = dept.getJobPositions().values().iterator();
			while(jobsItr.hasNext()) {
				job = jobsItr.next();
				
				if(job.getJobTitle().equals(jobTitleComboBox.getSelectedItem())) {
					if(!job.isFilled) {
						
					}
					newJobId = job.getJobId();
					break;
				}
			}
			
			if(!job.isFilled) {
				Employee newEmployee = new Employee(
						firstNameTextField.getText(), surnameTextField.getText(),
						employee.getEmployeeId(), emailTextField.getText(), 
						phoneTextField.getText(), newJobId
						);
				
				saveChangesDialog = new SaveChangesDialog(newEmployee);
				saveChangesDialog.setVisible(true);
			}else {
				jobTitleComboBox.setBorder(BorderFactory.createLineBorder(new Color(215, 40, 40)));
			}
		}
		
		if(e.getSource().equals(deleteEmployeeButton)) {
			saveChangesDialog = new DeleteEmployeeDialog(employee);
			saveChangesDialog.setVisible(true);
		}
		
	}
}
