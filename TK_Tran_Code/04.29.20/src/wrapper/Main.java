package wrapper;

public class Main {
    public static void main(String[] args) throws Exception {
        // int i = 5;
        // Integer j = new Integer(5); // Boxing (deprecated)

        // Autoboxing (converts primitive into an Object)
        Integer k = 3;
        // Unboxing (converts back to primitive)
        int l = k;

        String s = "123";
        Integer g = Integer.parseInt(s); // Converts String param into Object
        System.out.println(g);

		// Able to utilize various methods on these Objects
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.MIN_VALUE);
    }
}