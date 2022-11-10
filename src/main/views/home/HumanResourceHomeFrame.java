package main.views.home;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionEvent;

import main.models.Employee;
import main.views.components.EmployeeActionButton;
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
	
	private HumanResourceViewEmployeeFrame viewEmployeeFrame = null;
	private AddEmployeeFrame addEmployeeFrame = null;

	private Employee emp;

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
	public void actionPerformed(ActionEvent e) {
		//View Selected Employee
		if(e.getSource().equals(viewEmployeeButton)) {
			try {
				//if employee view frame exists, bring current employee view frame to front
				if(this.viewEmployeeFrame != null) {
					if(this.viewEmployeeFrame.isEditEmployeeFrameActive()) {
						this.viewEmployeeFrame.getEditEmployeeFrame().setVisible(true);;
					}else {
						this.viewEmployeeFrame.setVisible(true);
					}
				}else {
					if(this.addEmployeeFrame != null) {
						this.addEmployeeFrame.setVisible(true);
					}else {
						//create new employee view frame with the current selected employee
						selectedEmployeeIndex = employeesDisplay.getSelectedIndex();
						selectedEmployee = empVals.get(selectedEmployeeIndex);
						
						this.viewEmployeeFrame = createViewEmployeeFrame(selectedEmployee);
						this.viewEmployeeFrame.setVisible(true);
						
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
			
			if(this.addEmployeeFrame != null) {
				this.addEmployeeFrame.setVisible(true);
			}else {
				if(this.viewEmployeeFrame != null) {
					
					if(this.viewEmployeeFrame.isEditEmployeeFrameActive()) {
						this.viewEmployeeFrame.getEditEmployeeFrame().setVisible(true);;
					}else {
						this.viewEmployeeFrame.setVisible(true);
					}
				}else {
					this.addEmployeeFrame = EmployeeFrameFactory.createAddEmployeeFrame(deptName);
					this.addEmployeeFrame.addClosedListener(this);
					this.addEmployeeFrame.setVisible(true);
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
		this.viewEmployeeFrame = (HumanResourceViewEmployeeFrame) 
				EmployeeFrameFactory.createViewEmployeeFrame(this, emp);
		this.viewEmployeeFrame.addClosedListener(this);
		viewEmployeeButton.enableButton();
		
		return this.viewEmployeeFrame;
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
				if(this.viewEmployeeFrame == null) {
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
			if(this.viewEmployeeFrame != null) {
				this.viewEmployeeFrame.setClosedEvent(CloseEvent.Event.SAVE);
				this.viewEmployeeFrame.dispose();
			}
			emp = e.getEmployee();
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
				this.viewEmployeeFrame = null;
				break;
			case SAVE:
				this.viewEmployeeFrame = createViewEmployeeFrame(emp);
				this.viewEmployeeFrame.setVisible(true);
				break;
			case DELETE: 
				this.viewEmployeeFrame = null;
				break;
			default: break;
		}		
		
		if(this.addEmployeeFrame != null) this.addEmployeeFrame.dispose();
		this.addEmployeeFrame = null;		
	}
}
