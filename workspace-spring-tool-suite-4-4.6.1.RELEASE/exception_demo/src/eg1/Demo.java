package eg1;

public class Demo {

	public static void main(String[] args) {

		int x=10;
		int y=2;
		int res=0;
		try {
		res = x/y;
		String s=null;
		System.out.println(s.length());
		} catch(ArithmeticException | NullPointerException e) {//try catch used in presentation layer
			if (e instanceof ArithmeticException )
			System.out.println("You cannot divide by zero");
			else 
				System.out.println("Something it empty");
//		} catch(NullPointerException e) {
//			System.out.println("Something it empty");
		} catch (Exception e) {
			//cant be before the other exceptions
		}
		finally { //executed no matter if try catch works or not
			System.out.println("result is "+res);
		}
		
		System.out.println("Thank you for using our app");
	}

}
