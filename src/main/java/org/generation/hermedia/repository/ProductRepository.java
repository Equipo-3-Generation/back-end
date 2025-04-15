package org.generation.hermedia.repository;

import org.generation.hermedia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}