package Views;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import EmployeeSystem.App;
import Models.Employee;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JScrollBar;
import java.awt.List;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class HomeView extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField searchTextField;
	private JButton searchButton;
	private JButton viewEmployeeButton;
	private JList<String> employeesListDisplay;

	/**
	 * Create the application.
	 */
	public HomeView() {
		setResizable(false);
		setTitle("Employee Information System");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 741, 570);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel north_panel = new JPanel();
		north_panel.setBackground(new Color(68, 68, 68));
		panel.add(north_panel, BorderLayout.NORTH);
		FlowLayout fl_north_panel = new FlowLayout(FlowLayout.RIGHT, 5, 15);
		fl_north_panel.setAlignOnBaseline(true);
		north_panel.setLayout(fl_north_panel);
		
		searchTextField = new JTextField();
		searchTextField.setText("Enter Text");
		north_panel.add(searchTextField);
		searchTextField.setColumns(20);
		
		searchButton = new JButton("Search");
		searchButton.setBackground(new Color(140, 140, 140));
		searchButton.setFocusable(false);
		
		north_panel.add(searchButton);
		
		JPanel south_panel = new JPanel();
		south_panel.setBackground(new Color(51, 51, 51));
		panel.add(south_panel, BorderLayout.SOUTH);
		
		JPanel west_panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) west_panel.getLayout();
		flowLayout_1.setHgap(80);
		west_panel.setBackground(new Color(60, 60, 60));
		panel.add(west_panel, BorderLayout.WEST);
		
		JPanel east_panel = new JPanel();
		east_panel.setBackground(new Color(51, 51, 51));
		panel.add(east_panel, BorderLayout.EAST);
		
		JPanel center_panel = new JPanel();
		center_panel.setBackground(new Color(51, 51, 51));
		panel.add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 555, 423);
		center_panel.add(scrollPane);
		
		employeesListDisplay = new JList<String>();
		employeesListDisplay.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(employeesListDisplay.getSelectedIndex() != -1) {
					viewEmployeeButton.setBackground(new Color(0, 120, 255));
					viewEmployeeButton.setEnabled(true);
				}
			}
		});
		employeesListDisplay.setBorder(null);
		employeesListDisplay.setBackground(new Color(72, 72, 72));
		employeesListDisplay.setForeground(new Color(225, 225, 225));
		employeesListDisplay.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		String[] vals = new String[App.employeesList.size()];
		
		for(int i = 0; i < vals.length; i++) {
			vals[i] = App.employeesList.get(i).getEmployeeInfoFormatted();
		}
		employeesListDisplay.setModel(new AbstractListModel<String>() {
			
			private static final long serialVersionUID = 1L;
			
			String[] values = vals;
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		scrollPane.setViewportView(employeesListDisplay);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setVgap(10);
		panel_1.setBackground(new Color(51, 51, 51));
		scrollPane.setColumnHeaderView(panel_1);
		
		viewEmployeeButton = new JButton("View Employee Details");
		viewEmployeeButton.addActionListener(this);
		viewEmployeeButton.setBackground(new Color(140, 140, 140));
		viewEmployeeButton.setEnabled(false);
		viewEmployeeButton.setFocusable(false);
		viewEmployeeButton.setBounds(373, 434, 172, 23);
		
		center_panel.add(viewEmployeeButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
