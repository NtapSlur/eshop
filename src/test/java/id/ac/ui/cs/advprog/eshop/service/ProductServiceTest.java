package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @MockBean
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl service;

    private static ArrayList<Product> productList;
    private static int productId = 0;

    @BeforeEach
    void setUp(){
        productList = new ArrayList<>();
    }

    @AfterEach
    void deleteList()
    {
        productId = 0;
        productList = new ArrayList<>();
    }

    Iterator<Product> findAll()
    {
        return productList.iterator();
    }

    Product createProduct(String productName, int productQuantity)
    {
        Product product = new Product();
        product.setProductId(String.valueOf(productId));
        productId++;
        product.setProductName(productName);
        product.setProductQuantity(productQuantity);
        return product;
    }

    boolean delete(String productId)
    {
        int productIndex = findIndexProduct(productId);
        if (productIndex != -1)
        {
            productList.remove(productIndex);
            return true;
        }
        return false;
    }
    int findIndexProduct(String productId)
    {
        for (Product product : productList)
        {
            if (product.getProductId().equals(productId))
            {
                return productList.indexOf(product);
            }
        }
        return -1;
    }
    Product findProduct(String productId)
    {
        int productIndex = findIndexProduct(productId);
        if (productIndex == -1)
        {
            return null;
        }
        return productList.get(productIndex);
    }
    void createAndSaveProduct(Product product)
    {
        productList.add(product);
        service.create(product);
    }

    Product edit(Product product)
    {
        Product productUpdate = findProduct(product.getProductId());
        if (productUpdate == null)
        {
            return null;
        }
        productUpdate.setProductName(product.getProductName());
        productUpdate.setProductQuantity(product.getProductQuantity());
        return productUpdate;
    }

    @Test
    void testCreateProduct(){
        Product product = createProduct("Ayam", 10);
        createAndSaveProduct(product);
    }

    @Test
    void testCreateAndDelete()
    {
        Product product = createProduct("Ayam", 10);
        createAndSaveProduct(product);

        assertNotNull(findProduct("0"));
        service.findProduct("0");

        when(productRepository.delete("0")).thenReturn(delete("0"));
        service.delete("0");

        assertNull(findProduct("0"));
    }

    @Test
    void testCreateAndEdit()
    {
        Product product = createProduct("Bakso", 10);
        createAndSaveProduct(product);
        product.setProductName("Ayam");
        when(productRepository.edit(product)).thenReturn(edit(product));
        service.edit(product);
        assertEquals(findProduct(product.getProductId()).getProductName(), "Ayam");
    }

    @Test
    void testFindIndexProduct()
    {
        Product product = createProduct("Ayam", 10);
        createAndSaveProduct(product);

        Product product2 = createProduct("Indomie", 11);
        createAndSaveProduct(product2);

        assertEquals(String.valueOf(findIndexProduct("1")), "1");
        service.findIndexProduct("1");
    }

    @Test
    void testFindAllProduct()
    {
        createAndSaveProduct(createProduct("Ayam", 10));
        createAndSaveProduct(createProduct("Indomie", 11));
        createAndSaveProduct(createProduct("Bakso", 11));

        when(productRepository.findAll()).thenReturn(findAll());
        List<Product> iterator = service.findAll();
        assertEquals(3, iterator.size());

        assertEquals("Ayam", iterator.get(0).getProductName());
        assertEquals("Indomie", iterator.get(1).getProductName());
        assertEquals("Bakso", iterator.get(2).getProductName());
    }
}