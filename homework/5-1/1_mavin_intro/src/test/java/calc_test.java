import static org.junit.Assert.assertEquals;

import com.calculator.calculator;

import org.junit.Test;

public class calc_test {
	
	@Test
	public void AddTest() {
		assertEquals(5, calculator.add(2, 3));
	}

}
