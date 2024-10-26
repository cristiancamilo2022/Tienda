package com.demo.Tienda.controllers;

import com.demo.Tienda.entities.CustomerEntity;
import com.demo.Tienda.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tienda/1.0/clientes")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getCostumers() {
        List<CustomerEntity> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustomer(@PathVariable UUID id) {
        Optional<CustomerEntity> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerEntity customer) {
        UUID createCustomer = customerService.createCustomer(customer);
        return ResponseEntity.created(URI.create("/tienda/1.0/clientes/" + createCustomer)).body("Cliente Creado Correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable UUID id, @RequestBody CustomerEntity customer) {
        Optional<CustomerEntity> updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok().body("Cliente Actualizado Correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().body("Cliente Eliminado Correctamente");
    }
}
