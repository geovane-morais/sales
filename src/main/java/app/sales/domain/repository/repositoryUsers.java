package app.sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.sales.domain.entity.user;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface repositoryUsers extends JpaRepository<user, Integer>{
    @Query("select u from user u where u.email = :email")
    Optional<user> findByEmail(@Param("email") String email);
    @Query("select u from user u where u.name = :name")
    Optional<user> findByName(@Param("name") String name);
}