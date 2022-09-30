package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

import EmployeeSystem.App;
import Models.Employee;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JPasswordField;
import java.awt.Component;
import javax.swing.Box;

public class LoginView extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private JTextField employeeIdTextField;
	private JPasswordField passwordField;
	private JLabel flashMessage;
	private HashMap<Integer, Employee> employeesList;
	public boolean isLoggedIn = false;
	
	/**
	 * Create the application.
	 * @param managersList 
	 */
	public LoginView(HashMap<Integer, Employee> list) {
		employeesList = list;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Employee Information System");
		this.setBounds(100, 100, 480, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel north_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) north_panel.getLayout();
		flowLayout.setVgap(20);
		north_panel.setBackground(new Color(51, 51, 51));
		panel.add(north_panel, BorderLayout.NORTH);
		
		JPanel south_panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) south_panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setAlignOnBaseline(true);
		flowLayout_1.setVgap(20);
		south_panel.setBackground(new Color(51, 51, 51));
		panel.add(south_panel, BorderLayout.SOUTH);
		
		flashMessage = new JLabel();
		flashMessage.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		south_panel.add(verticalStrut);
		south_panel.add(flashMessage);
		
		JPanel west_panel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) west_panel.getLayout();
		flowLayout_2.setHgap(40);
		west_panel.setBackground(new Color(51, 51, 51));
		panel.add(west_panel, BorderLayout.WEST);
		
		JPanel east_panel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) east_panel.getLayout();
		flowLayout_3.setHgap(40);
		east_panel.setBackground(new Color(51, 51, 51));
		panel.add(east_panel, BorderLayout.EAST);
		
		JPanel center_panel = new JPanel();
		center_panel.setBackground(new Color(51, 51, 51));
		panel.add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(new MigLayout("", "[][grow]", "[][30.00][][30.00][30.00][][30.00][35.00][30.00][]"));
		
		JLabel loginHeading = new JLabel("EMPLOYEE INFORMATION SYSTEM LOGIN");
		loginHeading.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		loginHeading.setForeground(new Color(234, 234, 234));
		center_panel.add(loginHeading, "cell 0 0 2 1,alignx center,aligny center");
		
		JLabel employeeIdLabel = new JLabel("Employee ID");
		employeeIdLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		employeeIdLabel.setForeground(new Color(234, 234, 234));
		center_panel.add(employeeIdLabel, "cell 1 2");
		
		employeeIdTextField = new JTextField();
		employeeIdTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		employeeIdTextField.setBackground(new Color(234, 234, 234));
		employeeIdTextField.setText("220002");
		center_panel.add(employeeIdTextField, "cell 1 3,grow");
		employeeIdTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(new Color(234, 234, 234));
		passwordLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		center_panel.add(passwordLabel, "cell 1 5");
		passwordLabel.setLabelFor(passwordField);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(234, 234, 234));
		passwordField.setText("1234");
		center_panel.add(passwordField, "cell 1 6,grow");
		passwordField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		loginButton.setBackground(new Color(0, 120, 215));
		center_panel.add(loginButton, "cell 1 8,alignx center,growy");
	}
	
	public String getEmployeeIdText() {
		return new String(employeeIdTextField.getText());
	}
	
	public String getPasswordText() {
		return new String(passwordField.getPassword());
	}


	public void actionPerformed(ActionEvent e) {
		int emp_id;
		if(getEmployeeIdText().matches("[0-9]+")) emp_id = Integer.valueOf(getEmployeeIdText());
		else emp_id = 0;
		try {
			if(emp_id == 0) throw new Exception();
			
			if(!employeesList.containsKey(emp_id)) {
				flashMessage.setText("Incorrect Employee ID");
				flashMessage.setForeground(new Color(215, 120, 0));
				flashMessage.setVisible(true);
			}else {
				if(getEmployeeIdText().equals(Integer.toString(employeesList.get(emp_id).getEmployeeId())) && 
						getPasswordText().equals(employeesList.get(emp_id).getJobPosition().getPassword())) {
					
					flashMessage.setText("Logging In");
					flashMessage.setForeground(new Color(55, 146, 255));
					flashMessage.setVisible(true);
					employeesList.get(emp_id).getJobPosition().isLoggedIn = true;
					
					App.updateEmployeesList(employeesList);
					proceedToHomeView(employeesList.get(emp_id));
				}else{
					flashMessage.setText("Incorrect Password");
					flashMessage.setForeground(new Color(215, 120, 0));
					flashMessage.setVisible(true);
				}
			}
		}catch(Exception err) {
			flashMessage.setText("Enter Employee ID");
			flashMessage.setForeground(new Color(215, 120, 0));
			flashMessage.setVisible(true);
		}
			
	}
	
	private void proceedToHomeView(Employee emp) {
		if(emp.getJobPosition().isLoggedIn) {
			System.out.println("Is Logged in? " + emp.getJobPosition().isLoggedIn);
			this.dispose();
			App.showHomeView();
		}
	}

}
