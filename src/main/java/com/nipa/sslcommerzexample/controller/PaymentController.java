package com.nipa.sslcommerzexample.controller;

import com.nipa.sslcommerzexample.dto.PaymentRequest;
import com.nipa.sslcommerzexample.dto.PaymentResponse;
import com.nipa.sslcommerzexample.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String paymentSuccess(@RequestParam Map<String, String> params, Model model) {
        // Example fields SSLCommerz sends
        String tranId = params.get("tran_id");          // Transaction ID
        String amount = params.get("amount");           // Paid amount
        String cardType = params.get("card_type");      // Card/Mobile Banking type
        String bankTranId = params.get("bank_tran_id"); // Bank Transaction ID

        // Add data to the view
        model.addAttribute("tranId", tranId);
        model.addAttribute("amount", amount);
        model.addAttribute("cardType", cardType);
        model.addAttribute("bankTranId", bankTranId);

        // TODO: Save transaction details into DB

        return "success";  // success.html view will show details
    }


    @PostMapping("/payment/fail")
    public String paymentFail(@RequestParam Map<String, String> params, Model model) {
        // Example fields for failed transactions
        String tranId = params.get("tran_id");               // Transaction ID
        String errorReason = params.get("error");            // Failure reason (if available)
        String failedReason = params.get("failedreason");    // SSLCommerz failure reason

        model.addAttribute("tranId", tranId);
        model.addAttribute("failedReason", failedReason != null ? failedReason : errorReason);

        // TODO: log/store failed transaction in DB

        return "fail"; // fail.html view will show failure reason
    }

    @PostMapping("/payment/cancel")
    public String paymentCancel(@RequestParam Map<String, String> params, Model model) {
        String tranId = params.get("tran_id");
        model.addAttribute("tranId", tranId);

        return "cancel";
    }

    @PostMapping("/payment/ipn")
    @ResponseBody
    public String paymentIPN(@RequestParam Map<String, String> params) {
        // TODO: validate with SSLCommerz API and update DB
        return "IPN received";
    }
}
