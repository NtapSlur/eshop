package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.*;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment;
        if (method.equals(PaymentMethod.VOUCHER.getValue())){
            payment = new PaymentVoucher(order.getId(), method, paymentData, order);
        }
        else if (method.equals(PaymentMethod.BANK.getValue())){
            payment = new PaymentBank(order.getId(), method, paymentData, order);
        }
        else{
            payment = new Payment(order, method, paymentData);
        }
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }


}
