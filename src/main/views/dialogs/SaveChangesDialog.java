package main.views.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.models.Employee;
import main.views.employee.HumanResourceViewEmployeeFrame;
import main.views.factories.EmployeeFrameFactory;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveChangesDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	protected JButton okButton = null;
	protected JButton cancelButton = null;
	protected Employee employee = null;
	

	/**
	 * Create the dialog.
	 */
	public SaveChangesDialog(Employee newEmployee) {
		setType(Type.POPUP);
		setTitle("Are You Sure?");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 360, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(69, 69, 69));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[15.00][316.00]", "[40.00][40.00]"));
		{
			JLabel lblNewLabel = new JLabel("Are you sure you want to update changes made?");
			lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
			lblNewLabel.setForeground(new Color(214, 214, 214));
			contentPanel.add(lblNewLabel, "cell 1 1");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(69, 69, 69));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new MigLayout("", "[70.00px][70.00px][80.00][10.00][80.00]", "[30.00px]"));
			{
				okButton = new JButton("OK");
				okButton.setFocusable(false);
				okButton.setBackground(new Color(0, 120, 215));
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton, "cell 2 0,growx,aligny center");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(214, 214, 214));
				cancelButton.setFocusable(false);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton, "cell 4 0,growx,aligny center");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(okButton)) {
			
			if(isValidEmployee()) {
				HumanResourceViewEmployeeFrame frame = EmployeeFrameFactory.returnToViewEmployeeFrame(employee);
				frame.setVisible(true);
				this.dispose();
			}	
		}
		
	}

	private boolean isValidEmployee() {
		// TODO Auto-generated method stub
		return false;
	}

}
