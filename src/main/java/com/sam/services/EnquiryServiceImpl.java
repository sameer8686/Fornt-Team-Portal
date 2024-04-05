package com.sam.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.bindingClasses.DashboardResponse;
import com.sam.bindingClasses.EnquiryForm;
import com.sam.bindingClasses.EnquirySearchCriteria;
import com.sam.entities.CourseEntity;
import com.sam.entities.EnqStatusEntity;
import com.sam.entities.StudentEnqEntity;
import com.sam.entities.UserDtlsEntity;
import com.sam.repo.CourseRepo;
import com.sam.repo.EnqStatusRepo;
import com.sam.repo.StudentEnqRepo;
import com.sam.repo.UserDtlsRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private StudentEnqRepo enqRepo;
	
	@Autowired
	private CourseRepo coursesRepo;
	
	@Autowired
	private EnqStatusRepo statusRepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public List<String> getCourses() {

		List<CourseEntity> findAll=coursesRepo.findAll();
		
		List<String> names=new ArrayList<>();
		
		for(CourseEntity entity : findAll) {
			names.add(entity.getCourseName());
		}
		
		return names;
	}

	@Override
	public List<String> getEnqStatus() {

		List<EnqStatusEntity> findAll=statusRepo.findAll();
		
		List<String> statusList=new ArrayList<>();
		
		for(EnqStatusEntity entity : findAll) {
			statusList.add(entity.getStatusName());
		}
		
		return statusList;
	}
	
	@Override
	public boolean saveEnquriry(EnquiryForm form) {

		StudentEnqEntity enqEntity =new StudentEnqEntity();
		BeanUtils.copyProperties(form, enqEntity);
		
		Integer userId=(Integer)session.getAttribute("userId");
		
		UserDtlsEntity userEntity=userDtlsRepo.findById(userId).get();
		enqEntity.setUserDetails(userEntity);
		
		enqRepo.save(enqEntity);
		
		return true;
	}
	
	

	@Override
	public DashboardResponse getDashboardData(Integer userId) {
		
		DashboardResponse reponse = new DashboardResponse();
		
		Optional<UserDtlsEntity> findById=
				userDtlsRepo.findById(userId);
		
		if(findById.isPresent()) {
			
			UserDtlsEntity userEntity = findById.get();
		
			List<StudentEnqEntity> enquiries=
					userEntity.getEnquiries();
		
			Integer totalCnt=enquiries.size();
			
			Integer enrolledCnt=enquiries.stream().
			          filter(e -> e.getEnqStatus().equals("Enrolled"))
			          .collect(Collectors.toList()).size();
			
			Integer lostCnt=enquiries.stream().
			          filter(e -> e.getEnqStatus().equals("Lost"))
			          .collect(Collectors.toList()).size();
			
			
			reponse.setEnrolledCnt(enrolledCnt);
			reponse.setLostCnt(lostCnt);
			reponse.setLostCnt(lostCnt);
			
		}
		
		
		
		return reponse;
	}

	@Override
	public List<StudentEnqEntity> getEnquiries() {
		Integer userId=(Integer)session.getAttribute("userId");
		Optional<UserDtlsEntity>findById=userDtlsRepo.findById(userId);
		if(findById.isPresent()) {
			UserDtlsEntity userDstlsEntity = findById.get();
			List<StudentEnqEntity> enquiries = userDstlsEntity.getEnquiries();
			return enquiries;
		}
		
		return null;
	}

	@Override
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId) {
		Optional<UserDtlsEntity>findById=userDtlsRepo.findById(userId);
		if(findById.isPresent()) {
			UserDtlsEntity userDstlsEntity = findById.get();
			List<StudentEnqEntity> enquiries = userDstlsEntity.getEnquiries();
			
			//filter logic
			
			if(null!=criteria.getCourseName() 
					&& !"".equals(criteria.getCourseName())) {
				
				enquiries=	enquiries.stream()
				         .filter(e -> e.getCourseName().equals(criteria.getCourseName()))
			             .collect(Collectors.toList());
			}
			
			if(null!=criteria.getEnqStatus() 
					&& !"".equals(criteria.getCourseName())) {
				
				enquiries=	enquiries.stream()
				         .filter(e -> e.getEnqStatus().equals(criteria.getEnqStatus()))
			             .collect(Collectors.toList());
			}
			
			if(null!=criteria.getClassMode() 
					&& !"".equals(criteria.getClassMode())) {
				
				enquiries=	enquiries.stream()
				         .filter(e -> e.getClassMode().equals(criteria.getClassMode()))
			             .collect(Collectors.toList());
			}
			
			return enquiries;
		}
		
		return null;

	}

	 

	
	

}
