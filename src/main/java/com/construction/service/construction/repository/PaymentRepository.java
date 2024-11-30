package com.construction.service.construction.repository;

import com.construction.service.construction.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByServiceRequestId(Long requestId);
}