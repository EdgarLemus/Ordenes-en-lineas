package mundo;

import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.Request;

public class IPFilteringService {

	private static Map<String, Integer> failedAttempts = new HashMap<>();
    private static final int MAX_FAILED_ATTEMPTS = 5;
    
	public static boolean filter(Request request) {
		int failedAttemptsCount = failedAttempts.getOrDefault(request.toString(), 0);
        if (failedAttemptsCount >= MAX_FAILED_ATTEMPTS) {
            return false;
        } else {
            failedAttempts.put(request.toString(), failedAttemptsCount + 1);
            return true;
        }
    }
}
