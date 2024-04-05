package com.sam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.entities.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity, Integer> {

}
