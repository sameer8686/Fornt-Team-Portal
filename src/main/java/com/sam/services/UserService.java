package com.sam.services;

import com.sam.bindingClasses.LoginForm;
import com.sam.bindingClasses.SignUpForm;
import com.sam.bindingClasses.UnlockForm;

public interface UserService {
	
	
	
	public String login(LoginForm form);
	
	public boolean signUp(SignUpForm form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public boolean forgotPwd(String email);
	
	
	
	
	
	
	
	

}
