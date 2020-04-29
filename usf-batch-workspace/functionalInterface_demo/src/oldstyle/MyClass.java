package oldstyle;

public class MyClass implements MyInterface {

	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("Hellloooooo");
	}

	public static void main(String[] args) {
		MyInterface m=new MyClass();
		m.sayHello();
		
		MyInterface m1=new MyInterface() {
			
			@Override
			public void sayHello() {
				// TODO Auto-generated method stub
				System.out.println("Hello anonmyous");
			}
		};
		
		m1.sayHello();
		MyInterface m2=new MyInterface() {
			
			@Override
			public void sayHello() {
				// TODO Auto-generated method stub
				System.out.println("Hello anonymous again");
			}
		};
		m2.sayHello();
	}
}
