package br.com.serratec.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

	@Autowired(required = true)
	private JavaMailSender mail;
	
	
	public void sendMail(String para, String assunto, String texto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("rschanuel@faeterj-petropolis.edu.br");
		message.setTo(para);
		message.setSubject(assunto);
		message.setText(texto);
		mail.send(message);
	}
}
