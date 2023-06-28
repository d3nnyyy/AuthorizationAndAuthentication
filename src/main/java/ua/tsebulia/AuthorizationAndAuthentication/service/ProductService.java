package ua.tsebulia.AuthorizationAndAuthentication.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ua.tsebulia.AuthorizationAndAuthentication.model.Product;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> products = null;

    @PostConstruct
    public void loadProducts() {
        products = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .id((int) i)
                        .name("Product " + i)
                        .quantity(new Random().nextInt(10))
                        .price(new Random().nextInt() * 5000)
                        .build())
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products
                .stream()
                .filter(product -> product.getId() ==id )
                .findAny()
                .orElseThrow(
                        () -> new IllegalStateException("Product with id " + id + " does not exist")
                );
    }
}
