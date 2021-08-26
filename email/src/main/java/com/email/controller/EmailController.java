package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.model.EmailResposne;
import com.email.service.EmailService;

import ch.qos.logback.core.subst.Token;

@RestController
@CrossOrigin
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/welcome")
	@ResponseBody
	public String welcome() {
		
		return "This is my email API";
	}
	
	//api to send email
	@RequestMapping(value="/sendemail",method=RequestMethod.POST)
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request)
	{
		
		
		boolean result = this.emailService.sendEmail(request.getMessage(), request.getSubject(),request.getTo());
		System.out.println(request);
		if(result) {
		}else {
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResposne("Email Sent Successfully...."));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EmailResposne("Email is sent Successfully......."));

	}

}
