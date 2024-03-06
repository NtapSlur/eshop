package id.ac.ui.cs.advprog.eshop.model;

import enums.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;
    String status;

    public Payment(String id, String method, Map<String, String> paymentData, Order order){
        this.id = id;
        this.method = method;
        this.order = order;
        this.status = PaymentStatus.WAITING_PAYMENT.getValue();
        setPaymentData(paymentData);
    }

    public Payment(String id, String method, Map<String, String> paymentData, Order order, String status){
        this(id, method, paymentData, order);
        this.setStatus(status);
    }

    public void setStatus(String status)
    {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        }

        else {
            throw new IllegalArgumentException();
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if ((!PaymentMethod.contains(this.method) || (paymentData.isEmpty()))) {
            throw new IllegalArgumentException();
        }

        else {
            this.paymentData = paymentData;
        }
    }

}
