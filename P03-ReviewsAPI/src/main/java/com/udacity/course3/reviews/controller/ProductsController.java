package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.persistance.PersistenceService;
import com.udacity.course3.reviews.repository.jpa.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    private final PersistenceService persistenceService;

    public ProductsController(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    /**
     * Creates a product.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(Product product) {
        persistenceService.createProduct(product);
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
        Optional<Product> optionalProduct = persistenceService.findPorductById(id);
        if(!optionalProduct.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalProduct.get());
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> listProducts() {
        return persistenceService.listProducts();
    }
}