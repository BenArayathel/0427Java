package soap_thing_take_12312;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


@WebService
@SOAPBinding(style = Style.RPC)
public class SaySomething {
	
	@WebMethod
	public String saySomething(String name) {
		return "Here is " + name;
	}

}


