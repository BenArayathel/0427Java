package generateInt;

public class makeInt {
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 99; i++) {
			
			Long x = new Long((long)((Math.random()*10000) * 10000));
			System.out.println(x);
		}
		
	}

}
