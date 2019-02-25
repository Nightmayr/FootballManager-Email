package com.qa.email.rest;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.email.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	EmailService service;

	@RequestMapping(value = "/sendemail")
	public String sendEmail() throws AddressException, MessagingException, IOException {
		service.sendmail();
		return "Email sent succesfully.";
	}
}
