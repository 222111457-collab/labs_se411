
package edu.psu.se411.lab14.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import edu.psu.se411.lab14.exception.ObjectNotFoundException;
import edu.psu.se411.lab14.model.Product;
import edu.psu.se411.lab14.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> op = productService.getProductById(id);

        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(
            @Valid @RequestBody Product product,
            UriComponentsBuilder ucb) {

        if (product.id() != null) {
            return ResponseEntity.badRequest().build();
        }

        Product createdProduct = productService.createProduct(product);

        URI uri = ucb.path("/api/v1/products/{id}")
                .buildAndExpand(createdProduct.id()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable @Positive Long id,
            @Valid @RequestBody Product product) {

        if (product.id() != null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            productService.updateProduct(id, product);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
