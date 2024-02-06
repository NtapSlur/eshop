package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository
{
    private List<Product> productData = new ArrayList<>();
    private static int idCounter = 0;

    public Product create(Product product)
    {
        product.setProductId(String.valueOf(idCounter));
        idCounter++;
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll()
    {
        return productData.iterator();
    }

    public boolean delete(String productId)
    {
        int productIndex = findIndexProduct(productId);
        if (productIndex != -1)
        {
            productData.remove(productIndex);
            return true;
        }
        return false;
    }

    public int findIndexProduct(String productId)
    {
        for (Product product : productData)
        {
            if (product.getProductId().equals(productId))
            {
                return productData.indexOf(product);
            }
        }
        return -1;
    }

    public Product findProduct(String productId)
    {
        int productIndex = findIndexProduct(productId);
        return productData.get(productIndex);
    }

    public Product updateProduct(Product product)
    {
        Product productUpdate = findProduct(product.getProductId());
        productUpdate.setProductName(product.getProductName());
        productUpdate.setProductQuantity(product.getProductQuantity());
        return productUpdate;
    }
}