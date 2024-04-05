package com.sam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.entities.EnqStatusEntity;

public interface  EnqStatusRepo extends JpaRepository<EnqStatusEntity, Integer> {

}
