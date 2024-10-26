package com.demo.Tienda.services;

import com.demo.Tienda.entities.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final List<CustomerEntity> customers;

    public CustomerService() {
        customers = new ArrayList<>();
        customers.add(new CustomerEntity(UUID.randomUUID(), "Juan Pérez", "juan.perez@example.com"));
        customers.add(new CustomerEntity(UUID.randomUUID(), "Ana Gómez", "ana.gomez@example.com"));
    }

    public List<CustomerEntity> getAllCustomers() {
        return customers;
    }

    public Optional<CustomerEntity> getCustomerById(UUID id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }

    public UUID createCustomer(CustomerEntity customer) {
        CustomerEntity newCustomer = new CustomerEntity(UUID.randomUUID(), customer.getNombre(), customer.getEmail());
        customers.add(newCustomer);
        return newCustomer.getId();
    }

    public Optional<CustomerEntity> updateCustomer(UUID id, CustomerEntity updateCustomer) {
        Optional<CustomerEntity> existingCustomer = getCustomerById(id);
        existingCustomer.ifPresent(customer -> {
            customer.setNombre(updateCustomer.getNombre());
            customer.setEmail(updateCustomer.getEmail());
        });
        return existingCustomer;
    }

    public boolean deleteCustomer(UUID id) {
        return customers.removeIf(customer -> customer.getId().equals(id));
    }


}
