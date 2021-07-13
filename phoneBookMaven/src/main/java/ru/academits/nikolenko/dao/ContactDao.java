package ru.academits.nikolenko.dao;

import ru.academits.nikolenko.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Anna on 17.06.2017.
 * Modified by Max on 08.07.2021
 */

public class ContactDao {
    private List<Contact> contactList = new ArrayList<>();
    private AtomicInteger idSequence = new AtomicInteger(0);

    public ContactDao() {
        Contact contact = new Contact();
        contact.setId(getNewId());
        contact.setFirstName("Иван");
        contact.setLastName("Иванов");
        contact.setPhone("9123456789");
        contactList.add(contact);
    }

    private int getNewId() {
        return idSequence.addAndGet(1);
    }

    public List<Contact> getAllContacts() {
        return contactList;
    }

    public List<Contact> getFilteredContacts(String filter) {
        List<Contact> result = new ArrayList<>();
        String upperCaseFilter = filter.toUpperCase();

        for (Contact contact : contactList) {
            String upperCaseFirstName = contact.getFirstName().toUpperCase();
            String upperCaseLastName = contact.getLastName().toUpperCase();
            String upperCasePhone = contact.getPhone().toUpperCase();

            if (upperCaseFirstName.contains(upperCaseFilter) || upperCaseLastName.contains(upperCaseFilter) || upperCasePhone.contains(upperCaseFilter)) {
                result.add(contact);
            }
        }

        return result;
    }

    public void add(Contact contact) {
        contact.setId(getNewId());
        contactList.add(contact);
    }

    public boolean deleteContact(int contactId) {
        Contact contact = findContact(contactId);

        if (contact == null) {
            return false;
        }

        return contactList.remove(contact);
    }

    private Contact findContact(int contactId) {
        for (Contact currentContact : contactList) {
            if (contactId == currentContact.getId()) {
                return currentContact;
            }
        }

        return null;
    }
}
