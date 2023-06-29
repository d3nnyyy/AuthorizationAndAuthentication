package ua.tsebulia.AuthorizationAndAuthentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.tsebulia.AuthorizationAndAuthentication.entity.UserInfo;
import ua.tsebulia.AuthorizationAndAuthentication.model.Product;
import ua.tsebulia.AuthorizationAndAuthentication.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secured";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return productService.addUser(userInfo);
    }
}
