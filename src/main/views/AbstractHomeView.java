package main.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import main.employeesystem.App;
import main.models.Department;
import main.models.Employee;
import main.views.components.EmployeeActionButton;
import net.miginfocom.swing.MigLayout;


/**
 * 
 * @author Mwiinga Kayuni
 * @version 1.1
 * @implSpec AbstractHomeView HomeView layout and functionality and is extended by 
 * GeneralHomeView and HumanResourcesHomeView
 *
 */
public abstract class AbstractHomeView extends JFrame implements HomeViewListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField searchTextField = null;
	protected JButton searchButton = null;
	protected EmployeeActionButton viewEmployeeButton = null;
	protected EmployeeActionButton editEmployeeButton = null;
	
	protected JPanel panel = null;
	protected JPanel north_panel = null;
	protected JPanel south_panel = null;
	protected JPanel east_panel = null;
	protected JPanel west_panel = null;
	protected JPanel center_panel = null;
	
	protected JList<String> employeesListDisplay = null;
	protected JList<String> deptsListDisplay = null;
	protected static EmployeeDetailsView employeeDetailsView = null;
	protected static Employee selectedEmployee = null;
	protected static ArrayList<Employee> empVals = null;
	protected int selectedEmployeeIndex = 0;

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		this.setBounds(100, 100, 741, 570);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
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
		
		deptsListDisplay = new JList<String>();
		
		employeesListDisplay = new JList<String>();
		employeesListDisplay.setSelectionBackground(new Color(0, 120, 215));
		deptsListDisplay.addListSelectionListener(this);
		
		deptsListDisplay.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		deptsListDisplay.setForeground(new Color(214, 214, 214));
		deptsListDisplay.setBackground(new Color(60, 60, 60));
		
		ArrayList<String> deptListVals = new ArrayList<String>();
		
		Iterator<Department> itr = App.departmentsList.values().iterator();
		
		while(itr.hasNext()) {
			deptListVals.add(itr.next().getDepartmentName().toUpperCase());
		}
		
		deptsListDisplay.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			
			ArrayList<String> values = deptListVals;
			public int getSize() {
				return values.size();
			}
			public String getElementAt(int index) {
				return values.get(index);
			}
		});
		
		deptsListDisplay.setSelectedIndex(0);
		west_panel.add(deptsListDisplay, "cell 0 1 1 6,grow");
		
		east_panel = new JPanel();
		east_panel.setBackground(new Color(51, 51, 51));
		panel.add(east_panel, BorderLayout.EAST);
		
		center_panel = new JPanel();
		center_panel.setBackground(new Color(51, 51, 51));
		panel.add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 555, 423);
		center_panel.add(scrollPane);
		
		employeesListDisplay.addListSelectionListener(this);
		employeesListDisplay.setBorder(null);
		employeesListDisplay.setBackground(new Color(72, 72, 72));
		employeesListDisplay.setForeground(new Color(225, 225, 225));
		employeesListDisplay.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		
		scrollPane.setViewportView(employeesListDisplay);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setVgap(10);
		panel_1.setBackground(new Color(51, 51, 51));
		scrollPane.setColumnHeaderView(panel_1);
		
		viewEmployeeButton = new EmployeeActionButton(
				"View Employee", new Color(0, 120, 255), 369, 434);
		viewEmployeeButton.disableButton();
		viewEmployeeButton.addActionListener(this);

		
		center_panel.add(viewEmployeeButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(new Color(6, 162, 255));
		lblNewLabel_1.setBounds(10, 438, 84, 14);
		center_panel.add(lblNewLabel_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//View Selected Employee
		if(e.getSource().equals(viewEmployeeButton)) {
			try {
				if(employeeDetailsView != null) {
					if(employeeDetailsView.isVisible()) {
						System.out.println("Employee Details View Is Visible");
					}
					employeeDetailsView.setVisible(true);
					
				}else {
					selectedEmployeeIndex = employeesListDisplay.getSelectedIndex();
					selectedEmployee = empVals.get(selectedEmployeeIndex);
					employeeDetailsView = null;
					
					employeeDetailsView = new EmployeeDetailsView(selectedEmployee);
					
					employeesListDisplay.setSelectedIndex(selectedEmployeeIndex);
					employeesListDisplay.setSelectionBackground(new Color(163, 163, 163));
					
					viewEmployeeButton.enableButton();
					
					employeeDetailsView.setVisible(true);
				}
			}catch(Exception err) {
				err.printStackTrace();
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		//Department Selection Handling
		if(e.getSource().equals(deptsListDisplay)) {
			empVals = new ArrayList<Employee>();
			
			try {
				Iterator<Employee> empItr = App.departmentsList.get(deptsListDisplay.getSelectedValue())
						.getEmployeesList().values().iterator();
				while(empItr.hasNext()) {
					empVals.add(empItr.next());
				}
				
			}catch(NullPointerException err) {
				err.printStackTrace();
			}
			
			employeesListDisplay.setModel(new AbstractListModel<String>() {
				
				private static final long serialVersionUID = 1L;
				
				ArrayList<Employee> values = empVals;
				public int getSize() {
					return values.size();
				}
				
				public String getElementAt(int index) {
					return values.get(index).getEmployeeInfoFormatted();
				}
			});
			
			try {
				viewEmployeeButton.disableButton();
			}catch(Exception err) {
				System.out.println("View Employee Button is null");
			}
		}
		
		//Employee Selection Handling
		if(e.getSource().equals(employeesListDisplay)) {
			try {
				if(employeeDetailsView == null) {
					
					employeesListDisplay.setSelectionBackground(new Color(1, 120, 255));
					
					selectedEmployeeIndex = employeesListDisplay.getSelectedIndex();
					selectedEmployee = empVals.get(selectedEmployeeIndex);
					
					viewEmployeeButton.enableButton();
				}else {
					if(!employeeDetailsView.isShowing()) {
						System.out.println("Employee Details View isn't Showing");
						employeeDetailsView = null;
						
						employeesListDisplay.setSelectionBackground(new Color(1, 120, 255));
						
						selectedEmployeeIndex = employeesListDisplay.getSelectedIndex();
						selectedEmployee = empVals.get(selectedEmployeeIndex);
						viewEmployeeButton.enableButton();
					}
				}
			}catch(IndexOutOfBoundsException err) {
				System.out.println("Out of bounds index");
				selectedEmployeeIndex = employeesListDisplay.getSelectedIndex();
				viewEmployeeButton.disableButton();
			}
			
			if(editEmployeeButton != null) {
				editEmployeeButton.setEnabled(true);
			}
		}
	}
}
