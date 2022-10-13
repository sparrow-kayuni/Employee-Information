package main.views;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

import main.models.Employee;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeDetailsView extends JDialog implements ActionListener {


	private static final long serialVersionUID = 1L;
	private static Employee employee;
	private JButton backButton;

	/**
	 * Create the application.
	 */
	public EmployeeDetailsView(Employee emp) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
			}
			
		});
		setResizable(false);
		employee = emp;
		setTitle("Employee Details");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 560, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(69, 69, 69));
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel west_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) west_panel.getLayout();
		flowLayout.setHgap(20);
		west_panel.setBackground(new Color(69, 69, 69));
		panel.add(west_panel, BorderLayout.WEST);
		
		JPanel north_panel = new JPanel();
		north_panel.setBackground(new Color(69, 69, 69));
		panel.add(north_panel, BorderLayout.NORTH);
		north_panel.setLayout(new MigLayout("", "[40.00][32.00][][][]", "[35.00]"));
		
		JLabel employeeNameHeader = new JLabel(employee.getFirstName().toUpperCase() + " "
				+ employee.getSurname().toUpperCase());
		employeeNameHeader.setForeground(new Color(215, 215, 215));
		employeeNameHeader.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		north_panel.add(employeeNameHeader, "cell 1 0 2 1");
		
		JPanel south_panel = new JPanel();
		south_panel.setBackground(new Color(69, 69, 69));
		panel.add(south_panel, BorderLayout.SOUTH);
		south_panel.setLayout(new MigLayout("", "[100.00][100.00][100.00][100.00][100.00][100.00][100.00][100.00][100.00][100.00]", "[45.00]"));
		
		backButton = new JButton("Close");
		backButton.addActionListener(this);
		backButton.setBackground(new Color(131, 131, 131));
		south_panel.add(backButton, "cell 7 0 2 1,growx,aligny center");
		
		JPanel east_panel = new JPanel();
		east_panel.setBackground(new Color(69, 69, 69));
		panel.add(east_panel, BorderLayout.EAST);
		
		JPanel center_panel = new JPanel();
		center_panel.setBackground(new Color(69, 69, 69));
		panel.add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(new MigLayout("", "[][40.00][50.00,grow][][114.00,grow][50.00]", "[17.00,grow][20.00][grow][25.00,grow][grow][20.00][25.00,grow][20.00][25.00,grow][20.00][25.00,grow][20.00][25.00][20.00,grow][25.00]"));
		
		JLabel employeeIdLabel = new JLabel("EMPLOYEE ID");
		employeeIdLabel.setForeground(new Color(215, 215, 215));
		employeeIdLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(employeeIdLabel, "cell 0 0");
		
		JTextPane employeeIdTextPane = new JTextPane();
		employeeIdTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		employeeIdTextPane.setBackground(new Color(167, 167, 167));
		employeeIdTextPane.setText(Integer.toString(employee.getEmployeeId()));
		employeeIdTextPane.setEditable(false);
		center_panel.add(employeeIdTextPane, "cell 2 0 3 1,growx,aligny baseline");
		
		JLabel firstNameLabel = new JLabel("FIRST NAME");
		firstNameLabel.setForeground(new Color(215, 215, 215));
		firstNameLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(firstNameLabel, "cell 0 2");
		
		JTextPane firstNameTextPane = new JTextPane();
		firstNameTextPane.setText(employee.getFirstName());
		firstNameTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		firstNameTextPane.setEditable(false);
		firstNameTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(firstNameTextPane, "cell 2 2 3 1,grow");
		
		JLabel surnameLabel = new JLabel("SURNAME");
		surnameLabel.setForeground(new Color(215, 215, 215));
		surnameLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(surnameLabel, "cell 0 4");
		
		JTextPane surnameTextPane = new JTextPane();
		surnameTextPane.setText(employee.getSurname());
		surnameTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		surnameTextPane.setEditable(false);
		surnameTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(surnameTextPane, "cell 2 4 3 1,grow");
		
		JLabel jobTitleLabel = new JLabel("JOB TITLE");
		jobTitleLabel.setForeground(new Color(215, 215, 215));
		jobTitleLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(jobTitleLabel, "cell 0 6");
		
		JTextPane jobTitleTextPane = new JTextPane();
		jobTitleTextPane.setText(employee.getJobPosition().getJobTitle());
		jobTitleTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		jobTitleTextPane.setEditable(false);
		jobTitleTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(jobTitleTextPane, "cell 2 6 3 1,growx,aligny baseline");
		
		JLabel departmentLabel = new JLabel("DEPARTMENT");
		departmentLabel.setForeground(new Color(215, 215, 215));
		departmentLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(departmentLabel, "cell 0 8");
		
		JTextPane departmentTextPane = new JTextPane();
		departmentTextPane.setText(employee.getJobPosition().getDepartment().getDepartmentName());
		departmentTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		departmentTextPane.setEditable(false);
		departmentTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(departmentTextPane, "cell 2 8 3 1,grow");
		
		JLabel phoneLabel = new JLabel("PHONE");
		phoneLabel.setForeground(new Color(215, 215, 215));
		phoneLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(phoneLabel, "cell 0 10");
		
		JTextPane phoneTextPane = new JTextPane();
		phoneTextPane.setText(employee.getPhoneNumber());
		phoneTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		phoneTextPane.setEditable(false);
		phoneTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(phoneTextPane, "cell 2 10 3 1,grow");
		
		JLabel emailLabel = new JLabel("EMAIL");
		emailLabel.setForeground(new Color(215, 215, 215));
		emailLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(emailLabel, "cell 0 12");
		
		JTextPane emailTextPane = new JTextPane();
		emailTextPane.setText(employee.getEmail());
		emailTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		emailTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(emailTextPane, "cell 2 12 3 1,grow");
		
		JLabel hourlyPayLabel = new JLabel("HOURLY PAY ($)");
		hourlyPayLabel.setForeground(new Color(215, 215, 215));
		hourlyPayLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(hourlyPayLabel, "cell 0 14");
		
		JTextPane hourlyPayTextPane = new JTextPane();
		hourlyPayTextPane.setText(Float.toString(employee.getJobPosition().getHourlyPay()));
		hourlyPayTextPane.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		hourlyPayTextPane.setEditable(false);
		hourlyPayTextPane.setBackground(new Color(167, 167, 167));
		center_panel.add(hourlyPayTextPane, "cell 2 14 3 1,grow");
	}
	
	public Employee getEmployee() {
		return employee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backButton)) {
			this.dispose();
		}
		
	}

}
