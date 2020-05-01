
public class Num_Pal {

	public static void main(String[] args) {
		// find all the palindromes between 1000 and 9999
		int count = 0;
		
		
		for (int test = 1000; test <= 9999; test++) {
	
	
			String test1 = test+""; // shortcut from int to string
			
			//make a sb version of each int so we can reverse it
			StringBuilder toReverse = new StringBuilder(test1); 
			toReverse.reverse(); //reverse that sb
			
			String testReverse = toReverse.toString(); //change that sb to a string
			String testString = String.valueOf(test); // change test to a string
//			System.out.println(toReverse + "reverse");
//			System.out.println(testString + "normal");
			
			if (testReverse.equalsIgnoreCase(testString)) {
				count++;
			}
			
		}
		
		System.out.println(count);

	}

}
