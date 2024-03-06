package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        this.products.add(product1);
        this.products.add(product2);

        this.orders = new ArrayList<>();
        Order order1 = new Order("id1", products, 1708560000L, "Aku");
        Order order2 = new Order("id2", products, 1708560000L, "Kamu");
        orders.add(order1);
        orders.add(order2);
    }

    @Test
    void testCreatePaymentUseVoucherSuccees(){
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP12345678ABC");

        Payment payment1 = new PaymentVoucher("idPayment", "VOUCHER", paymentDataVoucher, orders.get(0));
        assertSame(this.orders.get(0), payment1.getOrder());
        assertEquals("idPayment", payment1.getId());
        assertEquals("VOUCHER", payment1.getMethod());
    }

    @Test
    void testCreatePaymentIsVoucherFail(){
        Map<String, String> paymentDataVoucher = new  HashMap<>();
        paymentDataVoucher.put("voucherCode", "00000000AAA");
        assertThrows(IllegalArgumentException.class, ()-> {new PaymentVoucher("id1","VOUCHER",
                paymentDataVoucher, orders.get(1));
        });
    }

    @Test
    void testCreatePaymentIsBankFail(){
        Map<String, String> paymentDataBank = new  HashMap<>();
        paymentDataBank.put("bankName", "a");
        assertThrows(NullPointerException.class, ()-> {new PaymentBank("id1","BANK",
                paymentDataBank, orders.get(1));
        });
    }



}
