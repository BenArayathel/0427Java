package soap_thing_take_12312;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class WriteNumber {

	@WebMethod
	public String WriteNumber(int number) {
		return "Here is " + number;
	}
	
}
