package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.controller.CarController;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl service;

    @MockBean
    private CarServiceImpl carService;

    @InjectMocks
    ProductController controller;

    @InjectMocks
    CarController carController;

    private static ArrayList<Product> productList;
    private static int productId = 0;

    Product createProduct(String productName, int productQuantity)
    {
        Product product = new Product();
        product.setProductId(String.valueOf(productId));
        productId++;
        product.setProductName(productName);
        product.setProductQuantity(productQuantity);
        return product;
    }

    void createAndSaveProduct(Product product)
    {
        productList.add(product);
        service.create(product);
    }
    public int findIndexProduct(String productId)
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

    public Product findProduct(String productId)
    {
        int productIndex = findIndexProduct(productId);
        if (productIndex == -1)
        {
            return null;
        }
        return productList.get(productIndex);
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

    @Test
    void testProductList() throws Exception
    {
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product' List")));
    }

    @Test
    void testGetCreateProduct() throws Exception
    {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create New Product")));
    }

    @Test
    void testPostCreateProduct() throws Exception
    {
        Product product = createProduct("Ayam", 15);
        createAndSaveProduct(product);
        mockMvc.perform(post("/product/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testGetEditProduct() throws Exception
    {
        Product product = createProduct("Ayam", 15);
        createAndSaveProduct(product);
        when(service.findProduct("0")).thenReturn(findProduct("0"));
        mockMvc.perform(get("/product/edit/0"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Edit Product")));
    }

    @Test
    void testFailedGetEditProduct() throws Exception
    {
        Product product = createProduct("Ayam", 15);
        createAndSaveProduct(product);
        when(service.findProduct("1")).thenReturn(findProduct("1"));
        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testPostEditProduct() throws Exception
    {
        Product product = createProduct("Ayam", 15);
        createAndSaveProduct(product);
        when(service.edit(product)).thenReturn(edit(product));
        mockMvc.perform(post("/product/edit/0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testGetDeleteProduct() throws Exception
    {
        Product product = createProduct("Ayam", 15);
        createAndSaveProduct(product);
        assertEquals(1, productList.size());
        when(service.delete("0")).thenReturn(delete("0"));
        mockMvc.perform(get("/product/delete/0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
        assertEquals(0, productList.size());
    }




}

