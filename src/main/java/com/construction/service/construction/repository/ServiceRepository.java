package com.construction.service.construction.repository;

import com.construction.service.construction.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findByNameContainingIgnoreCase(String name);
}