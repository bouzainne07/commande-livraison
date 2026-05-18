package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody Client client,
                                           BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(e ->
                errors.put(e.getField(), e.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(clientService.saveClient(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id,
                                           @Valid @RequestBody Client client,
                                           BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(e ->
                errors.put(e.getField(), e.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }
        return clientService.getClientById(id)
            .map(existing -> {
                client.setId(id);
                return ResponseEntity.ok(clientService.saveClient(client));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}