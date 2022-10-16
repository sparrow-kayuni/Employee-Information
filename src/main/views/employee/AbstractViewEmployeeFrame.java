package main.views.employee;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec AbstractViewEmployeeFrame adds text panes to base AbstractEmployeeFrame
 *
 */
public class AbstractViewEmployeeFrame extends AbstractEmployeeFrame {

	private static final long serialVersionUID = 1L;
	
	protected JTextPane employeeIdTextPane;
	protected JTextPane firstNameTextPane;
	protected JTextPane surnameTextPane;
	protected JTextPane jobTitleTextPane;
	protected JTextPane departmentTextPane;
	protected JTextPane phoneTextPane;
	protected JTextPane emailTextPane;
	protected JTextPane hourlyPayTextPane;
	
	
	protected void addTextPanes() {
		employeeIdTextPane = new JTextPane();
		employeeIdTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		employeeIdTextPane.setBackground(new Color(167, 167, 167));
		employeeIdTextPane.setText(Integer.toString(employee.getEmployeeId()));
		employeeIdTextPane.setEditable(false);
		center_panel.add(employeeIdTextPane, "cell 2 2 5 1,growx,aligny baseline");
		
		firstNameTextPane = new JTextPane();
		firstNameTextPane.setText(employee.getFirstName());
		firstNameTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		firstNameTextPane.setEditable(false);
		firstNameTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(firstNameTextPane, "cell 2 4 5 1,grow");
		
		surnameTextPane = new JTextPane();
		surnameTextPane.setText(employee.getSurname());
		surnameTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		surnameTextPane.setEditable(false);
		surnameTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(surnameTextPane, "cell 2 6 5 1,grow");
		
		departmentTextPane = new JTextPane();
		departmentTextPane.setText(employee.getJobPosition().getDepartment().getDepartmentName());
		departmentTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		departmentTextPane.setEditable(false);
		departmentTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(departmentTextPane, "cell 2 8 5 1,grow");
		
		jobTitleTextPane = new JTextPane();
		jobTitleTextPane.setText(employee.getJobPosition().getJobTitle());
		jobTitleTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		jobTitleTextPane.setEditable(false);
		jobTitleTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(jobTitleTextPane, "cell 2 10 5 1,growx,aligny baseline");
		
		emailTextPane = new JTextPane();
		emailTextPane.setText(employee.getEmail());
		emailTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		emailTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(emailTextPane, "cell 2 14 5 1,grow");
		
		phoneTextPane = new JTextPane();
		phoneTextPane.setText(employee.getPhoneNumber());
		phoneTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		phoneTextPane.setEditable(false);
		phoneTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(phoneTextPane, "cell 2 12 5 1,grow");
		
		
		hourlyPayTextPane = new JTextPane();
		hourlyPayTextPane.setText(Float.toString(employee.getJobPosition().getHourlyPay()));
		hourlyPayTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		hourlyPayTextPane.setEditable(false);
		hourlyPayTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(hourlyPayTextPane, "cell 2 16 5 1,grow");
	}
}
