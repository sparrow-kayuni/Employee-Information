package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import main.employeesystem.App;
import main.models.Employee;
import main.views.home.GeneralHomeFrame;

@TestMethodOrder(OrderAnnotation.class)
class GeneralHomeFrameTests {

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
		
		App.getLoginFrame().setJobIdText("100001");
		App.getLoginFrame().setPasswordText("54321");
		App.getLoginFrame().actionPerformed(loginButtonPressed);
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
	void testIfGeneralHomeFrameHasLoaded() {
		assertTrue(App.getHomeFrame() instanceof GeneralHomeFrame);
		System.out.println("General HomeFrame has loaded");
	}
	
	@Test
	@Order(2)
	void testIfViewEmployeeFrameLoads() {		
		List<Employee> emps = App.getHomeFrame().getEmployeeValues();
		
		int selectedIndex = emps.indexOf(emps.stream()
				.filter(e -> e.getFirstName().equals("Margret"))
				.findFirst().orElse(null));
		
		App.getHomeFrame().getDepartmentsTab().setSelectedValue("ALL DEPARTMENTS", false);
		App.getHomeFrame().getEmployeesDisplay().setSelectedIndex(selectedIndex);
		
		ActionEvent viewButtonPressed = new ActionEvent(
				App.getHomeFrame().getViewEmployeeButton(), 
				ActionEvent.ACTION_PERFORMED, "View Employee",
                EventQueue.getMostRecentEventTime(),
                16);
		
		App.getHomeFrame().actionPerformed(viewButtonPressed);
		
		assertNotNull(App.getHomeFrame().getViewEmployeeFrame());
		
		assertTrue(App.getHomeFrame().getViewEmployeeFrame()
				.getFirstNameTextPane().getText().equals("Margret"));
		
		System.out.println("View Employee Frame has loaded");
	}
	
	@Test
	@Order(3)
	void testIfViewButtonnTriggers() {		
		List<Employee> emps = App.getHomeFrame().getEmployeeValues();
		
		int selectedIndex = emps.indexOf(emps.stream()
				.filter(e -> e.getFirstName().equals("Harry"))
				.findFirst().orElse(null));
		
		App.getHomeFrame().getDepartmentsTab().setSelectedValue("FINANCE", false);
		App.getHomeFrame().getEmployeesDisplay().setSelectedIndex(selectedIndex);
		
		ActionEvent viewButtonPressed = new ActionEvent(
				App.getHomeFrame().getViewEmployeeButton(), 
				ActionEvent.ACTION_PERFORMED, "View Employee",
                EventQueue.getMostRecentEventTime(),
                16);
		
		App.getHomeFrame().actionPerformed(viewButtonPressed);
		
		assertFalse(App.getHomeFrame().getViewEmployeeFrame()
				.getFirstNameTextPane().getText().equals("Harry"));
		
		assertTrue(App.getHomeFrame().getViewEmployeeFrame()
				.getFirstNameTextPane().getText().equals("Margret"));

		System.out.println("No New View Employee Frame has loaded");
	}
	
	@Test
	@Order(4)
	void testIfViewEmployeeFrameCloses() {
		
		ActionEvent closeButtonPressed = new ActionEvent(
				App.getHomeFrame().getViewEmployeeFrame().getCloseButton(), 
				ActionEvent.ACTION_PERFORMED, "Back",
                EventQueue.getMostRecentEventTime(),
                16);
		
		App.getHomeFrame().getViewEmployeeFrame().actionPerformed(closeButtonPressed);
		
		assertNull(App.getHomeFrame().getViewEmployeeFrame());
		
		System.out.println("View Employee Frame has closed");
	}
}