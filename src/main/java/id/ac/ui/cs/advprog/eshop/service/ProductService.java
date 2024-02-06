package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public boolean delete(String productId);
    public int findIndexProduct(String productId);
    public Product findProduct(String productId);
    public Product edit(Product product);
}
