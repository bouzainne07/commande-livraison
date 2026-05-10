package com.example.demo.controller;

import com.example.demo.entity.Transporteur;
import com.example.demo.service.TransporteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transporteurs")
@CrossOrigin(origins = "*")
public class TransporteurController {

    @Autowired
    private TransporteurService transporteurService;

    @GetMapping
    public List<Transporteur> getAllTransporteurs() {
        return transporteurService.getAllTransporteurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transporteur> getById(@PathVariable Long id) {
        return transporteurService.getTransporteurById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transporteur create(@RequestBody Transporteur transporteur) {
        return transporteurService.saveTransporteur(transporteur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transporteur> update(@PathVariable Long id, @RequestBody Transporteur transporteur) {
        return transporteurService.getTransporteurById(id)
            .map(existing -> {
                transporteur.setId(id);
                return ResponseEntity.ok(transporteurService.saveTransporteur(transporteur));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transporteurService.deleteTransporteur(id);
        return ResponseEntity.ok().build();
    }
}