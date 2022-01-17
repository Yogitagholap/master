package com.javatpoint.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.javatpoint.model.emailsending;
import com.javatpoint.service.emailsendingService;



//mark class as Controller
@RestController
public class EmailController {
	
	@Autowired
	 emailsendingService emailSending;
	
//creating a get mapping that retrieves all the Email detail from the database 
	@GetMapping("/emailsending")
	private List<emailsending> getAllEmail() {
		return emailSending.getAllEmail();
	}
	
	@GetMapping("/emailsending11")
	private void getSendEmail() {
		 emailSending.getSendEmail();
	}
//creating a get mapping that retrieves the detail of a specific Email
	@GetMapping("/email/{id}")
	private emailsending getEmail(@PathVariable("id") int id) {
		return emailSending.getemailsendingById(id);
	}

//creating a delete mapping that deletes a specified Email
	@DeleteMapping("/email/{id}")
	private void deleteEmail(@PathVariable("id") int id) {
		emailSending.delete(id);
	}

//creating post mapping that post the Email detail in the database
	@PostMapping("/emailSave")
	private int saveEmail(@RequestBody emailsending emailsending) {
		emailSending.saveOrUpdate(emailsending);
		return emailsending.getId();
	}

//creating put mapping that updates the Email detail 
	@PutMapping("/emailUpdate")
	private emailsending update(@RequestBody emailsending emailsending) {
		emailSending.saveOrUpdate(emailsending);
		return emailsending;
	}
}
