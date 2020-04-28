package new_lambdaway;

import oldstyle.MyInterface;

public class Main {
	public static void main(String[] args) {
		MyInterface m = () -> {
			System.out.println("hello lambda");
		};
		m.sayHello();
		MyInterface m1 = () -> {
			System.out.println("hello lambda again");
		};
		m1.sayHello();
		
		MyFunctional f=(n)->{
			System.out.println("Hello "+n);
		};
		f.hello("Jack");
		
		Add a=(x,y,z)->{
			return x+y+z;
		};
		System.out.println(a.sum(10, 88, 99));
	}
}
