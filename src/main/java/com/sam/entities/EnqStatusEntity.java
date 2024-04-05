package com.sam.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AIT_ENQUIRY_STATUS")
public class EnqStatusEntity {

	
	
	    @Id
	    @GeneratedValue
	   
	    private Integer statusId;

	    
	    private String statusName;

	    // Getters and setters

	    public Integer getStatusId() {
	        return statusId;
	    }

	    public void setStatusId(Integer statusId) {
	        this.statusId = statusId;
	    }

	    public String getStatusName() {
	        return statusName;
	    }

	    public void setStatusName(String statusName) {
	        this.statusName = statusName;
	    }

	
	
	
}
