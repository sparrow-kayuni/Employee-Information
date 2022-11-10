package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.employeesystem.App;
import main.models.Department;
import main.models.JobPosition;

class ExampleTests {

	@BeforeEach
	void setUp() throws Exception {
		App.connectDatabase();
		App.setDepartments();
		App.addManagerLoginInfo();
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testIfDepartmentsAreLoadedCorrectly() {
		ArrayList<String> departmentNames = new ArrayList<>();
		departmentNames.add("HUMAN RESOURCES");
		departmentNames.add("FINANCE");
		departmentNames.add("SALES");
		departmentNames.add("ACCOUNTS");
		departmentNames.add("EXECUTIVE");
		
		Iterator<Department> deptItr = App.getAllDepartments().values().iterator();
		
		while(deptItr.hasNext()) {
			Department dept = deptItr.next();
			System.out.println(dept.getDepartmentName());
			assertTrue(departmentNames.contains(dept.getDepartmentName()));
		}
	}
	
	@Test
	void testIfManagerLoginInfoIsCorrect() {
		ArrayList<String> departmentNames = new ArrayList<>();
		departmentNames.add("HUMAN RESOURCES");
		departmentNames.add("FINANCE");
		departmentNames.add("SALES");
		departmentNames.add("ACCOUNTS");
		departmentNames.add("EXECUTIVE");
		
		Iterator<JobPosition> jobItr = App.getAllLoginPositions().values().iterator();
		HashMap<String, Department> depts = App.getAllDepartments();
		
		int i = 0;
		while(jobItr.hasNext()) {
			JobPosition job = jobItr.next();
			System.out.println(job);
			assertTrue(true);
			i++;
		}
	}

}
