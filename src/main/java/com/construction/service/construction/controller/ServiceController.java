package com.construction.service.construction.controller;

import com.construction.service.construction.model.Service;
import com.construction.service.construction.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @PostMapping
    public Service addService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceRepository.deleteById(id);
    }

    /**
     * GET /api/services/search?name={name}
       Search for services with a name containing the query string
     */
    @GetMapping("/search")
    public List<Service> searchServices(@RequestParam String name) {
        return serviceRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * PUT /api/services/{id}
     * Update the name, description, or price of a service.
     * @param id
     * @param updatedService
     * @return
     */

    @PutMapping("/{id}")
    public Service updateService(@PathVariable Long id, @RequestBody Service updatedService) {
        Service service = serviceRepository.findById(id).orElseThrow();
        service.setName(updatedService.getName());
        service.setDescription(updatedService.getDescription());
        service.setPrice(updatedService.getPrice());
        return serviceRepository.save(service);
    }

    /**
     * GET /api/services?page={page}&size={size}
     * Paginate service data.
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/pagination")
    public Page<Service> getServices(@RequestParam int page, @RequestParam int size) {
        return serviceRepository.findAll(PageRequest.of(page, size));
    }
}