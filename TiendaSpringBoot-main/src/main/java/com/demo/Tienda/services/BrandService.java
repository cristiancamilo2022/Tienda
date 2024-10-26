package com.demo.Tienda.services;

import com.demo.Tienda.entities.BrandEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BrandService {
    private final List<BrandEntity> brands;

    public BrandService() {
        brands = new ArrayList<>();
        brands.add(new BrandEntity(UUID.randomUUID(), "HP"));
        brands.add(new BrandEntity(UUID.randomUUID(), "Samsung"));
        brands.add(new BrandEntity(UUID.randomUUID(), "Apple"));
        brands.add(new BrandEntity(UUID.randomUUID(), "Xiaomi"));
        brands.add(new BrandEntity(UUID.randomUUID(), "LG"));
    }

    public List<BrandEntity> getAllBrands() {
        return brands;
    }

    public Optional<BrandEntity> getBrandById(UUID id) {
        return brands.stream().filter(brand -> brand.getId().equals(id)).findFirst();
    }

    public UUID createBrand(BrandEntity brand) {
        brand.setId(UUID.randomUUID());
        brands.add(brand);
        return brand.getId();
    }

    public Optional<BrandEntity> updateBrand(UUID id, BrandEntity updateBrand) {
        Optional<BrandEntity> existingBrand = getBrandById(id);
        existingBrand.ifPresent(brand -> {
            brand.setNombre(updateBrand.getNombre());
        });
        return existingBrand;
    }

    public boolean deleteBrand(UUID id) {
        return brands.removeIf(brand -> brand.getId().equals(id));
    }
}
