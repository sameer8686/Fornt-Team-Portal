package com.sam.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sam.bindingClasses.LoginForm;
import com.sam.bindingClasses.SignUpForm;
import com.sam.bindingClasses.UnlockForm;
import com.sam.services.EnquiryService;
import com.sam.services.UserService;

@Controller
public class UserController {
	
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public EnquiryService enqService;
	
	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String handlesSingUp(@ModelAttribute("user") SignUpForm form,Model model) {
		
		boolean status = userService.signUp(form);
		if(status) {
			model.addAttribute("succMsg","Account Created, Check Your Email");
		}else
		{
			model.addAttribute("errMsg","Choose uique Email");
		}
		
		return "signup";
		
	}
	
	
	 
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email,Model model) {
		UnlockForm unlockFormobj=new UnlockForm();
		unlockFormobj.setEmail(email);
		model.addAttribute("unlock", unlockFormobj);
		return "unlock";
	}
	
	 
		@PostMapping("/unlock")
		public String unlockUserAccount(@ModelAttribute("unlock")  UnlockForm unlock,Model model) {
			
			System.out.println(unlock);
			
			if(unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
				boolean status=userService.unlockAccount(unlock);
				
				if(status) {
					model.addAttribute("succMsg","Your Account unLocked Successfully");
				}else {
					model.addAttribute("errMsg","Give Temporary Pwd is Incorrect");
				}
				}else {
					model.addAttribute("errMsg","Both Pasdword Should be Same");
				}
			
			return "unlock";
		}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginForm",new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm form,Model model) {
		
		String status=userService.login(form);
		if(status.contains("success")) {
			
			return "redirect:/dashboard";
		} 
		 model.addAttribute("errMsg", status);
		return "login";
	}
	
		
	@GetMapping("/forgot")
	public String forgotPwdPage() {
		
		return "forgotPwd";
	}
	
	@PostMapping("/forgotPwd")
	public String forgotPwd(@RequestParam("email") String email,Model model) {
		
		boolean status=userService.forgotPwd(email);
		
		if(status) {
			model.addAttribute("succMsg","Pwd sent to your email");
		}else
			model.addAttribute("errMsg","Invaild email");

		return "forgotPwd";
		
	}
	
	
	
	
	
	
	
	
	
	
}
