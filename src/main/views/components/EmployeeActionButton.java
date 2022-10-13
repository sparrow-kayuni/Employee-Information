package main.views.components;

import java.awt.Color;

import javax.swing.JButton;

public class EmployeeActionButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color backgroundColor = null;
	private Color disabledColor = new Color(140, 140, 140);
	
	public EmployeeActionButton(String text, Color bgColor) {
		this.setText(text);
		this.backgroundColor = bgColor;
		this.setFocusable(false);
	}
	
	public void enableButton() {
		this.setEnabled(true);
		this.setBackground(backgroundColor);
		this.setForeground(new Color(51, 51, 51));
	}
	
	public void disableButton() {
		this.setEnabled(false);
		this.setBackground(disabledColor);
		this.setForeground(new Color(120, 120, 120));
	}

}
