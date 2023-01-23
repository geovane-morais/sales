package app.sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.sales.domain.entity.project;

public interface repositoryProjects extends JpaRepository<project, Integer>{

}