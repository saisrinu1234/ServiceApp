package com.example.serviceapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.serviceapplication.model.ScNumber;

import jakarta.transaction.Transactional;

@Repository
public interface ScNumberRepository extends JpaRepository<ScNumber, Integer> {

   public boolean existsByscNumber(String ScNumber);
   @Transactional
   public void deleteByscNumber(String ScNumber);

   public ScNumber findByscNumber(String scNumber);

}
