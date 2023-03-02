package mundo;

import org.omg.CORBA.Request;

public class ValidationService {

	public String sanitize(Request orderRequest) {
		 String sanitizedInput = orderRequest.toString().replaceAll("[^a-zA-Z0-9 ]", "");
        return sanitizedInput;
    }
}
