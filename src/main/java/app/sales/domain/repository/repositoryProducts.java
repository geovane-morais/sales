package app.sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.sales.domain.entity.product;

public interface repositoryProducts extends JpaRepository<product, Integer>{

}