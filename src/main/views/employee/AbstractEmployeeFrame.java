package main.views.employee;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import main.employeesystem.App;
import main.models.Department;
import main.models.Employee;
import main.models.JobPosition;
import main.views.AbstractFrame;
import main.views.components.CloseActionButton;
import main.views.events.CloseEvent;
import main.views.listeners.FrameClosedListener;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec AbstractEmployeeFrame contains base frame components (panels, labels and close button)
 *
 */
public abstract class AbstractEmployeeFrame extends AbstractFrame implements ActionListener {

	protected static final long serialVersionUID = 1L;
	protected Employee currentEmployee = null;
	protected Department currentDepartment = null;
	protected JobPosition currentJobPosition = null;
	protected boolean hasInfo = false;
	
	protected CloseActionButton closeButton = null;
	private ArrayList<AbstractFrame> listeners = new ArrayList<AbstractFrame>();
	
	protected JPanel panel;
	protected JPanel west_panel;
	protected JPanel north_panel;
	protected JPanel south_panel;
	protected JPanel east_panel;
	protected JPanel center_panel;
	
	protected JLabel frameHeader;
	protected JLabel employeeNameHeader;

	protected final int width = 600;
	protected final int height = 500;
	
	//specifies what caused the frame to close
	protected CloseEvent.Event closedEvent = null;
	
	public void setClosedEvent(CloseEvent.Event event) {
		closedEvent = event;
	}
	
	public CloseEvent.Event getClosedEvent() {
		return this.closedEvent;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initializePanels() {
		setResizable(false);
		
		int yPos = (App.screen.height / 2) - height / 2;
		int xPos = (App.screen.width / 2) - width / 2;
				
		this.setBounds(xPos, yPos, width, height);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if(getClosedEvent() == null) setClosedEvent(CloseEvent.Event.CANCEL);
				notifyClosedListeners(getClosedEvent());
			}
		});
		
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
		
		this.employeeNameHeader = new JLabel();
		this.employeeNameHeader.setHorizontalAlignment(SwingConstants.CENTER);
		this.employeeNameHeader.setForeground(new Color(215, 215, 215));
		this.employeeNameHeader.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		center_panel.add(this.employeeNameHeader, "cell 0 0,alignx center,aligny center");
		
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
		
		closeButton = new CloseActionButton("Back");
		closeButton.setFocusable(false);
		closeButton.setBackground(new Color(131, 131, 131));
		
		center_panel.add(closeButton, "cell 6 18,growx");
	}
	
	public Employee getEmployee() {
		return this.currentEmployee;
	}
	
	//sets value of fframe header
	protected void setFrameHeader() {
		this.employeeNameHeader.setText(currentEmployee.getFirstName().toUpperCase() 
				+ " " + currentEmployee.getSurname().toUpperCase());
	}
	
	//sets current employee information
	protected void setEmployeeInformation(Employee emp) {
		String jobTitle = null;
		
		if(emp != null) {
			this.currentEmployee = emp;
			this.currentDepartment = App.getDepartment(currentEmployee.getDepartmentName());
			jobTitle = currentDepartment.getJobTitle(currentEmployee.getJobId());
			this.currentJobPosition = this.currentDepartment.getJobPosition(jobTitle);
		}
	}
	
	//registers frames that will be notified when this frame is closed
	public void addClosedListener(AbstractFrame frame) {
		this.listeners.add(frame);
	}
	
	//notifies listeners
	public void notifyClosedListeners(CloseEvent.Event eventType) {
		for(int i = 0; i < this.listeners.size(); i++) {
			((FrameClosedListener) this.listeners.get(i))
				.onEmployeeFrameClosed(new CloseEvent(this, eventType));
		}
	}
}
