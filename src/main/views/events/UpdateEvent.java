package main.views.events;

import main.views.dialogs.AbstractUpdateChangesDialog;

public class UpdateEvent {
	private AbstractUpdateChangesDialog source = null;

	public UpdateEvent(AbstractUpdateChangesDialog source) {
		this.source = source;
	}
	
	//returns the source of the update
	public AbstractUpdateChangesDialog getSource() {
		return this.source;
	}
	
	
}
