package com.sam.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "AIT_STUDENT_ENQUIRIES")
public class StudentEnqEntity {

	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    
	    private Integer enqId;


	    private String studentName;

	   
	    private Long studentPhno;

	    
	    private String classMode;

	   
	    private String courseName;

	   
	    private String enqStatus;

	  
	    @CreationTimestamp
	    private Date dataCreated;

	 
	    @UpdateTimestamp
	    private Date updatedDate;

	    @ManyToOne
	    @JoinColumn(name = "USER_ID", referencedColumnName = "userId")
	    private UserDtlsEntity userDetails;

		public Integer getEnqId() {
			return enqId;
		}

		public void setEnqId(Integer enqId) {
			this.enqId = enqId;
		}

		public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}

		public Long getStudentPhno() {
			return studentPhno;
		}

		public void setStudentPhno(Long studentPhno) {
			this.studentPhno = studentPhno;
		}

		public String getClassMode() {
			return classMode;
		}

		public void setClassMode(String classMode) {
			this.classMode = classMode;
		}

		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}

		public String getEnqStatus() {
			return enqStatus;
		}

		public void setEnqStatus(String enqStatus) {
			this.enqStatus = enqStatus;
		}

		public Date getDataCreated() {
			return dataCreated;
		}

		public void setDataCreated(Date dataCreated) {
			this.dataCreated = dataCreated;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}

		public UserDtlsEntity getUserDetails() {
			return userDetails;
		}

		public void setUserDetails(UserDtlsEntity userDetails) {
			this.userDetails = userDetails;
		}

		

	    // Constructors, getters, and setters
	
	
}
