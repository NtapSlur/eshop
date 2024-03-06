package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentBank extends Payment {
    public PaymentBank(String id, String method, Map<String, String> paymentData, Order order){
        super(id, method, paymentData, order);
    }

    public PaymentBank(String id, String method, Map<String, String> paymentData, Order order, String status){
        super(id, method, paymentData, order, status);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.get("bankName").isBlank() || paymentData.get("referenceCode").isBlank()) {
            throw new IllegalArgumentException();
        }

        this.paymentData =  paymentData;
    }
}
