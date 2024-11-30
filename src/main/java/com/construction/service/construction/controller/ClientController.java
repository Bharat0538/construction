package com.construction.service.construction.controller;

import com.construction.service.construction.model.Client;
import com.construction.service.construction.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    /**
     * GET /api/clients/search?name={name}
     * Search for clients by their name.
     * @param name
     * @return
     */
    @GetMapping("/search")
    public List<Client> searchClients(@RequestParam String name) {
        return clientRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * PUT /api/clients/{id}
     * Update client details (name, email, or phone number).
     * @param id
     * @param updatedClient
     * @return
     */

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client client = clientRepository.findById(id).orElseThrow();
        client.setName(updatedClient.getName());
        client.setEmail(updatedClient.getEmail());
        client.setPhone(updatedClient.getPhone());
        return clientRepository.save(client);
    }

    /**
     * DELETE /api/clients/{id}
     * Remove a client from the database.
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }

    /**
     * GET /api/clients?sortBy={field}&order={asc/desc}
     * Sort client data based on a field.
     * @param sortBy
     * @param order
     * @return
     */
    @GetMapping("/sort")
    public List<Client> getClients(@RequestParam String sortBy, @RequestParam String order) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return clientRepository.findAll(sort);
    }
}