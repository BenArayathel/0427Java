package wrapper;

public class Main {
    public static void main(String[] args) throws Exception {
        
        // Boxing (deprecated)
        // int i = 5;
        // Integer j = new Integer(5); 

        // Autoboxing (converts primitive into an Object)
        int x = 3;  // primitive int
        Integer k = x;  // now an Object Integer
        System.out.println(k.getClass());   // class java.lang.Integer

        // Unboxing (converts back to primitive)
        int l = k;  // back to primitive int
        System.out.println(l);

        // Autoboxing a String
        String s = "123";
        Integer g = Integer.parseInt(s); // Converts String to Integer and assigns
        System.out.println(g.getClass());   // class java.lang.Integer
        
		// Can utilize various methods only on Objects (cannot on primitives)
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		System.out.println(Short.MIN_VALUE);
    }
}