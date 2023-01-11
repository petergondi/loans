package com.project.loans.Controller;

import com.project.loans.Model.Contact;
import com.project.loans.Model.Loans;
import com.project.loans.Service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    ContactService contactService;
    @PostMapping("/create")
    ResponseEntity<Contact> createContact(@Validated @RequestBody Contact contact) {
        logger.info("Received Contact Details:"+contact.toString());
        //TODO
        //check duplicate phone
        return new ResponseEntity<>(contactService.saveContact(contact), HttpStatus.OK);
    }
    @GetMapping("/getcontacts")
    ResponseEntity<List<Contact>> getContact() {
        return new ResponseEntity<>(contactService.getContactAll(), HttpStatus.OK);
    }
    @GetMapping("/getcontact/{id}")
    ResponseEntity<Contact> oneContact(@PathVariable Long id) {
        return new ResponseEntity<>(contactService.getOneContact(id), HttpStatus.OK);
    }
}
