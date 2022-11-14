package main.views.home;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionEvent;

import main.models.Employee;
import main.views.components.EmployeeActionButton;
import main.views.employee.AbstractViewEmployeeFrame;
import main.views.employee.AddEmployeeFrame;
import main.views.employee.HumanResourceViewEmployeeFrame;
import main.views.events.CloseEvent;
import main.views.events.UpdateEvent;
import main.views.factories.EmployeeFrameFactory;


/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec HumanResourcesHomeView is an implementation of AbstractHomeView and includes an add employee buttons 
 * @version 1.1
 *
 */
public class HumanResourceHomeFrame extends AbstractHomeFrame {

	private static final long serialVersionUID = 1L;

	private EmployeeActionButton addEmployeeButton = null;
	
	private static HumanResourceViewEmployeeFrame viewEmployeeFrame = null;
	private static AddEmployeeFrame addEmployeeFrame = null;

	private Employee employee;

	public HumanResourceHomeFrame() {
		setResizable(false);
		setTitle("Employee Information (HR)");
		initialize();
		initializeEditButtons();
	}

	
	private void initializeEditButtons() {
		addEmployeeButton = new EmployeeActionButton("Add Employee", new Color(0, 215, 120));
		addEmployeeButton.addActionListener(this);
		addEmployeeButton.enableButton();
		center_panel.add(addEmployeeButton, "cell 4 13,growx");
	}
	
	@Override
	public AbstractViewEmployeeFrame getViewEmployeeFrame() {
		return viewEmployeeFrame;
	}
	
	public AddEmployeeFrame getAddEmployeeFrame() {
		return addEmployeeFrame;
	}
	
	public EmployeeActionButton getAddEmployeeButton() {
		return addEmployeeButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//View Selected Employee
		if(e.getSource().equals(viewEmployeeButton)) {
			try {
				//if employee view frame exists, bring current employee view frame to front
				if(viewEmployeeFrame != null) {
					if(viewEmployeeFrame.isEditEmployeeFrameActive()) {
						viewEmployeeFrame.getEditEmployeeFrame().setVisible(true);;
					}else {
						viewEmployeeFrame.setVisible(true);
					}
				}else {
					if(addEmployeeFrame != null) {
						addEmployeeFrame.setVisible(true);
					}else {
						//create new employee view frame with the current selected employee
						selectedEmployeeIndex = employeesDisplay.getSelectedIndex();
						selectedEmployee = empVals.get(selectedEmployeeIndex);
						
						viewEmployeeFrame = createViewEmployeeFrame(selectedEmployee);
						viewEmployeeFrame.setVisible(true);
						
						employeesDisplay.setSelectedIndex(selectedEmployeeIndex);
						employeesDisplay.setSelectionBackground(new Color(163, 163, 163));
					}
				}
			}catch(Exception err) {
				err.printStackTrace();
			}
		}
		
		//Add button clicked
		if(e.getSource().equals(addEmployeeButton)) {
			String deptName = null;
			
			//if tab selected is "ALL DEPARTMENTS" or "SEARCH RESULTS", set to EXECUTIVE department by default
			if(departmentsTab.getSelectedValue().equals("ALL DEPARTMENTS") ||
					departmentsTab.getSelectedValue().equals("SEARCH RESULTS")) deptName = "EXECUTIVE"; 
			else deptName = departmentsTab.getSelectedValue();
			
			if(addEmployeeFrame != null) {
				addEmployeeFrame.setVisible(true);
			}else {
				if(viewEmployeeFrame != null) {
					
					if(viewEmployeeFrame.isEditEmployeeFrameActive()) {
						viewEmployeeFrame.getEditEmployeeFrame().setVisible(true);;
					}else {
						viewEmployeeFrame.setVisible(true);
					}
				}else {
					addEmployeeFrame = EmployeeFrameFactory.createAddEmployeeFrame(deptName);
					addEmployeeFrame.addClosedListener(this);
					addEmployeeFrame.setVisible(true);
				}
			}
		}
		
		//search button clicked
		if(e.getSource().equals(searchButton)) {
			getSearchResults();
		}
	}
	
	
	//
	private HumanResourceViewEmployeeFrame createViewEmployeeFrame(Employee emp) {
		viewEmployeeFrame = (HumanResourceViewEmployeeFrame) 
				EmployeeFrameFactory.createViewEmployeeFrame(this, emp);
		viewEmployeeFrame.addClosedListener(this);
		viewEmployeeButton.enableButton();
		
		return viewEmployeeFrame;
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

	//called when save or delete dialogs have been accepted
	@Override
	public void onEmployeeUpdate(UpdateEvent e) {
		refreshEmployeesDisplay();
		
		//if update event has an employee, means the event was from an add or edit operation
		//if the employee is null, it means it was a delete operation
		if(e.getEmployee() != null) {
			if(viewEmployeeFrame != null) {
				viewEmployeeFrame.setClosedEvent(CloseEvent.Event.SAVE);
				viewEmployeeFrame.dispose();
			}
			employee = e.getEmployee();
		} 
		else{
			viewEmployeeFrame.setClosedEvent(CloseEvent.Event.DELETE);
		}
	}
	
	//called when view employee frame is closed manually or after being disposed
	@Override
	public void onEmployeeFrameClosed(CloseEvent e) {
		
		//check for the type off close event
		switch(e.getEventType()) {
			case CANCEL:
				viewEmployeeFrame = null;
				break;
			case SAVE:
				viewEmployeeFrame = createViewEmployeeFrame(employee);
				viewEmployeeFrame.setVisible(true);
				break;
			case DELETE: 
				viewEmployeeFrame = null;
				break;
			default: break;
		}		
		
		if(addEmployeeFrame != null) addEmployeeFrame.dispose();
		addEmployeeFrame = null;		
	}
}
