package com.nipa.sslcommerzexample.service;

import com.nipa.sslcommerzexample.config.SSLCommerzConfig;
import com.nipa.sslcommerzexample.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    @Value("${server.port}")
    private Integer port;

    private final SSLCommerzConfig config;

    public PaymentService(SSLCommerzConfig config) {
        this.config = config;
    }

    public Map<String, Object> initiatePayment(PaymentRequest request) {
        String transactionId = generateTransactionId();

        Map<String, String> params = new HashMap<>();
        params.put("store_id", config.getStoreId());
        params.put("store_passwd", config.getStorePassword());
        params.put("total_amount", request.getAmount());
        params.put("currency", request.getCurrency());
        params.put("tran_id", transactionId);
        params.put("success_url", "http://localhost:" + port + "/payment/success");
        params.put("fail_url", "http://localhost:" + port + "/payment/fail");
        params.put("cancel_url", "http://localhost:" + port + "/payment/cancel");
        params.put("ipn_url", "http://localhost:" + port + "/payment/ipn");

        // Customer info
        params.put("cus_name", request.getCustomerName());
        params.put("cus_email", request.getCustomerEmail());
        params.put("cus_add1", request.getCustomerAddress1());
        params.put("cus_city", request.getCustomerCity());
        params.put("cus_postcode", request.getCustomerPostcode());
        params.put("cus_country", request.getCustomerCountry());
        params.put("cus_phone", request.getCustomerPhone());

        // Product info
        params.put("product_name", request.getProductName());
        params.put("product_category", request.getProductCategory());
        params.put("product_profile", "general");
        params.put("shipping_method", "NO");

        // ✅ Convert params to Form URL Encoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        StringBuilder bodyBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            bodyBuilder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        String requestBody = bodyBuilder.substring(0, bodyBuilder.length() - 1);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // ✅ Call SSLCommerz API
        String apiUrl = "https://sandbox.sslcommerz.com/gwprocess/v4/api.php";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> response =
                restTemplate.exchange(apiUrl, HttpMethod.POST, entity, Map.class);

        return response.getBody();
    }

    private String generateTransactionId() {
        return "TXN_" + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
