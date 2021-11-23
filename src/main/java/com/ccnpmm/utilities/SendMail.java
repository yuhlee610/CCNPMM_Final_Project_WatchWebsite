package com.ccnpmm.utilities;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMail {
	@Autowired
    ServletContext context;
	
    @Autowired
    JavaMailSender mailSender;
    
    private String emailFrom = "nhomltweb@gmail.com";
    
    public boolean sendMailHandler(String to, String name) {
    	try {
    		MimeMessage message = mailSender.createMimeMessage();
    		MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailFrom);
            helper.setTo(to);
            helper.setReplyTo(emailFrom);
            helper.setSubject(name);
            helper.setText("Click link below to activate your account: <a>https://jqueryvalidation.org/</a>", true);
            mailSender.send(message);
            return true;
    	}
    	catch(Exception ex) {
    		System.out.println(ex.toString());
    		return false;
    	}
    }
}
