package app.sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.sales.domain.entity.user;

public interface repositoryUsers extends JpaRepository<user, Integer>{

}