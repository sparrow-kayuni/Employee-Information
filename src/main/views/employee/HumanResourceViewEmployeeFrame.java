package main.views.employee;

import java.awt.Color;
import java.awt.event.ActionEvent;

import main.employeesystem.App;
import main.models.Employee;
import main.views.components.EmployeeActionButton;
import main.views.events.CloseEvent;
import main.views.factories.EmployeeFrameFactory;
import main.views.listeners.FrameClosedListener;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec HumanResourcesViewEmployeeFrame is an implementation of AbstractViewEmployeeFrame that includes an edit and delete button 
 * @version 1.1
 *
 */
public class HumanResourceViewEmployeeFrame extends AbstractViewEmployeeFrame implements FrameClosedListener {
	
	private static final long serialVersionUID = 1L;
	
	protected EmployeeActionButton editEmployeeButton = null;
	
	protected EditEmployeeFrame editEmployeeFrame = null;

	public HumanResourceViewEmployeeFrame(Employee emp) {
		setEmployeeInformation(emp);
		setTitle("Employee Details");
		initializePanels();
		setFrameHeader();
		initializeTextPanes();
		setTextPaneValues();
		initializeActionButtons();
	}

	private void initializeActionButtons() {
		editEmployeeButton = new EmployeeActionButton("Edit Employee", new Color(0, 120, 215));
		editEmployeeButton.addActionListener(this);
		editEmployeeButton.enableButton();
		
		center_panel.add(editEmployeeButton, "cell 4 18,growx");
		
		closeButton.addActionListener(this);
		
	}
	
	public boolean isEditEmployeeFrameActive() {
		return this.editEmployeeFrame != null;
	}
	
	public EditEmployeeFrame getEditEmployeeFrame() {
		return this.editEmployeeFrame;
	}
	
	
	public void refreshViewEmployeeFrame(Employee emp) {
		this.editEmployeeFrame = null;
		setEmployeeInformation(emp);
		setFrameHeader();
		setTextPaneValues();
		setLocation((App.screen.width / 2) - width / 2, 
				(App.screen.height / 2) - height / 2);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(closeButton)) {
			notifyClosedListeners(CloseEvent.Event.CANCEL);
			this.dispose();
		}
		
		if(e.getSource().equals(editEmployeeButton)) {
			
			this.editEmployeeFrame = EmployeeFrameFactory.createEditEmployeeFrame(currentEmployee);
			this.editEmployeeFrame.addClosedListener(this);
			this.editEmployeeFrame.setVisible(true);
			this.setVisible(false);
		}
	}

	//called when edit frame is closed
	@Override
	public void onEmployeeFrameClosed(CloseEvent e) {
		
		switch(e.getEventType()) {
			case CANCEL:
				//initialize edit frame and show view employee frame
				this.editEmployeeFrame = null;
				this.setVisible(true);
				break;
			case SAVE:
				//initialize edit frame
				this.editEmployeeFrame = null;
				break;
			case DELETE:
				//initialize edit frame and close view employee frame
				this.editEmployeeFrame = null;
				this.dispose();
			default: ;
		}
	}
}