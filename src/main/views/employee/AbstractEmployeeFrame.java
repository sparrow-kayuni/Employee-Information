package main.views.employee;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import main.models.Employee;
import main.views.listeners.DefaultActionListener;

import javax.swing.JButton;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec AbstractEmployeeFrame contains base frame components (panels, labels and close button)
 *
 */
public class AbstractEmployeeFrame extends JFrame implements DefaultActionListener{

	protected static final long serialVersionUID = 1L;
	protected static Employee employee = null;
	protected JButton closeButton = null;
	
	protected JPanel panel;
	protected JPanel west_panel;
	protected JPanel north_panel;
	protected JPanel south_panel;
	protected JPanel east_panel;
	protected JPanel center_panel;
	
	protected JLabel frameHeader;


	/**
	 * Initialize the contents of the frame.
	 */
	protected void initializePanels() {
		setResizable(false);
		
		this.setBounds(100, 100, 560, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(69, 69, 69));
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		west_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) west_panel.getLayout();
		flowLayout.setHgap(20);
		west_panel.setBackground(new Color(69, 69, 69));
		panel.add(west_panel, BorderLayout.WEST);
		
		north_panel = new JPanel();
		north_panel.setBackground(new Color(69, 69, 69));
		panel.add(north_panel, BorderLayout.NORTH);
		north_panel.setLayout(new MigLayout("", "[100.00][100.00][100.00][100.00][100.00][100.00][100.00][100.00][100.00][100.00]", "[10.00]"));
		
		south_panel = new JPanel();
		south_panel.setBackground(new Color(69, 69, 69));
		panel.add(south_panel, BorderLayout.SOUTH);
		south_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		east_panel = new JPanel();
		east_panel.setBackground(new Color(69, 69, 69));
		east_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		panel.add(east_panel, BorderLayout.EAST);
		
		center_panel = new JPanel();
		center_panel.setBackground(new Color(69, 69, 69));
		panel.add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(new MigLayout("", "[100.00][30.00][100.00,grow][100.00,grow][100.00][30.00][100.00,grow][40.00]", "[35.00][15.00][35.00,grow][20.00][35.00,grow][20.00,grow][35.00,grow][20.00][35.00,grow][20.00][35.00,grow][20.00][35.00,grow][20.00][35.00][20.00,grow][35.00][30.00][30.00]"));
		
		JLabel employeeNameHeader = new JLabel(employee.getFirstName().toUpperCase() 
				+ " " + employee.getSurname().toUpperCase());
		employeeNameHeader.setHorizontalAlignment(SwingConstants.CENTER);
		employeeNameHeader.setForeground(new Color(215, 215, 215));
		employeeNameHeader.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		center_panel.add(employeeNameHeader, "cell 0 0,alignx center,aligny center");
		
		JLabel employeeIdLabel = new JLabel("EMPLOYEE ID");
		employeeIdLabel.setForeground(new Color(215, 215, 215));
		employeeIdLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(employeeIdLabel, "cell 0 2");
				
		JLabel firstNameLabel = new JLabel("FIRST NAME");
		firstNameLabel.setForeground(new Color(215, 215, 215));
		firstNameLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(firstNameLabel, "cell 0 4");
				
		JLabel surnameLabel = new JLabel("SURNAME");
		surnameLabel.setForeground(new Color(215, 215, 215));
		surnameLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(surnameLabel, "cell 0 6");
		
		JLabel departmentLabel = new JLabel("DEPARTMENT");
		departmentLabel.setForeground(new Color(215, 215, 215));
		departmentLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(departmentLabel, "cell 0 8");
				
		JLabel jobTitleLabel = new JLabel("JOB TITLE");
		jobTitleLabel.setForeground(new Color(215, 215, 215));
		jobTitleLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(jobTitleLabel, "cell 0 10");
		
		JLabel phoneLabel = new JLabel("PHONE");
		phoneLabel.setForeground(new Color(215, 215, 215));
		phoneLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(phoneLabel, "cell 0 12");
		
		JLabel emailLabel = new JLabel("EMAIL");
		emailLabel.setForeground(new Color(215, 215, 215));
		emailLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(emailLabel, "cell 0 14");
		
		JLabel hourlyPayLabel = new JLabel("HOURLY PAY ($)");
		hourlyPayLabel.setForeground(new Color(215, 215, 215));
		hourlyPayLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		center_panel.add(hourlyPayLabel, "cell 0 16");
		
		closeButton = new JButton("Close");
		closeButton.setFocusable(false);
		closeButton.setBackground(new Color(131, 131, 131));
		closeButton.addActionListener(this);
		center_panel.add(closeButton, "cell 6 18,growx");
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	protected void setEmployee(Employee emp) {
		employee = emp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(closeButton)) {
			this.dispose();
		}
	}

}
