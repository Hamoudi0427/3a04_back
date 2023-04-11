# 3a04_back

# Delete Account:
DELETE https://3a04back-production.up.railway.app/delete?email=|email|

Note: The response is a 204 if success.

# Login to account:
POST https://3a04back-production.up.railway.app/login?email=|email|&password=|password|

Success response: 200 status and "Success." in response body.
Failure Response: 401 status and "Login failed." in response body.

# Create Account:
POST https://3a04back-production.up.railway.app/signup?email=|email|&password=|password|&age=|age|&firstName=|firstname|&lastName=|lastname|

Success response: 201 status and "Success." in response body.
Failure Resposne: 400 status and message based on failure reason.

Note: |item| is what to replace in URL.

# Get All Carpool Offers
GET https://3a04back-production.up.railway.app/offer

Success response: 200 status and JSON containing offer object in response body.
Failure Resposne: 400 status and empty response.

# Create Carpool Offer
POST https://3a04back-production.up.railway.app/offer?offeringTaxi=||&openSeats=||&offerer=||&offererRating=||&offerTime=||&offerDestination=helo&startLocation=||

Success response: 201 status and item created in response body as JSON.
Failure Resposne: 400 status.

Note: offeringTaxi and offerer are foreign keys, so must exists in those tables.

# Delete Carpool Offer
DELETE https://3a04back-production.up.railway.app/offer?offerId=||

Success response: 204 status if success.
Failure Resposne: 400 status if failure.

# Get Offers by Username
GET https://3a04back-production.up.railway.app/offer/|username here|
  
Success response: 200 and json array of offers.
Failure response: 404.
