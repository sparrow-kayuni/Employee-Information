package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import main.employeesystem.App;
import main.models.Employee;
import main.views.home.HumanResourceHomeFrame;

@TestMethodOrder(OrderAnnotation.class)
class HumanResourceHomeFrameTest {
	
	static HumanResourceHomeFrame homeFrame = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		App.connectDatabase();
		App.setDepartments();
		App.addManagerLoginInfo();
		App.showLoginView();
		
		ActionEvent loginButtonPressed = new ActionEvent(App.getLoginFrame().getLoginButton(), 
				ActionEvent.ACTION_PERFORMED, "Login",
                EventQueue.getMostRecentEventTime(),
                16);
		
		App.getLoginFrame().setJobIdText("400001");
		App.getLoginFrame().setPasswordText("1234");
		App.getLoginFrame().actionPerformed(loginButtonPressed);
		
		if((App.getHomeFrame() instanceof HumanResourceHomeFrame)) 
			homeFrame = (HumanResourceHomeFrame) App.getHomeFrame();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		App.logout();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	void testIfHumanResourceHomeFrameHasLoaded() {
		assertTrue(App.getHomeFrame() instanceof HumanResourceHomeFrame);
		System.out.println("Human Resource HomeFrame has loaded");
	}
	
	@Test
	@Order(2)
	void testIfViewEmployeeFrameLoads() {		
		List<Employee> emps = homeFrame.getEmployeeValues();
		
		int selectedIndex = emps.indexOf(emps.stream()
				.filter(e -> e.getFirstName().equals("Margret"))
				.findFirst().orElse(null));
		
		homeFrame.getDepartmentsTab().setSelectedValue("ALL DEPARTMENTS", false);
		homeFrame.getEmployeesDisplay().setSelectedIndex(selectedIndex);
		
		ActionEvent viewButtonPressed = new ActionEvent(
				homeFrame.getViewEmployeeButton(), 
				ActionEvent.ACTION_PERFORMED, "View Employee",
                EventQueue.getMostRecentEventTime(),
                16);
		
		homeFrame.actionPerformed(viewButtonPressed);
		
		assertNotNull(homeFrame.getViewEmployeeFrame());
		
		assertTrue(homeFrame.getViewEmployeeFrame()
				.getFirstNameTextPane().getText().equals("Margret"));

		System.out.println("View Employee Frame has loaded");
	}
	
	@Test
	@Order(3)
	void viewButtonPressedWhileViewingEmployee() {		
		List<Employee> emps = homeFrame.getEmployeeValues();
		
		int selectedIndex = emps.indexOf(emps.stream()
				.filter(e -> e.getFirstName().equals("Harry"))
				.findFirst().orElse(null));
		
		homeFrame.getDepartmentsTab().setSelectedValue("FINANCE", false);
		homeFrame.getEmployeesDisplay().setSelectedIndex(selectedIndex);
		
		ActionEvent viewButtonPressed = new ActionEvent(
				homeFrame.getViewEmployeeButton(), 
				ActionEvent.ACTION_PERFORMED, "View Employee",
                EventQueue.getMostRecentEventTime(),
                16);
		
		homeFrame.actionPerformed(viewButtonPressed);
		
		assertFalse(homeFrame.getViewEmployeeFrame()
				.getFirstNameTextPane().getText().equals("Harry"));
		
		assertTrue(homeFrame.getViewEmployeeFrame()
				.getFirstNameTextPane().getText().equals("Margret"));

		System.out.println("No New View Employee Frame has loaded");
	}
	
	@Test
	@Order(4)
	void addButtonPressedWhileViewingEmployee() {		
		List<Employee> emps = homeFrame.getEmployeeValues();
		
		int selectedIndex = emps.indexOf(emps.stream()
				.filter(e -> e.getFirstName().equals("Harry"))
				.findFirst().orElse(null));
		
		homeFrame.getDepartmentsTab().setSelectedValue("FINANCE", false);
		homeFrame.getEmployeesDisplay().setSelectedIndex(selectedIndex);
		
		ActionEvent viewButtonPressed = new ActionEvent(
				homeFrame.getAddEmployeeButton(), 
				ActionEvent.ACTION_PERFORMED, "View Employee",
                EventQueue.getMostRecentEventTime(),
                16);
		
		homeFrame.actionPerformed(viewButtonPressed);
		
		assertFalse(homeFrame.getViewEmployeeFrame()
				.getFirstNameTextPane().getText().equals("Harry"));
		
		assertTrue(homeFrame.getViewEmployeeFrame()
				.getFirstNameTextPane().getText().equals("Margret"));

		System.out.println("No New View Employee Frame has loaded");
	}
	
	@Test
	@Order(5)
	void testIfViewEmployeeFrameCloses() {	

		ActionEvent closeButtonPressed = new ActionEvent(
				homeFrame.getViewEmployeeFrame().getCloseButton(),
				ActionEvent.ACTION_PERFORMED, "Back",
                EventQueue.getMostRecentEventTime(),
                16);
		
		homeFrame.getViewEmployeeFrame().actionPerformed(closeButtonPressed);
		
		assertNull(homeFrame.getViewEmployeeFrame());
		
		System.out.println("View Employee Frame has closed");
	}
	
	@Test
	@Order(6)
	void viewButtonPressedAfterViewingEmployee() {		
		List<Employee> emps = homeFrame.getEmployeeValues();
		
		int selectedIndex = emps.indexOf(emps.stream()
				.filter(e -> e.getFirstName().equals("Harry"))
				.findFirst().orElse(null));
		
		homeFrame.getDepartmentsTab().setSelectedValue("FINANCE", false);
		homeFrame.getEmployeesDisplay().setSelectedIndex(selectedIndex);
		
		ActionEvent viewButtonPressed = new ActionEvent(
				homeFrame.getViewEmployeeButton(), 
				ActionEvent.ACTION_PERFORMED, "View Employee",
                EventQueue.getMostRecentEventTime(),
                16);
		
		homeFrame.actionPerformed(viewButtonPressed);
		
		assertTrue(homeFrame.getViewEmployeeFrame()
				.getFirstNameTextPane().getText().equals("Harry"));
		
		assertFalse(homeFrame.getViewEmployeeFrame()
				.getFirstNameTextPane().getText().equals("Margret"));

		System.out.println("No New View Employee Frame has loaded");
	}
}