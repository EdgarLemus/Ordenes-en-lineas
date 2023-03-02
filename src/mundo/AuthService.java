package mundo;

import java.util.List;

public class AuthService {

	private List<User> userList;

    public AuthService(List<User> userList) {
        this.userList = userList;
    }

    public boolean authenticate(Credentials credentials) {
        for (User user : userList) {
            if (user.getUsername().equals(credentials.getUsername()) && user.getPassword().equals(credentials.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
