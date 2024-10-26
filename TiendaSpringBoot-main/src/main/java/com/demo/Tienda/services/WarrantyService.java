package com.demo.Tienda.services;

import com.demo.Tienda.entities.WarrantyEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WarrantyService {
    private final List<WarrantyEntity> warranties;

    public WarrantyService() {
        warranties = new ArrayList<>();
        warranties.add(new WarrantyEntity(UUID.randomUUID(), LocalDate.now(), LocalDate.now().plusYears(1)));
        warranties.add(new WarrantyEntity(UUID.randomUUID(), LocalDate.now(), LocalDate.now().plusYears(3)));
    }

    public List<WarrantyEntity> getAllWarranties() {
        return warranties;
    }

    public Optional<WarrantyEntity> getWarrantyById(UUID id) {
        return warranties.stream().filter(w -> w.getId().equals(id)).findFirst();
    }

    public Optional<WarrantyEntity> getWarrantyByProductId(UUID productId) {
        return warranties.stream()
                .filter(warranty -> warranty.getId().equals(productId))
                .findFirst();
    }

    public UUID createWarranty(WarrantyEntity warranty) {
        WarrantyEntity newWarranty = new WarrantyEntity(UUID.randomUUID(), warranty.getFechaInicio(), warranty.getFechaFin());
        warranties.add(newWarranty);
        return newWarranty.getId();
    }

    public Optional<WarrantyEntity> updateWarranty(UUID id, WarrantyEntity updateWarranty) {
        Optional<WarrantyEntity> existingWarranty = getWarrantyById(id);
        existingWarranty.ifPresent(warranty -> {
            warranty.setFechaInicio(warranty.getFechaInicio());
            warranty.setFechaFin(warranty.getFechaFin());
        });
        return existingWarranty;
    }

    public boolean deleteWarranty(UUID id) {

        return warranties.removeIf(w -> w.getId().equals(id));
    }
}
