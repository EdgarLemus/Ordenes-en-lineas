package mundo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.omg.CORBA.Request;

public class OrderClient {

	private Credentials credentials;
    private AuthService authService;
    private ValidationService validationService;
    private IPFilteringService ipFilteringService;
    private CacheService cacheService;
    
    public OrderClient(Credentials credentials, List<User> users) {
        this.credentials = credentials;
        this.authService = new AuthService(users);
        this.validationService = new ValidationService();
        this.ipFilteringService = new IPFilteringService();
        this.cacheService = new CacheService();
    }
    
    public void createOrder(Request orderRequest) {       
    	authenticate();
        sanitize(orderRequest);
        filterIP(orderRequest);
        isCache(orderRequest);
        Response orderResponse = null;
        cacheResponse(orderRequest, orderResponse); 
    }
    
    /*
     * La clase ValidationService se encarga de sanear los datos de la solicitud para evitar ataques de inyección de código.
     * */
    private void sanitize(Request request) {
    	validationService.sanitize(request);
    }
    
    /*
     *La autenticación se maneja a través del objeto AuthService, 
     *que tiene una lista de usuarios para verificar si las credenciales proporcionadas son correctas.
     */
    private void authenticate() {
    	if (!authService.authenticate(credentials)) {
            throw new RuntimeException("Invalid credentials");
        }
    }
    
    /*
     * La clase IPFilteringService se encarga de filtrar las solicitudes fallidas repetidas que vienen de la misma dirección IP 
     * para evitar ataques de fuerza bruta.
     */
    private void filterIP(Request request) {
    	if (ipFilteringService.filter(request)) {
            throw new RuntimeException("Too many requests from this IP address");
        }
    }
    
    /*
     * La clase CacheService se encarga de almacenar en caché las respuestas a solicitudes 
     * repetidas que contienen los mismos datos para aumentar la velocidad de respuesta.
     * */
    private void isCache(Request request) {
    	Response cachedResponse = cacheService.getCache(request);
        if (cachedResponse != null) {
            System.out.println("Returning cached response");
            return;
        }
    }
    
    private void cacheResponse(Request request, Response response) {
        cacheService.cache(request, response);
        System.out.println("Order completed successfully");
    }
    
}
