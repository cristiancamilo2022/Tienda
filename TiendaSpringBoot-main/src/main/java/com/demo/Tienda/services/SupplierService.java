package com.demo.Tienda.services;

import com.demo.Tienda.entities.SupplierEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierService {
    private final List<SupplierEntity> suppliers;

    public SupplierService() {
        suppliers = new ArrayList<>();
        suppliers.add(new SupplierEntity(UUID.randomUUID(), "Tech Distributors", "123 Main St"));
        suppliers.add(new SupplierEntity(UUID.randomUUID(), "Gadgets Supply Co.", "456 Elm St"));
        suppliers.add(new SupplierEntity(UUID.randomUUID(), "Electro World", "789 Oak St"));
        suppliers.add(new SupplierEntity(UUID.randomUUID(), "Digital Hub", "321 Pine St"));
        suppliers.add(new SupplierEntity(UUID.randomUUID(), "Future Electronics", "654 Maple St"));
        suppliers.add(new SupplierEntity(UUID.randomUUID(), "Innovative Tech", "987 Cedar St"));
    }

    public List<SupplierEntity> getAllSuppliers() {
        return suppliers;
    }

    public Optional<SupplierEntity> getSupplierById(UUID id) {
        return suppliers.stream().filter(supplier -> supplier.getId().equals(id)).findFirst();
    }

    public UUID createSupplier(SupplierEntity supplier) {
        SupplierEntity newSupplier = new SupplierEntity(UUID.randomUUID(), supplier.getNombre(), supplier.getDireccion());
        suppliers.add(newSupplier);
        return newSupplier.getId();
    }

    public Optional<SupplierEntity> updateSupplier(SupplierEntity UpdateSupplier) {
        Optional<SupplierEntity> existingSupplier = getSupplierById(UpdateSupplier.getId());
        existingSupplier.ifPresent(supplier -> {
            supplier.setNombre(UpdateSupplier.getNombre());
            supplier.setDireccion(UpdateSupplier.getDireccion());
        });
        return existingSupplier;
    }

    public boolean deleteSupplier(UUID id) {
        return suppliers.removeIf(supplier -> supplier.getId().equals(id));
    }
}

