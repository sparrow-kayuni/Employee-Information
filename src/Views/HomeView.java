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
import java.awt.event.ActionEvent;

public class HomeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;

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
		FlowLayout fl_north_panel = new FlowLayout(FlowLayout.RIGHT, 5, 25);
		fl_north_panel.setAlignOnBaseline(true);
		north_panel.setLayout(fl_north_panel);
		
		txtSearch = new JTextField();
		txtSearch.setText("Enter Text");
		north_panel.add(txtSearch);
		txtSearch.setColumns(20);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBackground(new Color(0, 120, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		north_panel.add(btnNewButton);
		
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
		scrollPane.setBounds(0, 0, 555, 461);
		center_panel.add(scrollPane);
		
		JList list = new JList();
		list.setBorder(null);
		list.setBackground(new Color(72, 72, 72));
		list.setForeground(new Color(225, 225, 225));
		list.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"2109399 Mwiinga Kayuni Downin Street", "2033099 Camilla Mtoloka Avianno Verspucci", "3211032 Chris Hemsworth Australia", "23003w1 Camry Cars Leaue Of Legends", "2373949 Thabo Mbeki South Africa", "2363919 Micasa Su Casa London Britain", "2918393 Cherry Pickers Everywhere", "2173930 Christaino Ronaldo Tritimane", "2910393 Cali Linux I F*cked Your Mum", "2109399 Mwiinga Kayuni Downin Street", "2033099 Camilla Mtoloka Avianno Verspucci", "3211032 Chris Hemsworth Australia", "23003w1 Camry Cars Leaue Of Legends", "2373949 Thabo Mbeki South Africa", "2363919 Micasa Su Casa London Britain", "2918393 Cherry Pickers Everywhere", "2173930 Christaino Ronaldo Tritimane", "2910393 Cali Linux I F*cked Your Mum", "2910393 Cali Linux I F*cked Your Mum", "2109399 Mwiinga Kayuni Downin Street", "2033099 Camilla Mtoloka Avianno Verspucci", "3211032 Chris Hemsworth Australia", "23003w1 Camry Cars Leaue Of Legends", "2373949 Thabo Mbeki South Africa", "2363919 Micasa Su Casa London Britain", "2918393 Cherry Pickers Everywhere"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setVgap(10);
		panel_1.setBackground(new Color(51, 51, 51));
		scrollPane.setColumnHeaderView(panel_1);
	}
}
