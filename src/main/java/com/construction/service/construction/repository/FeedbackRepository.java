package com.construction.service.construction.repository;

import com.construction.service.construction.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByServiceRequestId(Long requestId);
}