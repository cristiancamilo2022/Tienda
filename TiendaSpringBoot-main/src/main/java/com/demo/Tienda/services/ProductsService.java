package com.demo.Tienda.services;

import com.demo.Tienda.entities.ProductsEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {
    private final List<ProductsEntity> products;

    public ProductsService() {
        products = new ArrayList<>();
        UUID warrantyId = UUID.randomUUID();
        products.add(new ProductsEntity(UUID.randomUUID(), "Laptop", 3500000, warrantyId));
        products.add(new ProductsEntity(UUID.randomUUID(), "Smartphone", 1200000, warrantyId));
        products.add(new ProductsEntity(UUID.randomUUID(), "Tablet", 850000, warrantyId));
        products.add(new ProductsEntity(UUID.randomUUID(), "Smartwatch", 600000, warrantyId));
        products.add(new ProductsEntity(UUID.randomUUID(), "Monitor", 950000, warrantyId));
    }

    public List<ProductsEntity> getAllProducts() {
        return products;
    }

    public Optional<ProductsEntity> getProductById(UUID id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public UUID createProduct(ProductsEntity product) {
        product.setId(UUID.randomUUID());
        products.add(product);
        return product.getId();
    }

    public Optional<ProductsEntity> updateProduct(UUID id, ProductsEntity updateProduct) {
        Optional<ProductsEntity> existingProduct = getProductById(id);
        existingProduct.ifPresent(product -> {
            product.setNombre(updateProduct.getNombre());
            product.setPrecio(updateProduct.getPrecio());
            product.setWarrantyId(updateProduct.getWarrantyId());
        });
        return existingProduct;
    }

    public boolean deleteProduct(UUID id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}
