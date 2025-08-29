SSLCommerz Payment Integration Example

This project demonstrates how to integrate SSLCommerz payment gateway in a Spring Boot web application using both redirect checkout and embed checkout (popup/inline).

Customers can fill in their payment details and either be redirected to SSLCommerz or pay directly on your site via an embedded iframe.

Features

Spring Boot backend with REST API to initiate payment

Thymeleaf frontend form for payment details

Redirect checkout to SSLCommerz page

Embed checkout (popup/inline) on your site using embed.min.js

Handles success, failure, and cancellation pages

Sandbox environment support for testing

Prerequisites

Java 21+ / Spring Boot 3+

Maven or Gradle

Internet connection to access SSLCommerz sandbox API

SSLCommerz Store ID and Store Password

Project Structure
sslcommerz-example/
│
├── src/main/java/com/nipa/sslcommerzexample
│   ├── controller/PaymentController.java
│   ├── service/PaymentService.java
│   └── dto/PaymentRequest.java
│   └── dto/PaymentResponse.java
│
├── src/main/resources/templates
│   ├── index.html          # Payment form
│   ├── success.html        # Payment success page
│   ├── fail.html           # Payment failed page
│   └── cancel.html         # Payment cancelled page
│
└── pom.xml

Configuration
SSLCommerz Sandbox Credentials
private static final String STORE_ID = "YOUR_STORE_ID";
private static final String STORE_PASSWORD = "YOUR_STORE_PASSWORD";


Replace YOUR_STORE_ID and YOUR_STORE_PASSWORD with your SSLCommerz sandbox credentials.

For live payments, replace the sandbox URL with the live API URL.

Backend Implementation
PaymentService.java

Prepares payment parameters

Calls SSLCommerz API (https://sandbox.sslcommerz.com/gwprocess/v4/api.php)

Returns the GatewayPageURL (redirect) or sessionkey (embed checkout)

Map<String, Object> response = restTemplate.postForObject(apiUrl, params, Map.class);

PaymentController.java

/initiate-payment endpoint receives frontend form data

Calls PaymentService

Returns response containing GatewayPageURL (redirect) or sessionkey (embed checkout)

@PostMapping("/initiate-payment")
@ResponseBody
public Map<String, Object> initiatePayment(@RequestBody PaymentRequest request) {
return paymentService.initiatePayment(request);
}

Frontend Implementation
1️⃣ Redirect Checkout (Simplest)

Redirects user to SSLCommerz secure page

if (response.GatewayPageURL) {
window.location.href = response.GatewayPageURL;
}


Shows loading spinner while waiting for backend

Displays error message if initialization fails

2️⃣ Embed Checkout (Popup / Inline)

Include the embed script from SSLCommerz sandbox:

<script src="https://sandbox.sslcommerz.com/embed.min.js"></script>
<!-- For live, replace with: https://seamless-sslcommerz.com/embed.min.js -->


Call embed checkout using sessionkey:

if (response.success && response.data.sessionkey) {
const sslcz = new SSLCommerz({ sessionkey: response.data.sessionkey });
sslcz.render('#sslcommerz-embed-container'); // Inline container
}


HTML container for embedding payment:

<div id="sslcommerz-embed-container"></div>


The embed script loads the payment iframe directly on your site, so the user doesn’t leave your page.

Running the Project

Clone the repository

git clone https://github.com/yourusername/sslcommerz-example.git


Update Store ID and Store Password in PaymentService.java

Run Spring Boot application

mvn spring-boot:run


Open browser at: http://localhost:8080

Fill in the payment form and click Proceed to Payment

Testing

Use sandbox credentials provided by SSLCommerz

Check redirect and embed flows

Observe success, fail, and cancel pages after payment attempt

Notes

Redirect checkout is simpler, avoids JS errors like SSLCommerz is not defined

Embed checkout requires embed.min.js and a valid sessionkey from backend

Ensure your success_url, fail_url, cancel_url are accessible

Sandbox mode uses SSLCommerz demo store logo and sandbox environment.



