package reading_date_and_validation;

public class ReadDate {

	public static void main(String[] args) {
		String s = "21-12-2009"; // dd-MM-yyyy
		ValidationBusiness v=new ValidationBusiness();
		try {
			if (v.isValidDate(s)) {
				System.out.println("Date validated");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
