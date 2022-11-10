package main.views.events;

import main.views.AbstractFrame;

public class CloseEvent {
	
	private AbstractFrame source = null;
	
	public enum Event {CANCEL, SAVE, DELETE}
	
	private Event eventType;
	
	public CloseEvent(AbstractFrame frame, Event event) {
		this.source = frame;
		this.eventType = event;
	}
	
	public AbstractFrame getSource() {
		return this.source;
	}
	
	public Event getEventType() {
		return this.eventType;
	}
	
}
