package com.sam.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.bindingClasses.LoginForm;
import com.sam.bindingClasses.SignUpForm;
import com.sam.bindingClasses.UnlockForm;
import com.sam.constants.AppConstants;
import com.sam.entities.UserDtlsEntity;
import com.sam.repo.UserDtlsRepo;
import com.sam.util.EmailUtils;
import com.sam.util.PwdUtils;

import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override  
	public String login(LoginForm form) {
		UserDtlsEntity entity=
				userDtlsRepo.findByEmailAndPwd(form.getEmail(),form.getPwd());
		
		if(entity == null) {
			return "Invaild Credentials";
		}
		
		if(entity.getAccountStatus().equals("LOCKED")) {
			return "Your Account Locked";
		}
		
		session.setAttribute("userId", entity.getUserId());
		
		return "success";				
		
	}

	@Override
	public boolean signUp(SignUpForm form) {
		UserDtlsEntity user=  userDtlsRepo.findByEmail(form.getEmail());
		if(user!=null) {
			return false;
		}
		
		// copy data from binding obj to entity
		UserDtlsEntity entity=new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);
		
		
		// TODO : To Generate Random password
		String tempPwd=PwdUtils.generateRandomPwd();
		entity.setPassword(tempPwd);
		
		//set account status as Locked
		entity.setAccountStatus(AppConstants.STR_LOCKED);
		
		//Insert record
		userDtlsRepo.save(entity);
		
		//send email to unlock the account
		String to=form.getEmail();
		String subject= AppConstants.UNLOCK_EMAIL_SUBJECT;
		
		StringBuffer body=new StringBuffer("");
		body.append("<h1>Use Below Temporary Password to unlock Your Acoount</h1>");
		body.append("Temporary pwd :"+tempPwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:1010/unlock?email=" + to + "\">Click Here To Unlock Your Account</a>");
		emailUtils.sendEmail(to, subject, body.toString());
		return false;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {
			
		UserDtlsEntity entity=userDtlsRepo.findByEmail(form.getEmail());
		
		if(entity.getPwd().equals(form.getTempPwd())){
			entity.setPassword(form.getNewPwd());
			entity.setAccountStatus(AppConstants.STR_UNLOCKED);
			userDtlsRepo.save(entity);
			return true;
		}else {
		
		return false;
		}
	}

	@Override
	public boolean forgotPwd(String email) {
		// check record present in db  with given email
		UserDtlsEntity entity=userDtlsRepo.findByEmail(email);
		
		
		//if record not avaliable send error message
		
		if(entity == null) {
			return false;
		}
		
		//if record avaliable in db then send owd to email and send success msg
		
		String subject="Recover Password";
		String body="Your Pwd::"+entity.getPwd();
		
		emailUtils.sendEmail(email, subject, body);
		
		return true ;
	}

}
