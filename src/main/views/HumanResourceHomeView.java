package main.views;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;

public class HumanResourceHomeView extends AbstractHomeView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton editEmployeeButton = null;

	public HumanResourceHomeView() {
		setResizable(false);
		setTitle("Employee Information System (HR)");
		initialize();
		addEditButton();
	}

	private void addEditButton() {
		System.out.println("Added Edit Button");
		
		editEmployeeButton = new JButton("Edit Employee Details");
		editEmployeeButton.addActionListener(this);
		editEmployeeButton.setBackground(new Color(140, 140, 140));
		editEmployeeButton.setEnabled(false);
		editEmployeeButton.setFocusable(false);
		editEmployeeButton.setBounds(187, 434, 172, 23);
		
		center_panel.add(editEmployeeButton);
	}
	
//	public void valueChanged(ListSelectionEvent e) {
//		if(e.getSource().equals(employeesListDisplay)) {
//			editEmployeeButton.setBackground(new Color(120, 0, 215));
//			editEmployeeButton.setEnabled(true);
//		}
//	}
}
