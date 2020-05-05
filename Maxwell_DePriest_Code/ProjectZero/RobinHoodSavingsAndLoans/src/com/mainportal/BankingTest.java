package com.mainportal;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BankingTest {
	
	Account a = new Account(22334, 56217, 100.00, 200.00, "tom@email.com");
	Customer c = new Customer("Mike Hardy", "mike@email.com", "1233455", "guest", a);
//    @Test
//    public void basicTests() {  
//    	assertEquals("zyx", User.passwordEncryption("abc"));
////        assertEquals(12, TotalPoints.points(new String[]
////                         {"1:0","2:0","3:0","4:4","2:2","3:3","1:4","2:3","2:4","3:4"}));
//    }
    
}
