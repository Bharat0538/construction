package com.construction.service.construction.controller;

import com.construction.service.construction.model.ServiceRequest;
import com.construction.service.construction.repository.ClientRepository;
import com.construction.service.construction.repository.ServiceRepository;
import com.construction.service.construction.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
/*
POST /api/requests?clientId=1&serviceId=1
PUT /api/requests/1?status=Completed
 */
@RestController
@RequestMapping("/api/requests")
public class ServiceRequestController {
    @Autowired
    private ServiceRequestRepository requestRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public List<ServiceRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    @PostMapping
    public ServiceRequest createRequest(@RequestParam Long clientId, @RequestParam Long serviceId) {
        ServiceRequest request = new ServiceRequest();
        request.setClient(clientRepository.findById(clientId).orElseThrow());
        request.setService(serviceRepository.findById(serviceId).orElseThrow());
        request.setRequestDate(LocalDate.now());
        request.setStatus("Pending");
        return requestRepository.save(request);
    }

    @PutMapping("/{id}")
    public ServiceRequest updateRequestStatus(@PathVariable Long id, @RequestParam String status) {
        ServiceRequest request = requestRepository.findById(id).orElseThrow();
        request.setStatus(status);
        return requestRepository.save(request);
    }

    /**
     * GET /api/requests/by-client/{clientId}
     * Retrieve all requests for a specific client.
     * @param clientId
     * @return
     */

    @GetMapping("/by-client/{clientId}")
    public List<ServiceRequest> getRequestsByClient(@PathVariable Long clientId) {
        return requestRepository.findByClientId(clientId);
    }

    /**
     * GET /api/requests/by-status?status={status}
     * Retrieve all requests with a specific status.
     * @param status
     * @return
     */

    @GetMapping("/by-status")
    public List<ServiceRequest> getRequestsByStatus(@RequestParam String status) {
        return requestRepository.findByStatus(status);
    }

    /**
     * DELETE /api/requests/{id}
     * Remove a service request from the database.
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteServiceRequest(@PathVariable Long id) {
        requestRepository.deleteById(id);
    }

    /**
     * PUT /api/requests/{id}
     * Update the status, client, or service of a request.
     * @param id
     * @param updatedRequest
     * @return
     */
    @PutMapping("/service/{id}")
    public ServiceRequest updateServiceRequest(
            @PathVariable Long id,
            @RequestBody ServiceRequest updatedRequest) {
        ServiceRequest request = requestRepository.findById(id).orElseThrow();
        request.setClient(updatedRequest.getClient());
        request.setService(updatedRequest.getService());
        request.setStatus(updatedRequest.getStatus());
        return requestRepository.save(request);
    }
}