package com.construction.service.construction.repository;

import com.construction.service.construction.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    List<ServiceRequest> findByClientId(Long id);

    List<ServiceRequest> findByStatus(String status);

    List<ServiceRequest> findByStatusContainingIgnoreCase(String status);

    long countByStatus(String status);
}