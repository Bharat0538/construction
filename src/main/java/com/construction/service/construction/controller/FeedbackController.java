package com.construction.service.construction.controller;

import com.construction.service.construction.model.Feedback;
import com.construction.service.construction.model.ServiceRequest;
import com.construction.service.construction.repository.FeedbackRepository;
import com.construction.service.construction.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @PostMapping
    public Feedback addFeedback(@RequestParam Long requestId, @RequestBody Feedback feedback) {
        ServiceRequest request = serviceRequestRepository.findById(requestId).orElseThrow();
        feedback.setServiceRequest(request);
        return feedbackRepository.save(feedback);
    }

    @GetMapping("/by-request/{requestId}")
    public List<Feedback> getFeedbackByRequest(@PathVariable Long requestId) {
        return feedbackRepository.findAllByServiceRequestId(requestId);
    }
}