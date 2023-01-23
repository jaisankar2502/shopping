/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.service.impl;

import com.shopping.entity.Contact;
import com.shopping.exception.NotFoundException;
import com.innovaturelabs.training.contacts.form.ContactForm;
import com.shopping.repository.ContactRepository;
import com.shopping.security.util.SecurityUtil;
import com.shopping.service.ContactService;
import com.shopping.view.ContactDetailView;
import com.shopping.view.ContactListView;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nirmal
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Collection<ContactListView> list() {
        return contactRepository.findAllByUserUserId(SecurityUtil.getCurrentUserId());
    }

    @Override
    public ContactDetailView add(ContactForm form) {
        return new ContactDetailView(contactRepository.save(new Contact(form, SecurityUtil.getCurrentUserId())));
    }

    @Override
    public ContactDetailView get(Integer contactId) throws NotFoundException {
        return contactRepository.findByContactIdAndUserUserId(contactId, SecurityUtil.getCurrentUserId())
                .map((contact) -> {
                    return new ContactDetailView(contact);
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public ContactDetailView update(Integer contactId, ContactForm form) throws NotFoundException {
        return contactRepository.findByContactIdAndUserUserId(contactId, SecurityUtil.getCurrentUserId())
                .map((contact) -> {
                    return new ContactDetailView(contactRepository.save(contact.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public void delete(Integer contactId) throws NotFoundException {
        contactRepository.delete(
                contactRepository.findByContactIdAndUserUserId(contactId, SecurityUtil.getCurrentUserId())
                        .orElseThrow(NotFoundException::new)
        );
    }
}
