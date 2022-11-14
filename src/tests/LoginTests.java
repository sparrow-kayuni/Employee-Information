package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import main.employeesystem.App;
import main.models.Department;

@TestMethodOrder(OrderAnnotation.class)
class LoginTests {
	
	ActionEvent loginButtonPressed;

	@BeforeAll
	static void setUpBeforeAll() {
		App.connectDatabase();
		App.setDepartments();
		App.addManagerLoginInfo();
		App.showLoginView();
	}
	
	@BeforeEach
	void setUp() throws Exception {		
		loginButtonPressed = new ActionEvent(App.getLoginFrame().getLoginButton(), 
				ActionEvent.ACTION_PERFORMED, "Login",
                EventQueue.getMostRecentEventTime(),
                16);
		System.out.println("Started");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Done");
		if(App.getHomeFrame() != null) App.logout();
	}
	
	@AfterAll
	static void tearDownAfterAll() {
		App.getLoginFrame().dispose();
	}
	
	@Test
	@Order(1)
	void testForInvalidJobIdWithLetters() {
		System.out.println("Invalid with letters");
		
		App.getLoginFrame().setJobIdText("1absdeed");
		App.getLoginFrame().setPasswordText("54789929");
		App.getLoginFrame().actionPerformed(loginButtonPressed);
		
		assertNull(App.getLoginFrame().getCurrentJobPosition());
		
		assertEquals(App.getLoginFrame().getFlashMessage(), "Enter numbers only");
		
		assertNull(App.getHomeFrame());
	}
	
	@Test
	@Order(2)
	void testForInvalidJobIdWithNumbersOnly() {
		System.out.println("Invalid with wrong id");
		
		App.getLoginFrame().setJobIdText("112345");
		App.getLoginFrame().setPasswordText("54789929");
		App.getLoginFrame().actionPerformed(loginButtonPressed);
		
		assertNull(App.getLoginFrame().getCurrentJobPosition());
		
		assertEquals(App.getLoginFrame().getFlashMessage(), "User Doesn't Exist");
		
		assertNull(App.getHomeFrame());
	}

	@Test
	@Order(3)
	void testForIncorrectPassword() {
		System.out.println("Invalid with wrong password");
		
		App.getLoginFrame().setJobIdText("100001");
		App.getLoginFrame().setPasswordText("iub");
		App.getLoginFrame().actionPerformed(loginButtonPressed);
		
		assertNotNull(App.getLoginFrame().getCurrentJobPosition());
		
		assertTrue(App.getLoginFrame().getFlashMessage().equals("Wrong Password"));
		
		Department dept = App.getDepartment(App.getLoginFrame().getCurrentJobPosition()
				.getEmployee().getDepartmentName());
		
		assertFalse(dept.isLoggedIn);
		
		assertNull(App.getHomeFrame());
	}
	
	@Test
	@Order(4)
	void testForCorrectGeneralManagerLogin() {
		System.out.println("Correct Login");
		
		App.getLoginFrame().setJobIdText("100001");
		App.getLoginFrame().setPasswordText("54321");
		App.getLoginFrame().actionPerformed(loginButtonPressed);
		
		assertNotNull(App.getLoginFrame().getCurrentJobPosition());
		
		Department dept = App.getDepartment(App.getLoginFrame().getCurrentJobPosition()
				.getEmployee().getDepartmentName());
		
		assertTrue(dept.isLoggedIn);
		
		assertNotNull(App.getHomeFrame());
	}
	
	@Test
	@Order(5)
	void testForCorrectHumanResourceLogin() {
		System.out.println("Correct HR Login");
		
		App.getLoginFrame().setJobIdText("400001");
		App.getLoginFrame().setPasswordText("1234");
		App.getLoginFrame().actionPerformed(loginButtonPressed);
		
		assertNotNull(App.getLoginFrame().getCurrentJobPosition());
		
		Department dept = App.getDepartment(App.getLoginFrame().getCurrentJobPosition()
				.getEmployee().getDepartmentName());
		
		assertTrue(dept.isLoggedIn);
		
		assertNotNull(App.getHomeFrame());
	}
}