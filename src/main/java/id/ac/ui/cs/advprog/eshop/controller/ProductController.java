package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController
{
    @Autowired
    private ProductService service;

    @GetMapping("/product/create")
    public String createProductPage(Model model)
    {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute Product product, Model model)
    {
        service.create(product);
        return "redirect:/product/list";
    }

    @GetMapping("/product/list")
    public String productListPage(Model model)
    {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/product/delete/{productId}")
    public String deleteProductPost(Model model, @PathVariable String productId)
    {
        service.delete(productId);
        return "redirect:/product/list";
    }

    @GetMapping("/product/edit/{productId}")
    public String editProductPage(Model model, @PathVariable String productId){
        Product product = service.findProduct(productId);
        if (product == null)
        {
            return "redirect:/product/list";
        }
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/product/edit/{productId}")
    public String editProductPost(@ModelAttribute Product product, Model model)
    {
        service.edit(product);
        return "redirect:/product/list";
    }

    @GetMapping("")
    public String homePage(Model model)
    {
        return "HomePage";
    }
}


