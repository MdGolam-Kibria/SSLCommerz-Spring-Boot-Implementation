package com.nipa.sslcommerzexample.controller;

import com.nipa.sslcommerzexample.dto.PaymentRequest;
import com.nipa.sslcommerzexample.dto.PaymentResponse;
import com.nipa.sslcommerzexample.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/initiate-payment")
    @ResponseBody
    public PaymentResponse initiatePayment(@RequestBody PaymentRequest request) {
        try {
            Map<String, Object> response = paymentService.initiatePayment(request);

            if (response.containsKey("sessionkey")) {
                return new PaymentResponse(true, "Payment initiated successfully", response);
            } else {
                return new PaymentResponse(false, "SSLCommerz did not return sessionkey", null);
            }

        } catch (Exception e) {
            return new PaymentResponse(false, "Payment initiation failed: " + e.getMessage(), null);
        }
    }

    @PostMapping("/payment/success")
    public String paymentSuccess() {
        return "success";
    }

    @PostMapping("/payment/fail")
    public String paymentFail() {
        return "fail";
    }

    @PostMapping("/payment/cancel")
    public String paymentCancel() {
        return "cancel";
    }

    @PostMapping("/payment/ipn")
    @ResponseBody
    public String paymentIPN(@RequestParam Map<String, String> params) {
        // TODO: validate with SSLCommerz API and update DB
        return "IPN received";
    }
}
