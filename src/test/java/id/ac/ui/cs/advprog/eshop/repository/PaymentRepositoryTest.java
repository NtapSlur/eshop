package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Product> products;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        payments = new ArrayList<>();
        orders = new ArrayList<>();
        products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("id1");
        product1.setProductName("Tes");
        product1.setProductQuantity(10);
        products.add(product1);

        Order order1 = new Order("id", products, 100L, "IniAuthor");
        orders.add(order1);

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12121212ABC");
        Payment payment1 = new Payment("idPayment", "VOUCHER", paymentData, order1);
        payments.add(payment1);
    }

    @Test
    void testSavePaymentSuccess() {
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(payment);

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());

    }

    @Test
    void testGetPayment() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);
        Payment result = paymentRepository.getPayment(payment.getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
    }

    @Test
    void testGetAllPayments() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);

        List<Payment> result = paymentRepository.getAllPayments();
        assertEquals(result, payments);
    }
}
