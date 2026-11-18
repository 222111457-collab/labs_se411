package psu.edu.ch06.crud04.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import psu.edu.ch06.crud04.model.Product;
import psu.edu.ch06.crud04.repository.ProductRepository;

/**
 * Business layer for products: the controller talks only to this service,
 * and only this service talks to the repository (data access layer).
 */
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	public Product create(Product product) {
		// ignore any client-supplied id so a POST always inserts a new row
		return productRepository.save(new Product(null, product.name(), product.description(), product.price()));
	}

	/** Updates an existing product; returns empty if it does not exist. */
	public Optional<Product> update(Integer id, Product productUpdate) {
		return productRepository.findById(id)
				.map(existing -> productRepository.save(new Product(existing.id(),
						productUpdate.name(), productUpdate.description(), productUpdate.price())));
	}

	/** Deletes a product; returns false if it does not exist. */
	public boolean delete(Integer id) {
		if (!productRepository.existsById(id)) {
			return false;
		}
		productRepository.deleteById(id);
		return true;
	}
}
