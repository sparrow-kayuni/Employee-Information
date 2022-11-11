package main.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

import main.employeesystem.App;
import main.models.Department;
import main.models.JobPosition;
import main.views.factories.HomeFrameFactory;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JPasswordField;
import java.awt.Component;
import javax.swing.Box;

public class LoginFrame extends AbstractFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private JTextField jobIdTextField;
	private JPasswordField passwordField;
	private JLabel flashMessage;
	public boolean isLoggedIn = false;
	
	JButton loginButton = null;
	
	private final int width = 480;
	private final int height = 400;
	
	private JobPosition currentJobPositionLogin = null;
	
	/**
	 * Create the application.
	 * @param managersList 
	 */
	public LoginFrame(HashMap<String, Department> deptsMap) {		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Employee Information");
		
		int yPos = (App.screen.height / 2) - height/2;
		int xPos = (App.screen.width / 2) - width/2;
		
		this.setBounds(xPos, yPos, width, height);
		this.setResizable(false);
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
		center_panel.setLayout(new MigLayout("", "[][30.00][70.00][70.00][70.00][70.00][30.00,grow]", "[][30.00][][35.00][30.00][][35.00][50.00][35.00]"));
		
		JLabel loginHeading = new JLabel("EMPLOYEE INFORMATION LOGIN");
		loginHeading.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		loginHeading.setForeground(new Color(234, 234, 234));
		center_panel.add(loginHeading, "cell 0 0 7 1,alignx center,aligny center");
		
		JLabel jobIdLabel = new JLabel("Job ID");
		jobIdLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		jobIdLabel.setForeground(new Color(234, 234, 234));
		center_panel.add(jobIdLabel, "cell 2 2");
		
		jobIdTextField = new JTextField();
		jobIdTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jobIdTextField.setBackground(new Color(234, 234, 234));
		center_panel.add(jobIdTextField, "cell 2 3 4 1,grow");
		jobIdTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(new Color(234, 234, 234));
		passwordLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		center_panel.add(passwordLabel, "cell 2 5");
		passwordLabel.setLabelFor(passwordField);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(234, 234, 234));
		center_panel.add(passwordField, "cell 2 6 4 1,grow");
		passwordField.setColumns(10);
		
		loginButton = new JButton("Login");
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		loginButton.setBackground(new Color(0, 120, 215));
		center_panel.add(loginButton, "cell 3 8 2 1,grow");
	}
	
	public String getJobIdText() {
		return new String(jobIdTextField.getText());
	}
	
	public void setJobIdText(String text) {
		jobIdTextField.setText(text);
	}
	
	public String getPasswordText() {
		return new String(passwordField.getPassword());
	}
	
	public void setPasswordText(String text) {
		passwordField.setText(text);
	}
	
	public JButton getLoginButton() {
		return loginButton;
	}
	
	public JobPosition getCurrentJobPosition() {
		return currentJobPositionLogin;
	}
	
	public String getFlashMessage() {
		return flashMessage.getText();
	}

	public void actionPerformed(ActionEvent e) {
		int jobId;
		try {
			//Check if employee id text is a number
			if(!getJobIdText().matches("[0-9]+")) throw new Exception("Enter numbers only");
			
			jobId = Integer.valueOf(getJobIdText());

			if(!App.isLoginPositionPresent(jobId)) throw new Exception("User Doesn't Exist");
			
			currentJobPositionLogin = App.getLoginPosition(jobId);
			
			if(!getPasswordText().equals(currentJobPositionLogin.getPassword())) throw new Exception("Wrong Password");
			

			flashMessage.setText("Logging In");
			flashMessage.setForeground(new Color(55, 146, 255));
			flashMessage.setVisible(true);
			
			//log in employee
			App.logInEmployee(currentJobPositionLogin.getEmployee());
			
			if(App.getDepartment(currentJobPositionLogin.getEmployee()
					.getDepartmentName()).isLoggedIn) {
				proceedToHomeView();
			}
		}catch(Exception err) {
			flashMessage.setText(err.getMessage());
			flashMessage.setForeground(new Color(55, 146, 255));
			flashMessage.setVisible(true);
		}
	}
	
	
	private void proceedToHomeView() {
		
		App.setHomeFrame(HomeFrameFactory.createHomeView());
		
		if(App.getHomeFrame() != null) {
			this.dispose();
			App.getHomeFrame().setVisible(true);
		}		
	}

}
