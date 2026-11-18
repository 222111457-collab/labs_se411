package psu.edu.ch06.crud04.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import psu.edu.ch06.crud04.model.Product;
import psu.edu.ch06.crud04.service.ProductService;

/**
 * Presentation layer: handles HTTP only (mapping, status codes) and delegates
 * all product logic to the ProductService.
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<Iterable<Product>> findAll() {
		return ResponseEntity.ok(productService.findAll());
	}

	@GetMapping("/{requestedId}")
	public ResponseEntity<Product> findById(@PathVariable Integer requestedId) {
		return productService.findById(requestedId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Void> createProduct(@RequestBody Product newProductRequest, UriComponentsBuilder ucb) {
		Product newProduct = productService.create(newProductRequest);
		URI locationOfNewProduct = ucb.path("/api/v1/products/{id}")
				.buildAndExpand(newProduct.id())
				.toUri();
		return ResponseEntity.created(locationOfNewProduct).build();
	}

	@PutMapping("/{requestedId}")
	public ResponseEntity<Void> putProduct(@PathVariable Integer requestedId, @RequestBody Product productUpdate) {
		return productService.update(requestedId, productUpdate).isPresent()
				? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{requestedId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Integer requestedId) {
		return productService.delete(requestedId)
				? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}

}
