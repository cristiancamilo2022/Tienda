package com.demo.Tienda.controllers;

import com.demo.Tienda.entities.ProductsEntity;
import com.demo.Tienda.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tienda/1.0/productos")
public class ProductsController {
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<List<ProductsEntity>> getProducts() {
        List<ProductsEntity> products = productsService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsEntity> getProduct(@PathVariable UUID id) {
        Optional<ProductsEntity> product = productsService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductsEntity product) {
        UUID createProduct = productsService.createProduct(product);
        return ResponseEntity.created(URI.create("/tienda/1.0/productos/" + createProduct)).body("Producto Creado Correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable UUID id, @RequestBody ProductsEntity product) {
        Optional<ProductsEntity> updatedProduct = productsService.updateProduct(id, product);
        return ResponseEntity.ok().body("Producto Actualizado Correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productsService.deleteProduct(id);
        return ResponseEntity.ok().body("Producto Eliminado Correctamente");
    }
}
