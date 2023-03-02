package mundo;

import org.omg.CORBA.Request;

public class Order {

	private Credentials credentials;
    private Request request;
    
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}    
}
