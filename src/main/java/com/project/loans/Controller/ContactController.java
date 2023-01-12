package com.project.loans.Controller;

import com.project.loans.Model.Contact;
import com.project.loans.Model.ResponseHandler;
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
    ResponseEntity<Object> createContact(@Validated @RequestBody Contact contact) {
        try {
            logger.info("Received Contact Details:" + contact.toString());
            //TODO
            //check duplicate phone
            return ResponseHandler.generateResponse("Successfully added contact!", HttpStatus.OK, contactService.saveContact(contact));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
    @GetMapping("/getcontacts")
    ResponseEntity<List<Contact>> getContact() {
        return new ResponseEntity<>(contactService.getContactAll(), HttpStatus.OK);
    }
    @GetMapping("/getcontact/{id}")
    ResponseEntity<Object> oneContact(@PathVariable Long id) {
        try {
            return ResponseHandler.generateResponse("Successfully added contact!", HttpStatus.OK, contactService.getOneContact(id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
