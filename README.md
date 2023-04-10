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

# Get All Carpool offers
GET https://3a04back-production.up.railway.app/offer

Success response: 200 status and JSON containing offer object in response body.
Failure Resposne: 400 status and empty response.
