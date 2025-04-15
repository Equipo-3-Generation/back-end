package org.generation.hermedia.repository;

import org.generation.hermedia.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
}