package main.views.employee;

import java.awt.Color;
import java.awt.event.ActionEvent;

import main.models.Employee;
import main.views.components.EmployeeActionButton;
import main.views.factories.EmployeeFrameFactory;
import main.views.listeners.DefaultActionListener;
import main.views.listeners.HumanResourceViewActionListener;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec HumanResourcesViewEmployeeFrame is an implementation of AbstractViewEmployeeFrame that includes an edit and delete button 
 * @version 1.1
 *
 */
public class HumanResourceViewEmployeeFrame extends AbstractViewEmployeeFrame 
	implements HumanResourceViewActionListener, DefaultActionListener {
	
	private static final long serialVersionUID = 1L;
	
	protected EmployeeActionButton editEmployeeButton = null;

	public HumanResourceViewEmployeeFrame(Employee emp) {
		setEmployeeInformation(emp);
		setTitle("Employee Details");
		initializePanels();
		addTextPanes();
		initializeActionButtons();
	}

	private void initializeActionButtons() {
		
		editEmployeeButton = new EmployeeActionButton("Edit Employee", new Color(0, 120, 215));
		editEmployeeButton.addActionListener(this);
		editEmployeeButton.enableButton();
		
		center_panel.add(editEmployeeButton, "cell 4 18,growx");
		
		closeButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(closeButton)) {
			this.dispose();
		}
		
		if(e.getSource().equals(editEmployeeButton)) {
			EditEmployeeFrame editEmployeeFrame = EmployeeFrameFactory.createEditEmployeeFrame(employee);
			editEmployeeFrame.setVisible(true);
			
			this.dispose();
		}
	}
	
	
}
