import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.calculator.Calculator;

public class CalculatorTest {
	
	public static int a = 3;
	
	@BeforeClass //Before the class
	public static void beforeClass() {
		System.out.println("Getting ready for all the tests!\n");
	}
	
	@AfterClass //After the class
	public static void afterClass() {
		System.out.println("\nRelaxing after all the tests!");
	}
	
	@Before //Will run before every single test
	public void beforeTest() {
		System.out.println("Getting ready for a test");
	}
	
	@After
	public void afterTest() {
		System.out.println("Relaxing after a test! \n");
	}
	
	@Test
	public void addTest() {
		assertEquals(5, Calculator.add(2, a));
			//( expected, actual) results 
		System.out.println("Testing Add");
	}
	
//	@Test 
//	public void multiplyTest() {
//		fail("Method has not been implemented yet");
//	}
	
	@Test
	public void returnStringTest() {
		assertEquals("Hello!", Calculator.returnString("Hello!"));
		System.out.println("Testing return string");
	}

}
