package com.construction.service.construction.controller;

import com.construction.service.construction.repository.ClientRepository;
import com.construction.service.construction.repository.ServiceRepository;
import com.construction.service.construction.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRequestRepository requestRepository;

    @GetMapping
    public Map<String, Long> generateReports() {
        Map<String, Long> report = new HashMap<>();
        report.put("totalServices", serviceRepository.count());
        report.put("totalClients", clientRepository.count());
        report.put("pendingRequests", requestRepository.countByStatus("Pending"));
        report.put("completedRequests", requestRepository.countByStatus("Completed"));
        return report;
    }
}