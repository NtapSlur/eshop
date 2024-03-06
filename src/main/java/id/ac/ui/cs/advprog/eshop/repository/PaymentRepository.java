package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        checkPayment(payment);
        paymentData.add(payment);
        return payment;
    }

    public Payment getPayment(String id) {
        for (Payment paymentFromList : paymentData) {
            if (paymentFromList.getId().equals(id)) {
                return paymentFromList;
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        return paymentData;
    }

    public void checkPayment(Payment payment) {
        for (Payment paymentFromList : paymentData) {
            if (paymentFromList.getId().equals(payment.getId())) {
                throw new IllegalStateException();
            }
        }
    }

}
