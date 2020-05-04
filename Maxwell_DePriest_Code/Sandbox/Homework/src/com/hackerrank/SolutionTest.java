package com.hackerrank;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SolutionTest {
	
	public static void main(String[] args) {
		basicTests();
	}

    @Test
    public static void basicTests() {
        assertEquals("2 6 14 30 62 126 254 510 1022 2046 ", javaLoopTwo.mathProblem(0,2,10) );
    }
    
    public static boolean assertEquals(String answerLookingFor, String answerGiven) {
    	if (answerLookingFor.equals(answerGiven)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}