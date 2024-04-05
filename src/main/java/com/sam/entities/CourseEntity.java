package com.sam.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AIT_COURSES")
public class CourseEntity {
	
	    @Id
	    @GeneratedValue
	    
	    private Integer courseId;

	   
	    private String courseName;

	    // Getters and setters

	    public Integer getCourseId() {
	        return courseId;
	    }

	    public void setCourseId(Integer courseId) {
	        this.courseId = courseId;
	    }

	    public String getCourseName() {
	        return courseName;
	    }

	    public void setCourseName(String courseName) {
	        this.courseName = courseName;
	    }
	
	
	
	
	
}
