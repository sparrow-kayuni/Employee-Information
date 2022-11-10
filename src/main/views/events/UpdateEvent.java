package main.views.events;

import main.models.Employee;
import main.models.JobPosition;
import main.views.dialogs.AbstractUpdateChangesDialog;

public class UpdateEvent {
	private AbstractUpdateChangesDialog source = null;
	private Employee emp;
	private JobPosition job;

	public UpdateEvent(AbstractUpdateChangesDialog source) {
		this.source = source;
	}
	
	//returns the source of the update
	public AbstractUpdateChangesDialog getSource() {
		return this.source;
	}

	public Employee getEmployee() {
		return emp;
	}

	public void setEmployee(Employee emp) {
		this.emp = emp;
	}

	public JobPosition getJob() {
		return job;
	}

	public void setJob(JobPosition job) {
		this.job = job;
	}
	
	
}
