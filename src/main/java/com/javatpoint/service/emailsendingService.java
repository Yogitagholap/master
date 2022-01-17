package com.javatpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.javatpoint.model.emailsending;
import com.javatpoint.repository.EmailRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
@Configuration
@PropertySource("gmail.properties")
@Component
// @ConfigurationProperties(prefix="spring.datasource")
public class emailsendingService {
	@Autowired
	
	   @Value("${from}")
    private String from;
	
	EmailRepository emailRepository;

	public List<emailsending> getAllEmail() {
		try {
			List<emailsending> emailsending = new ArrayList<emailsending>();
			emailRepository.findAll().forEach(emailsending1 -> emailsending.add(emailsending1));

			return emailsending;
		} catch (Exception e) {
			System.out.println(e);

		}
		return null;
	}
	//getting a specific record by using the method findById() of CrudRepository
		public emailsending getemailsendingById(int id) {
			return emailRepository.findById(id).get();
		}

	//saving a specific record by using the method save() of CrudRepository
		public void saveOrUpdate(emailsending emailsending) {
			emailRepository.save(emailsending);
		}

	//deleting a specific record by using the method deleteById() of CrudRepository
		public void delete(int id) {
			emailRepository.deleteById(id);
		}

	//updating a record
		public void update(emailsending emailsending, int id) {
			emailRepository.save(emailsending);
		}

	public void getSendEmail() {
		try {
		List<emailsending> emailsending = new ArrayList<emailsending>();
	//	emailRepository.findAll().forEach(emailsending1 -> emailsending.add(emailsending1));
		
		String email1 = "";
		// System.out.println(from);

		for (int i = 0; i < emailsending.size(); i++) {
			  emailsending em = emailsending.get(i);
			  String email = em.getEmail();
			  email1 = email1 + "," + email ;
			}
		  email1 = email1.substring(email1.indexOf(",")+1);
		String to = "gholapyogita97@gmail.com";
		final String user = "yogita.gholap@iristechsys.com";
		final String password = "iris@1234";

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			 message.setFrom(new InternetAddress(user , "Yogita"));
			 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			
			
//			String address =email1;
//			InternetAddress[] iAdressArray = InternetAddress.parse(address);
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
//			message.setRecipients(Message.RecipientType.CC,iAdressArray);

			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("This is message body");
			message.setSubject("Email Sending");
			messageBodyPart1.setContent("<h1>Dear USERNAME,\r\n"
					+ "\r\n"
					+ "We searched your profile in Linked-In and PERSNALIZED_STATEMENT\r\n"
					+ "\r\n"
					+ "We are an ISO 9001:2015 company, based out of Pune, India. We are into bespoke software development and mostly cater to the SME sector in North America and Europe.\r\n"
					+ "\r\n"
					+ "We undertake software development using different tech stacks, including machine learning, Product Lifecycle Management, Mainframes, Internet Of Things, Big Data, and mobility.\r\n"
					+ "\r\n"
					+ "Due to COVID outbreak, most of the companies have started working remotely. If there is any opportunity at your end, vis-a-vis remote working/ outsourcing of the development work, please let us know accordingly.\r\n"
					+ "\r\n"
					+ "Lastly, please find the link of SaaS and mobility applications that we have developed and the benefits that we offer.\r\n"
					+ "\r\n"
					+ "https://iristechsys.com/work.html\r\n"
					+ "\r\n"
					+ "https://iristechsys.com/benefits.html\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "Thanks and Regards\r\n"
					+ "Pranav Patil</h1>", "text/html");
		//	MimeBodyPart messageBodyPart2 = new MimeBodyPart();

		//	messageBodyPart2.attachFile(new File("mail.txt"));
		//	messageBodyPart2.setHeader("Content-Type", "text/plain; charset=\"us-ascii\"; name=\"mail.txt\"");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
		//	multipart.addBodyPart(messageBodyPart2);

			message.setContent(multipart);
			Transport.send(message);

			System.out.println("message sent....");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}
		} catch (Exception e) {
			System.out.println(e);

		}
	}

}