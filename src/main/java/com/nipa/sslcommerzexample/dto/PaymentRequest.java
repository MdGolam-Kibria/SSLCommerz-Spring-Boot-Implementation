package com.nipa.sslcommerzexample.dto;

public class PaymentRequest {
    private String amount;
    private String currency = "BDT";
    private String customerName;
    private String customerEmail;
    private String customerAddress1;
    private String customerCity;
    private String customerPostcode;
    private String customerCountry;
    private String customerPhone;
    private String productCategory = "general";
    private String productName = "Test Product";

    // Getters and Setters
    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public String getCustomerAddress1() { return customerAddress1; }
    public void setCustomerAddress1(String customerAddress1) { this.customerAddress1 = customerAddress1; }
    public String getCustomerCity() { return customerCity; }
    public void setCustomerCity(String customerCity) { this.customerCity = customerCity; }
    public String getCustomerPostcode() { return customerPostcode; }
    public void setCustomerPostcode(String customerPostcode) { this.customerPostcode = customerPostcode; }
    public String getCustomerCountry() { return customerCountry; }
    public void setCustomerCountry(String customerCountry) { this.customerCountry = customerCountry; }
    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public String getProductCategory() { return productCategory; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
}