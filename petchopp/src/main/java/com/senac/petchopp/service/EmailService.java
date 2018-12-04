package com.senac.petchopp.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public void enviar(String destinatario, String subject, String msg) {
		
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.rerum.com.br");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator("petchopp@rerum.com.br", "mudar1234!@#$"));
			email.setSSLOnConnect(false);
			
			email.setFrom("petchopp@rerum.com.br");
			email.setSubject(subject);
			email.setMsg(msg);
			email.addTo(destinatario);
			email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
	}

}
