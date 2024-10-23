package com.zhm.product_management_restful_api.managing.infrastructure;

import com.zhm.product_management_restful_api.managing.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(long id);
}
