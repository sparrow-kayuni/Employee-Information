package main.views.home;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionEvent;

import main.models.Employee;
import main.views.employee.AbstractViewEmployeeFrame;
import main.views.employee.GeneralViewEmployeeFrame;
import main.views.events.CloseEvent;
import main.views.events.UpdateEvent;
import main.views.factories.EmployeeFrameFactory;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec GeneralHomeFrame is an implementation of AbstractHomeFrame for basic use  
 *
 */
public class GeneralHomeFrame extends AbstractHomeFrame {
	
	private static final long serialVersionUID = 1L;
	private static GeneralViewEmployeeFrame viewEmployeeFrame = null;

	/**
	 * Create the application.
	 */
	public GeneralHomeFrame() {
		setTitle("Employee Information");
		initialize();
	}
	
	@Override
	public AbstractViewEmployeeFrame getViewEmployeeFrame() {
		return viewEmployeeFrame;
	}
	
	//
	private GeneralViewEmployeeFrame createViewEmployeeFrame(Employee emp) {
		viewEmployeeFrame = (GeneralViewEmployeeFrame) 
				EmployeeFrameFactory.createViewEmployeeFrame(this, emp);
		viewEmployeeFrame.addClosedListener(this);
		viewEmployeeButton.enableButton();
		
		return viewEmployeeFrame;
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
					
					//create new employee view frame with the current selected employee
					selectedEmployeeIndex = employeesDisplay.getSelectedIndex();
					selectedEmployee = empVals.get(selectedEmployeeIndex);
					
					viewEmployeeFrame = createViewEmployeeFrame(selectedEmployee);
					viewEmployeeFrame.setVisible(true);
					
					employeesDisplay.setSelectedIndex(selectedEmployeeIndex);
					employeesDisplay.setSelectionBackground(new Color(163, 163, 163));
				}
			}catch(Exception err) {
				err.printStackTrace();
			}
		}

		//search button clicked
		if(e.getSource().equals(searchButton)) {
			getSearchResults();
		}
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		// Department Selection Handling
		if(e.getSource().equals(departmentsTab)) {
			refreshEmployeesDisplay();
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
				}
			}catch(IndexOutOfBoundsException err) {
				selectedEmployeeIndex = employeesDisplay.getSelectedIndex();
				viewEmployeeButton.disableButton();
			}
		}
	}

	@Override
	public void onEmployeeUpdate(UpdateEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEmployeeFrameClosed(CloseEvent e) {
		if(e.getSource().equals(viewEmployeeFrame)) viewEmployeeFrame = null;
	}

}
