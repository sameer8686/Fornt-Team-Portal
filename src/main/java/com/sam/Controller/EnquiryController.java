package com.sam.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sam.bindingClasses.DashboardResponse;
import com.sam.bindingClasses.EnquiryForm;
import com.sam.bindingClasses.EnquirySearchCriteria;
import com.sam.entities.StudentEnqEntity;
import com.sam.services.EnquiryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqService;

	@Autowired
	private HttpSession session;

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {

		Integer userId = (Integer) session.getAttribute("userId");

		DashboardResponse dashboardData = enqService.getDashboardData(userId);

		model.addAttribute("dashboardData", dashboardData);

		return "dashboard";
	}

	@PostMapping("/addEnq")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model) {

		// save the data
		boolean status = enqService.saveEnquriry(formObj);

		if (status) {
			model.addAttribute("succMsg", "Enquiry Added");
		} else {
			model.addAttribute("errMsg", "Problem Occured");
		}

		return "add-enquiry";
	}

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {

		initForm(model);

		return "add-enquiry";
	}

	private void initForm(Model model) {
		// get courses for drop down
		List<String> courses = enqService.getCourses();

		// get enq status for drop down
		List<String> enqStatuses = enqService.getEnqStatus();

		// create binding class obj
		EnquiryForm formObj = new EnquiryForm();

		// set data in model obj
		model.addAttribute("courseNames", courses);
		model.addAttribute("statusNames", enqStatuses);
		model.addAttribute("formObj", formObj);

	}

	@GetMapping("/enquires")
	public String viewEnquiriesPage(EnquirySearchCriteria criteria, Model model) {
		initForm(model);
		model.addAttribute("searchForm", new EnquirySearchCriteria());
		List<StudentEnqEntity> enquiries = enqService.getEnquiries();
		model.addAttribute("enquiries", enquiries);
		return "view-enquiries";
	}
	
	@GetMapping("/filter-enquiries")
	public String getFilteredEnqs(@RequestParam String cname,
			@RequestParam String status,
			@RequestParam String mode,
			Model model) {
		
		EnquirySearchCriteria criteria= new EnquirySearchCriteria();
		criteria.setCourseName(cname);
		criteria.setClassMode(mode);
		criteria.setEnqStatus(status);
		
		Integer userId = (Integer) session.getAttribute("userId");

		
		List<StudentEnqEntity> filteredEnqs=
				enqService.getFilteredEnqs(criteria, userId);
		
		model.addAttribute("enquiries", filteredEnqs);
		
		return "filter-enquiries-page";
	}
	
	
	 
	
	
	

}
