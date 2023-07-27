package com.dange.tanmay.repository;

import com.dange.tanmay.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity,Long> {
}
