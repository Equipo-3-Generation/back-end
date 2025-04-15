package org.generation.hermedia.repository;

import org.generation.hermedia.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}