package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.employeesystem.App;
import main.models.Department;
import main.views.home.GeneralHomeFrame;

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
		loginButtonPressed = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                "Login",
                EventQueue.getMostRecentEventTime(),
                16);
		System.out.println("Started");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Done");
		if(App.getHomeFrame() != null) {
			App.getHomeFrame().dispose();
			App.setHomeFrame(null);
		}
	}
	
	@AfterAll
	static void tearDownAfterAll() {
		App.getLoginFrame().dispose();
	}
	
	@Test
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
	void testForIncorrectPassword() {
		System.out.println("Invalid with wrong password");
		
		App.getLoginFrame().setJobIdText("100001");
		App.getLoginFrame().setPasswordText("");
		App.getLoginFrame().actionPerformed(loginButtonPressed);
		
		assertNotNull(App.getLoginFrame().getCurrentJobPosition());
		
		assertTrue(App.getLoginFrame().getFlashMessage().equals("Wrong Password"));
		
		Department dept = App.getDepartment(App.getLoginFrame().getCurrentJobPosition()
				.getEmployee().getDepartmentName());
		
		assertFalse(dept.isLoggedIn);
		
		assertNull(App.getHomeFrame());

	}
	
	
	@Test
	void testForCorrectLogin() {
		System.out.println("Correct Login");
		
		App.getLoginFrame().setJobIdText("100001");
		App.getLoginFrame().setPasswordText("54321");
		App.getLoginFrame().actionPerformed(loginButtonPressed);
		
		assertNotNull(App.getLoginFrame().getCurrentJobPosition());
		
		Department dept = App.getDepartment(App.getLoginFrame().getCurrentJobPosition()
				.getEmployee().getDepartmentName());
		
		assertTrue(dept.isLoggedIn);
		
		assertTrue(App.getHomeFrame() instanceof GeneralHomeFrame);

	}
}
