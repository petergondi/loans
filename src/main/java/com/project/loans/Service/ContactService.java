package com.project.loans.Service;

import com.project.loans.Model.Contact;

import java.util.List;

public interface ContactService {
    Contact saveContact(Contact contact);

    List<Contact> getContactAll();

    Contact getOneContact(Long id);
}
