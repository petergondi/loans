package com.project.loans.Service;

import com.project.loans.Model.Contact;
import com.project.loans.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;
    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
    @Override
    public List<Contact> getContactAll() {
        return contactRepository.findAll();
    }
    @Override
    public Contact getOneContact(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.orElse(null);
    }
}
