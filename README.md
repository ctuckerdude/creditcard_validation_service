# Credit Card Validation Service

Introduction
------------
The Credit Card Validation Service validates a credit card number using the Luhn algorithm and outputs the response in the following format:
<issuer>: credit_card_number (validation_response)

Where
issuer: AMEX, Discoverer, MasterCard, VISA and Unknown (if the card cannot be determined)

credit_card_number: The credit card number to be validated

validation_response: Valid or Invalid

e.g. MasterCard: 5105105105105100 (valid)

The Credit Card Validation Service consists of a jar file: credit_card_validation_service-xx_xx_xx.jar (where
xx_xx_xx is the release number) and an application.properties file which contains the configuration required by the
service. You will need Java 11 in order to run the service.

Configuration
-------------

An application properties file, application.properties contains the configuration required to start the service.
There is only one parameter, server.port, which defines the port on which the server will start. The application.properties
file must exist in the same directory as the credit_card_validation_service-xx_xx_xx.jar file, otherwise the service will
start on the default port of 8300.

Starting the Service
--------------------

The service is started on command line:
java -jar credit_card_validation_service_xx_xx_xx.jar

Once started you can invoke the endpoint to validate a credit card number:
http://localhost:<port>/api/<api_version>/creditcard/validation/<creditcardnumber>,

Where api_version is the version of the api and depends on the release number, see release notes

e.g.
http://localhost:8300/api/v1/creditcard/validation/5105105105105100