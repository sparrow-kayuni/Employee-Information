package main.views.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;

import main.employeesystem.App;
import main.models.Department;
import main.models.Employee;
import main.models.JobPosition;
import main.views.AbstractFrame;
import main.views.components.EmployeeActionButton;
import main.views.dialogs.SaveChangesDialog;
import main.views.employee.AbstractEmployeeFrame;
import main.views.employee.AddEmployeeFrame;
import main.views.factories.EmployeeFrameFactory;
import main.views.listeners.EmployeeUpdateListener;
import main.views.listeners.HomeViewListener;
import net.miginfocom.swing.MigLayout;


/**
 * 
 * @author Mwiinga Kayuni
 * @version 1.1
 * @implSpec AbstractHomeFrame contains base Home components
 *
 */
public abstract class AbstractHomeFrame extends AbstractFrame implements HomeViewListener, EmployeeUpdateListener {

	private static final long serialVersionUID = 1L;
	
	protected JTextField searchTextField = null;
	protected JButton searchButton = null;
	protected EmployeeActionButton viewEmployeeButton = null;
	protected EmployeeActionButton editEmployeeButton = null;
	protected EmployeeActionButton addEmployeeButton = null;
	protected JLabel headerLabel;
	protected SaveChangesDialog saveEmployeeDialog = null;
	
	protected JPanel panel = null;
	protected JPanel north_panel = null;
	protected JPanel south_panel = null;
	protected JPanel east_panel = null;
	protected JPanel west_panel = null;
	protected JPanel center_panel = null;
	
	protected JList<String> employeesDisplay = null;
	protected JList<String> departmentsDisplay = null;
	
	protected static AbstractEmployeeFrame viewEmployeeFrame = null;
	protected static Employee selectedEmployee = null;
	protected static ArrayList<Employee> empVals = null;
	protected static ArrayList<String> deptListVals = null;
	
	protected static boolean searchMode = false;
	
	protected int selectedEmployeeIndex = 0;
	
	private final int width = 810;
	private final int height = 660;

	
	protected void initialize() {
		setResizable(false);
		
		int yPos = (App.screen.height / 2) - height/2;
		int xPos = (App.screen.width / 2) - width/2;
		
		this.setBounds(xPos, yPos, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		var panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		north_panel = new JPanel();
		north_panel.setBackground(new Color(68, 68, 68));
		panel.add(north_panel, BorderLayout.NORTH);
		FlowLayout fl_north_panel = new FlowLayout(FlowLayout.RIGHT, 5, 15);
		fl_north_panel.setAlignOnBaseline(true);
		north_panel.setLayout(fl_north_panel);
		
		searchTextField = new JTextField();
		searchTextField.setText("Enter Text Here");
		north_panel.add(searchTextField);
		searchTextField.setColumns(20);
		
		searchButton = new JButton("Search");
		searchButton.setBackground(new Color(140, 140, 140));
		searchButton.addActionListener(this);
		searchButton.setFocusable(false);
		
		north_panel.add(searchButton);
		
		south_panel = new JPanel();
		south_panel.setBackground(new Color(51, 51, 51));
		panel.add(south_panel, BorderLayout.SOUTH);
		
		west_panel = new JPanel();
		west_panel.setBackground(new Color(60, 60, 60));
		panel.add(west_panel, BorderLayout.WEST);
		west_panel.setLayout(new MigLayout("", "[150.00]", "[32px][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("DEPARTMENTS");
		lblNewLabel.setForeground(new Color(214, 214, 214));
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		west_panel.add(lblNewLabel, "cell 0 0");
		
		departmentsDisplay = new JList<String>();
		
		employeesDisplay = new JList<String>();
		employeesDisplay.setSelectionBackground(new Color(0, 120, 215));
		departmentsDisplay.addListSelectionListener(this);
		
		departmentsDisplay.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		departmentsDisplay.setForeground(new Color(214, 214, 214));
		departmentsDisplay.setBackground(new Color(60, 60, 60));
		
		//Set department list values
		deptListVals = new ArrayList<String>();
		deptListVals.add("ALL DEPARTMENTS");
		
		Iterator<Department> deptItr = App.getDepartments().values().iterator();
		while(deptItr.hasNext()) {
			deptListVals.add(deptItr.next().getDepartmentName());
		}
		
		departmentsDisplay.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			
			ArrayList<String> values = deptListVals;
			public int getSize() {
				return values.size();
			}
			public String getElementAt(int index) {
				return values.get(index);
			}
		});
		
		departmentsDisplay.setSelectedIndex(0);
		west_panel.add(departmentsDisplay, "cell 0 1 1 6,grow");
		
		east_panel = new JPanel();
		east_panel.setBackground(new Color(51, 51, 51));
		panel.add(east_panel, BorderLayout.EAST);
		
		center_panel = new JPanel();
		center_panel.setBackground(new Color(51, 51, 51));
		panel.add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(new MigLayout("", "[100.00][100.00][100.00][10.00][120.00][10.00][120.00]", "[25.00][10.00][25.00][45.00][45.00][45.00][45.00][45.00][45.00][45.00][45.00][40.00][9.00][25.00]"));
		
		headerLabel = new JLabel(departmentsDisplay.getSelectedValue());
		headerLabel.setForeground(new Color(210, 210, 210));
		headerLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		center_panel.add(headerLabel, "cell 0 0");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		center_panel.add(scrollPane, "cell 0 2 7 10,grow");
		
		employeesDisplay.addListSelectionListener(this);
		employeesDisplay.setBorder(null);
		employeesDisplay.setBackground(new Color(72, 72, 72));
		employeesDisplay.setForeground(new Color(225, 225, 225));
		employeesDisplay.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		
		scrollPane.setViewportView(employeesDisplay);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(new Color(6, 162, 255));
		lblNewLabel_1.setBounds(10, 438, 84, 14);
		center_panel.add(lblNewLabel_1, "cell 2 3");
		
		viewEmployeeButton = new EmployeeActionButton("View Employee", new Color(0, 120, 255));
		viewEmployeeButton.disableButton();
		viewEmployeeButton.addActionListener(this);
		
		center_panel.add(viewEmployeeButton, "cell 6 13,grow");
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//View Selected Employee
		if(e.getSource().equals(viewEmployeeButton)) {
			try {
				//if employee view frame exists, bring current employee view frame to front
				if(viewEmployeeFrame != null) {
					viewEmployeeFrame.setVisible(true);
					
				}else {
					//if employee view frame exists, create new employee view frame with the current selected employee
					selectedEmployeeIndex = employeesDisplay.getSelectedIndex();
					selectedEmployee = empVals.get(selectedEmployeeIndex);
					viewEmployeeFrame = null;
					
					viewEmployeeFrame = EmployeeFrameFactory.createViewEmployeeFrame(this, selectedEmployee);
					
					employeesDisplay.setSelectedIndex(selectedEmployeeIndex);
					employeesDisplay.setSelectionBackground(new Color(163, 163, 163));
					
					viewEmployeeButton.enableButton();
					
					viewEmployeeFrame.setVisible(true);
				}
			}catch(Exception err) {
				err.printStackTrace();
			}
		}
		
		//
		if(e.getSource().equals(addEmployeeButton)) {
			if(addEmployeeButton != null) {
				String deptName = null;
				
				//if department selected is "ALL DEPARTMENTS", set, by default, to EXECUTIVE department
				if(departmentsDisplay.getSelectedValue().equals("ALL DEPARTMENTS")) deptName = "EXECUTIVE"; 
				else deptName = departmentsDisplay.getSelectedValue();
				
				AddEmployeeFrame addEmployeeFrame = EmployeeFrameFactory.createAddEmployeeFrame(deptName);
				addEmployeeFrame.setVisible(true);
			}
		}
		
		
		//search handling
		if(e.getSource().equals(searchButton)) {
			if(searchTextField.getText().equals("Enter Text Here") || searchTextField.getText().equals("")) {
				//Enter valid text
			}
			empVals = new ArrayList<Employee>();
			
			String search = searchTextField.getText();
			
			deptListVals.add("SEARCH RESULTS");
			
			searchMode = true;
			
			departmentsDisplay.setModel(new AbstractListModel<String>() {
				private static final long serialVersionUID = 1L;
				
				ArrayList<String> values = deptListVals;
				public int getSize() {
					return values.size();
				}
				public String getElementAt(int index) {
					return values.get(index);
				}
			});
			
			departmentsDisplay.setSelectedIndex(6);
			
			headerLabel = new JLabel(departmentsDisplay.getSelectedValue());
			
			Iterator<Department> deptItr = App.getDepartments().values().iterator();
			while(deptItr.hasNext()) {
				Department dept = deptItr.next();
				JobPosition[] jobs = dept.getJobPositions().values()
						.toArray(new JobPosition[dept.getJobPositions().size()]);
				for(int i = 0; i < jobs.length; i++) {
					if(jobs[i].isFilled) {
						if(jobs[i].getEmployee().getFirstName().matches(String.format("\\w{0,}%s\\w{0,}", search)) || 
								jobs[i].getEmployee().getSurname().matches(String.format("\\w{0,}%s\\w{0,}", search))) {
							empVals.add(jobs[i].getEmployee());
						}
					}
				}
			}
			
			//sort employees by employee id
			empVals.sort((Comparator<? super Employee>) new Employee());
			
			//show job positions of selected department
			employeesDisplay.setModel(new AbstractListModel<String>() {
				private static final long serialVersionUID = 1L;
				
				ArrayList<Employee> values = empVals;
				public int getSize() {
					return values.size();
				}
				
				public String getElementAt(int index) {
					return values.get(index).getEmployeeInfoFormatted();
				}
			});
		}
	}

	
	//
	protected void refreshEmployeesDisplay() {
		empVals = new ArrayList<Employee>();
		Department dept = null;
		
		try {
			
			//when selected department is "ALL DEPARTMENTS", iterate through all departments and their job positions
			if(departmentsDisplay.getSelectedValue().equals("ALL DEPARTMENTS")) {
				Iterator<Department> deptItr = App.getDepartments().values().iterator();
				
				while(deptItr.hasNext()) {
					dept = deptItr.next();
					
					//get employees from filled job positions from all departments
					Iterator<JobPosition> jobItr  = dept.getJobPositions().values().iterator();
					while(jobItr.hasNext()) {
						JobPosition job = jobItr.next();
						if(job.isFilled) empVals.add(job.getEmployee());
					}
				}
			}else {
				//get selected department object
				dept = App.getDepartments().get(departmentsDisplay.getSelectedValue());
				
				//get employees from filled job positions from selected department object
				Iterator<JobPosition> jobItr  = dept.getJobPositions().values().iterator();
				while(jobItr.hasNext()) {
					JobPosition job = jobItr.next();
					if(job.isFilled) empVals.add(job.getEmployee());
				}
			}
		}catch(NullPointerException err) {
			err.printStackTrace();
		}
		
		//Set Header to Department name
		if(headerLabel != null) {
			if(departmentsDisplay.getSelectedValue().equals("ALL DEPARTMENTS")) {
				headerLabel.setText(departmentsDisplay.getSelectedValue());
			}else {
				headerLabel.setText(departmentsDisplay.getSelectedValue() + " DEPARTMENT");
			}
		}
		
		//sort employees by employee id
		empVals.sort((Comparator<? super Employee>) new Employee());
		
		//show job positions of selected department
		employeesDisplay.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			
			ArrayList<Employee> values = empVals;
			public int getSize() {
				return values.size();
			}
			
			public String getElementAt(int index) {
				return values.get(index).getEmployeeInfoFormatted();
			}
		});
		
		//Disable view employee button
		try {
			viewEmployeeButton.disableButton();
		}catch(Exception err) {
//			System.out.println("View Employee Button is null");
		}
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		// Department Selection Handling
		if(e.getSource().equals(departmentsDisplay)) {
			if(!searchMode) refreshEmployeesDisplay();
		}
		
		//Employee Selection Handling
		if(e.getSource().equals(employeesDisplay)) {
			try {
				
				//if employee view doesn't exist, enable all buttons
				if(viewEmployeeFrame == null) {
					employeesDisplay.setSelectionBackground(new Color(1, 120, 255));
					
					selectedEmployeeIndex = employeesDisplay.getSelectedIndex();
					selectedEmployee = empVals.get(selectedEmployeeIndex);
					
					viewEmployeeButton.enableButton();
					
					if(editEmployeeButton != null && addEmployeeButton != null) {
						editEmployeeButton.enableButton();
						addEmployeeButton.enableButton();
					}
				}else {
					
					//If employee view exists, enable view button
					if(!viewEmployeeFrame.isShowing()) {
						viewEmployeeFrame = null;
						
						employeesDisplay.setSelectionBackground(new Color(1, 120, 255));
						
						selectedEmployeeIndex = employeesDisplay.getSelectedIndex();
						selectedEmployee = empVals.get(selectedEmployeeIndex);
						
						viewEmployeeButton.enableButton();
						
						if(editEmployeeButton != null) {
							editEmployeeButton.enableButton();
						}
					}else {
						addEmployeeButton.disableButton();
					}
				}
			}catch(IndexOutOfBoundsException err) {
//				System.out.println("Out of bounds index");
				selectedEmployeeIndex = employeesDisplay.getSelectedIndex();
				viewEmployeeButton.disableButton();
			}
		}
	}
}
