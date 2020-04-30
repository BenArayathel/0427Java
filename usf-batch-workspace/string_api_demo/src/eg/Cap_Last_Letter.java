package eg;

public class Cap_Last_Letter {

	public static void main(String[] args) {

		String s = "hello hi how are you doing today"; // hello hi how are you doing today?

		String ar[] = s.split(" ");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < ar.length; i++) {

			System.out.print(ar[i].substring(0, ar[i].length() - 1)
					+ Character.toUpperCase(ar[i].charAt(ar[i].length() - 1)) + " ");

		}

	}

}
