package com.sam.entities;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "AIT_USER_DTLS")

public class UserDtlsEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	   
	    private Integer userId;

	   
	    private String name;

	   
	    private String email;

	   
	    private Long phno;

	   
	    private String pwd;

	  
	    private String accountStatus;

	    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
	    private List<StudentEnqEntity> enquiries;

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Long getPhno() {
			return phno;
		}

		public void setPhoneNumber(Long phno) {
			this.phno = phno;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPassword(String pwd) {
			this.pwd = pwd;
		}

		public String getAccountStatus() {
			return accountStatus;
		}

		public void setAccountStatus(String accountStatus) {
			this.accountStatus = accountStatus;
		}

		public List<StudentEnqEntity> getEnquiries() {
			return enquiries;
		}

		public void setEnquiries(List<StudentEnqEntity> enquiries) {
			this.enquiries = enquiries;
		}

	

		
	    // Constructors, getters, and setters
	
	
}
