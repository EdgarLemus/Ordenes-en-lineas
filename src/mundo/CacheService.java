package mundo;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Response;

import org.omg.CORBA.Request;

public class CacheService {

	private Map<Request, Response> cache;

    public CacheService() {
        this.cache = new HashMap<>();
    }

    public Response getCache(Request request) {
        return cache.get(request);
    }

    public void cache(Request request, Response response) {
        cache.put(request, response);
    }
}
