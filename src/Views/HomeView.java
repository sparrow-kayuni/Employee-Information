package Views;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

import EmployeeSystem.App;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import net.miginfocom.swing.MigLayout;

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
		searchTextField.setText("Enter Text Here");
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
		west_panel.setBackground(new Color(60, 60, 60));
		panel.add(west_panel, BorderLayout.WEST);
		west_panel.setLayout(new MigLayout("", "[150.00]", "[32px][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("DEPARTMENTS");
		lblNewLabel.setForeground(new Color(214, 214, 214));
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		west_panel.add(lblNewLabel, "cell 0 0");
		
		JList<String> list = new JList<String>();
		list.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		list.setForeground(new Color(214, 214, 214));
		list.setBackground(new Color(60, 60, 60));
		list.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			
			String[] values = new String[] {"ALL DEPARTMENTS", "ACCOUNTS", "EXECUTIVE", "HUMAN RESOURCES", "SALES", "PURCHASING"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		west_panel.add(list, "cell 0 1 1 6,grow");
		
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
			vals[i] = App.employeesList.get(220000 + i).getEmployeeInfoFormatted();
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
		viewEmployeeButton.setBounds(369, 434, 172, 23);
		
		center_panel.add(viewEmployeeButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
