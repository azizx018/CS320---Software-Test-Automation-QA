package org.example.Contact;

import org.example.exceptions.ValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactService {

    private static ContactService INSTANCE;
    public Map<String, Contact> contactsList = new ConcurrentHashMap<>();
    private ContactService() {}



    public static synchronized  ContactService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContactService();
        }
        return INSTANCE;
    }

    public boolean addContact(Contact contact) {
        return contactsList.putIfAbsent(contact.getId(), contact) == null;
    }

    public boolean removeContact(String id) {
        return contactsList.remove(id) != null;
    }

    public boolean updateContact(String id, Contact updatedContact) throws Exception {
        Contact existingContact = contactsList.get(id);
        if (existingContact != null) {
            existingContact.setFirstName(updatedContact.getFirstName());
            existingContact.setLastName(updatedContact.getLastName());
            existingContact.setNumber(updatedContact.getNumber());
            existingContact.setAddress(updatedContact.getAddress());
            return true;
        }
        return false;
    }
}
