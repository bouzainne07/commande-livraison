package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController                    // Dit à Spring : "cette classe répond aux requêtes HTTP en JSON"
@RequestMapping("/api/clients")    // Toutes les routes commencent par /api/clients
@CrossOrigin(origins = "*")        // Autorise Angular à appeler cette API
public class ClientController {

    @Autowired
    private ClientService clientService;

    // GET http://localhost:8080/api/clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // GET http://localhost:8080/api/clients/1
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // POST http://localhost:8080/api/clients
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    // PUT http://localhost:8080/api/clients/1
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.getClientById(id)
            .map(existing -> {
                client.setId(id);
                return ResponseEntity.ok(clientService.saveClient(client));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // DELETE http://localhost:8080/api/clients/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}