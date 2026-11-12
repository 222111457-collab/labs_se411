
package edu.psu.se411.lab14.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.psu.se411.lab14.exception.ObjectNotFoundException;
import edu.psu.se411.lab14.model.Product;
import edu.psu.se411.lab14.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Product not found"));

        System.out.println("Updating product: " + existingProduct);

        Product updatedProduct = new Product(
            id,
            product.name(),
            product.description(),
            product.price()
        );

        productRepository.save(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Product not found"));

        productRepository.deleteById(id);
    }
}
