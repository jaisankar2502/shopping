/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.service;

import com.shopping.exception.NotFoundException;
import com.innovaturelabs.training.contacts.form.ContactForm;
import com.shopping.view.ContactDetailView;
import com.shopping.view.ContactListView;
import java.util.Collection;

/**
 *
 * @author nirmal
 */
public interface ContactService {

    Collection<ContactListView> list();

    ContactDetailView add(ContactForm form);

    ContactDetailView get(Integer contactId) throws NotFoundException;

    ContactDetailView update(Integer contactId, ContactForm form) throws NotFoundException;

    void delete(Integer contactId) throws NotFoundException;
}
