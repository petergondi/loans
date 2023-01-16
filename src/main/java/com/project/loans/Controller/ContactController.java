package com.project.loans.Controller;

import com.project.loans.Model.Contact;
import com.project.loans.Model.Loans;
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
            if(contactService.getByPhone(contact.getMSISDN())!=null) {
                return ResponseHandler.generateResponse("The contact already exists!!", HttpStatus.BAD_REQUEST, null);
            }
            if(contact.getMSISDN()==null || contact.getFullName()==null) {
                return ResponseHandler.generateResponse("Missing contact or fullname details!!", HttpStatus.BAD_REQUEST, null);
            }
            return ResponseHandler.generateResponse("Successfully added contact!", HttpStatus.CREATED, contactService.saveContact(contact));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    @GetMapping
    ResponseEntity<Object> getContact() {
        try {
            List<Contact> contact_result = contactService.getContactAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, contact_result);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    @GetMapping("/{id}")
    ResponseEntity<Object> oneContact(@PathVariable Long id) {
        try {
            Contact contact=contactService.getOneContact(id);
            if(contact==null){
                return ResponseHandler.generateResponse("Loan details do not exist", HttpStatus.NOT_FOUND,null);
            }
            return ResponseHandler.generateResponse("Found!", HttpStatus.OK, contact);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
