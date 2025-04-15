package org.generation.hermedia.controller;

import org.generation.hermedia.model.Payment;
import org.generation.hermedia.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Payment> getPaymentById(@PathVariable String id) {
        return paymentRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable String id) {
        paymentRepository.deleteById(id);
    }
}
