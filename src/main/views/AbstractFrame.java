package main.views;


import javax.swing.JFrame;

import main.views.dialogs.AbstractUpdateChangesDialog;
import main.views.home.AbstractHomeFrame;

public class AbstractFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected AbstractUpdateChangesDialog saveChangesDialog = new AbstractUpdateChangesDialog();
	protected static AbstractHomeFrame homeView = null;
	
}
