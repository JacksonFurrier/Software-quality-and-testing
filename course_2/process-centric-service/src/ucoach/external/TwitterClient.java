package ucoach.external;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.io.IOException;

public class TwitterClient {
	
   private String CONSUMER_KEY = "twitter_consumer_key";
   private String CONSUMER_SECRET = "twitter_consumer_secret";
   private String ACCESS_TOKEN = "twitter_access_token";
   private String ACCESS_TOKEN_SECRET = "twitter_access_token_secret";
	
   /**
    * 
    * @param message
    * @throws IOException
    * @throws TwitterException
    */
   public void sendTweet(String message) throws IOException, TwitterException { //integration test

  	 	 setDependencies();

	     TwitterFactory twitterFactory = new TwitterFactory();
		     
	     Twitter twitter = twitterFactory.getInstance();
	     twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
	     twitter.setOAuthAccessToken(new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET));

	     StatusUpdate statusUpdate = new StatusUpdate(message);
	     Status status = twitter.updateStatus(statusUpdate);
	 }

	/**
	 * Set needed dependencies
	 */
	 private void setDependencies() {
		 if (String.valueOf(System.getenv("TWITTER_CONSUMER_KEY")) != "null"){
			 CONSUMER_KEY = String.valueOf(System.getenv("TWITTER_CONSUMER_KEY"));
		 }
		
		 if (String.valueOf(System.getenv("TWITTER_CONSUMER_SECRET")) != "null"){
			 CONSUMER_SECRET = String.valueOf(System.getenv("TWITTER_CONSUMER_SECRET"));
		 }
		
		 if (String.valueOf(System.getenv("TWITTER_ACCESS_TOKEN")) != "null"){
			 ACCESS_TOKEN = String.valueOf(System.getenv("TWITTER_ACCESS_TOKEN"));
		 }

		 if (String.valueOf(System.getenv("TWITTER_ACCESS_TOKEN_SECRET")) != "null"){
			 ACCESS_TOKEN_SECRET = String.valueOf(System.getenv("TWITTER_ACCESS_TOKEN_SECRET"));
		 }
	 }
}