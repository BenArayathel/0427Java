package eg2;

public class Main {

	int x; //instance level
	int y;  // instance level
	static int z = 100;  //class level
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m1=new Main();
		m1.x=9;
		m1.y=99;
		System.out.println("Printing M1");
		m1.printXYZ();
		
		Main m2=new Main();
		m2.x=88;
		m2.y=898;
		System.out.println("Printing M2");
		m2.printXYZ();
		m1.z=90000;
		m1.x=700000;
		System.out.println("Printing M1 Again");
		m1.printXYZ();
		System.out.println("Printing M2 Again");
		m2.printXYZ();
		
		Two.helloStatic();
		
		Two t=new Two();
		t.helloNonStatic();
				
	}
	
	public void printXYZ() {
		System.out.println("x = "+x);
		System.out.println("y = "+y);
		System.out.println("z = "+z);
	}

}
