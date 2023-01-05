package app.sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.sales.domain.entity.log;

public interface repositoryLogs extends JpaRepository<log, Integer>{

}