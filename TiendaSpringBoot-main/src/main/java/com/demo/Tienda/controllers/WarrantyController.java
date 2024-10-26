package com.demo.Tienda.controllers;

import com.demo.Tienda.entities.ProductsEntity;
import com.demo.Tienda.entities.WarrantyEntity;
import com.demo.Tienda.services.ProductsService;
import com.demo.Tienda.services.WarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tienda/1.0/garantia")
public class WarrantyController {
    private final WarrantyService warrantyService;
    private final ProductsService productsService;

    @Autowired
    public WarrantyController(WarrantyService warrantyService, ProductsService productsService) {
        this.warrantyService = warrantyService;
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<List<WarrantyEntity>> getWarranties() {
        List<WarrantyEntity> warranties = warrantyService.getAllWarranties();
        return ResponseEntity.ok(warranties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarrantyEntity> getWarranties(@PathVariable UUID id) {
        Optional<WarrantyEntity> warranties = warrantyService.getWarrantyById(id);
        return warranties.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<WarrantyEntity> getWarrantiesByProduct(@PathVariable UUID productId) {
        Optional<WarrantyEntity> warranty = warrantyService.getWarrantyByProductId(productId);
        Optional<ProductsEntity> products = productsService.getProductById(productId);
        if (warranty.isPresent() && products.isPresent()) {
            return ResponseEntity.ok(warranty.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createWarranties(@RequestBody WarrantyEntity warranties) {
        UUID createWarrantyId = warrantyService.createWarranty(warranties);
        return ResponseEntity.created(URI.create("/tienda/1.0/garantia/" + createWarrantyId)).body("Garantia generada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateWarranties(@PathVariable UUID id, @RequestBody WarrantyEntity warranties) {
        Optional<WarrantyEntity> updateWarranties = warrantyService.updateWarranty(id, warranties);
        return ResponseEntity.ok().body("Garantia actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarranties(@PathVariable UUID id) {
        warrantyService.deleteWarranty(id);
        return ResponseEntity.ok("Garantia eliminada correctamente");
    }
}
