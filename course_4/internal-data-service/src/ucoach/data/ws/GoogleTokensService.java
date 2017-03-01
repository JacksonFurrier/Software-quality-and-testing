package ucoach.data.ws;

import ucoach.data.model.GoogleTokens;
import ucoach.data.model.User;
import ucoach.data.ws.Authorization;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService(endpointInterface="ucoach.data.ws.GoogleTokensInterface",
  serviceName="GoogleTokensService")
public class GoogleTokensService implements GoogleTokensInterface {

  @Resource
  WebServiceContext context;

  @Override
  public GoogleTokens getTokens(int userId) {

    System.out.println("Getting tokens for user " + userId);

    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check Authorization header");
      return null;
    }

    // Get user
    User user = User.getUserById(userId);

    // Return tokens
    return GoogleTokens.getTokensByUser(user);
  }

  @Override
  public GoogleTokens setTokens(int userId, String accessToken, String refreshToken) {

    System.out.println("Setting tokens for user " + userId);

    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check Authorization header");
      return null;
    }

    // Get user
    User user = User.getUserById(userId);
    if (user == null) {
      return null;
    }

    // Create new google tokens
    GoogleTokens tokens = new GoogleTokens();
    tokens.setUser(user);
    tokens.setAccessToken(accessToken);
    tokens.setRefreshToken(refreshToken);

    // Persist to database
    return tokens.create();
  }

	@Override
	public GoogleTokens updateTokens(int userId, String accessToken) {
		System.out.println("Updating tokens for user " + userId);

    // Validate client
    boolean isValid = Authorization.validateRequest(context);
    if (!isValid) {
      System.out.println("Request not valid. Check Authorization header");
      return null;
    }

    // Get user
    User user = User.getUserById(userId);
    if (user == null) {
      return null;
    }

    // Get current tokens and update
    GoogleTokens tokens = GoogleTokens.getTokensByUser(user);
    tokens.setAccessToken(accessToken);
    return tokens.update();
  }
}

