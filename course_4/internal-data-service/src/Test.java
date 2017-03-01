import java.util.Date;

import ucoach.data.model.*;

public class Test {
  public static void main(String[] args) throws Exception{
    User user = new User();

    user.setFirstname("Ana");
    User createdUser = User.createUser(user);

    System.out.println(">>>>>>>>>>>>>>>>" + user.getFirstname());
    
	GoogleTokens tokens = new GoogleTokens();
	tokens.setUser(user);
	tokens.setAccessToken("test_access_token");
	tokens.setRefreshToken("test_refresh_token");
	
	tokens.create();
	GoogleTokens testTokens = GoogleTokens.getTokensByUser(user);
	
	System.out.println(">>>>>>>>>>>>>>>>" + testTokens.getAccessToken());
  }
}
