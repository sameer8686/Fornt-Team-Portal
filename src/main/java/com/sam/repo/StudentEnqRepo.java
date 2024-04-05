package com.sam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.entities.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer> {

	

}
