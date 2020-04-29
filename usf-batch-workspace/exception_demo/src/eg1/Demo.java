package eg1;

public class Demo {

	public static void main(String[] args) {
		int x=10;
		int y=0;
		int res=0;
		try {
		res=x/y;
		String s=null;
		System.out.println(s.length());
		}
		catch(ArithmeticException | NullPointerException  e) {
			if(e instanceof ArithmeticException)
			System.out.println("You cannot divide by zero");
			else
				System.out.println("Something is empty");
		}
		finally {
			System.out.println("Result is "+res);
		}
		System.out.println("Thank you for using our app");

	}

}
