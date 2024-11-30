package com.construction.service.construction.controller;

import com.construction.service.construction.model.Payment;
import com.construction.service.construction.model.ServiceRequest;
import com.construction.service.construction.repository.PaymentRepository;
import com.construction.service.construction.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @PostMapping
    public Payment makePayment(@RequestParam Long requestId, @RequestBody Payment payment) {
        ServiceRequest request = serviceRequestRepository.findById(requestId).orElseThrow();
        payment.setServiceRequest(request);
        return paymentRepository.save(payment);
    }

    @GetMapping("/by-request/{requestId}")
    public List<Payment> getPaymentsByRequest(@PathVariable Long requestId) {
        return paymentRepository.findAllByServiceRequestId(requestId);
    }
}