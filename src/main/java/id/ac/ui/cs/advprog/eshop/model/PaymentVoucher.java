package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;

import java.util.Map;

public class PaymentVoucher extends Payment {
    public PaymentVoucher(String id, String method, Map<String, String> paymentData, Order order){
        super(id, method, paymentData, order);
    }

    public PaymentVoucher(String id, String method, Map<String, String> paymentData, Order order, String status){
        super(id, method, paymentData, order, status);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if ((!paymentData.get("voucherCode").startsWith("ESHOP")) || (paymentData.get("voucherCode").length() != 16)
        || (countNumeric(paymentData.get("voucherCode")) != 8)) {
            throw new IllegalArgumentException();
        }

        this.paymentData = paymentData;
    }

    public int countNumeric(String code) {
        int numeric = 0;
        for (int i = 0; i < code.length(); i++) {
            if (Character.isDigit(code.charAt(i))) {
                numeric++;
            }
        }
        return numeric;
    }

}
