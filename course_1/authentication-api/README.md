#Authentication Api

The Authentication-api is the service that manages the users authentication and stores their tokens. It is called a first time by the application to perform login and have back a user token. After that, all the other services will need to authenticate the user tokens with a call to the api.

This api needs to call the data-service only when performing login (to check password/username). The tokens are stored internally in a light MySQL database.

We decided to separate the authentication logic in a different api/service in order not to overload the business and data services when performing simple (but lot) of requests for authentication of tokens.

For more information and list of resources/endpoints provided see the [Wiki](../../wiki)

###DEPENDENCIES

	# Authorization keys
	export DATA_AUTH_KEY="data_auth_key"

###HOW TO RUN

	git clone https://github.com/uCoach/authentication-api.git
	cd authentication-api
	ant run

#####Deployed on Heorku:

  	https://ucoach-authentication-api.herokuapp.com
