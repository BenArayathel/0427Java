package soap_think_client;

import com.example.services.SaySomething;
import com.example.services.SaySomethingService;
import com.example.services.WriteNumber;
import com.example.services.WriteNumberService;

public class main {
	
	public static void main(String[] args) {
		
		SaySomethingService hgenService = new SaySomethingService();
		
		SaySomething speak = hgenService.getSaySomethingPort();
		
		String response = speak.saySomething("Vinay");
		
		System.out.println(response);
		
		WriteNumberService writenumber = new WriteNumberService();
		
		WriteNumber write = writenumber.getWriteNumberPort();
		
		String response2 = write.writeNumber(10);
		
		System.out.println(response2);
		
	}

}


//http://localhost:9000/soap_thing_take_12312/test/hello