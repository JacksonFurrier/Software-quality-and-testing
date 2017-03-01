#Internal data service

The Internal Data Service is the service that interact to the MySQL database and provides a SOAP interface to the external world.

For more information and list of wsdl resources provided see the [Wiki](../../wiki)

###DEPENDENCIES

	# Authorization keys
	export INTERNAL_DATA_AUTH_KEY="internal_data_auth_key"

###HOW TO RUN

	git clone https://github.com/uCoach/internal-data-service.git
	cd internal-data-service
	ant run

#####Deployed on Heorku:

  	https://ucoach-internal-data-service.herokuapp.com
