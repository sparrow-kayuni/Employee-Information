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
	
	public EmployeeActionButton(String text, Color bgColor, int x, int y) {
		this.setText(text);
		this.backgroundColor = bgColor;
		this.setBounds(x, y, 150, 23);
		this.setFocusable(false);
	}
	
	public void enableButton() {
		this.setEnabled(true);
		this.setBackground(backgroundColor);
	}
	
	public void disableButton() {
		this.setEnabled(false);
		this.setBackground(disabledColor);
	}

}
