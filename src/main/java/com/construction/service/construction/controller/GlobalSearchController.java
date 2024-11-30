package com.construction.service.construction.controller;

import com.construction.service.construction.repository.ClientRepository;
import com.construction.service.construction.repository.ServiceRepository;
import com.construction.service.construction.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/global-search")
public class GlobalSearchController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRequestRepository requestRepository;

    @GetMapping
    public Map<String, Object> globalSearch(@RequestParam String query) {
        Map<String, Object> results = new HashMap<>();
        results.put("services", serviceRepository.findByNameContainingIgnoreCase(query));
        results.put("clients", clientRepository.findByNameContainingIgnoreCase(query));
        results.put("requests", requestRepository.findByStatusContainingIgnoreCase(query));
        return results;
    }
}