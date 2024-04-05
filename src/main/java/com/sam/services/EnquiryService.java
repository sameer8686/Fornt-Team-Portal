package com.sam.services;

import java.util.List;
import java.util.Optional;

import com.sam.bindingClasses.DashboardResponse;
import com.sam.bindingClasses.EnquiryForm;
import com.sam.bindingClasses.EnquirySearchCriteria;
import com.sam.entities.StudentEnqEntity;

public interface EnquiryService {
	
	public List<String> getCourses();
	
    public List<String> getEnqStatus();
    
    public boolean saveEnquriry(EnquiryForm form);
	
	public DashboardResponse getDashboardData(Integer userId);
	
	public List<StudentEnqEntity> getEnquiries();
	
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria,Integer userId);

	
	
	
	
	
}
