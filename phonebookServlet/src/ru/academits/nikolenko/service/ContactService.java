package ru.academits.nikolenko.service;

import ru.academits.nikolenko.PhoneBook;
import ru.academits.nikolenko.dao.ContactDao;
import ru.academits.nikolenko.model.Contact;

import java.util.List;

public class ContactService {
    private ContactDao contactDao = PhoneBook.contactDao;

    private boolean isExistContactWithPhone(String phone) {
        List<Contact> contactList = contactDao.getAllContacts();

        for (Contact contact : contactList) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }

        return false;
    }

    public ContactValidation validateContact(Contact contact) {
        ContactValidation contactValidation = new ContactValidation();
        contactValidation.setValid(true);

        if (contact.getFirstName().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Имя должно быть заполнено.");
            return contactValidation;
        }

        if (contact.getLastName().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Фамилия должно быть заполнено.");
            return contactValidation;
        }

        if (contact.getPhone().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Номер телефона должно быть заполнено.");
            return contactValidation;
        }

        if (isExistContactWithPhone(contact.getPhone())) {
            contactValidation.setValid(false);
            contactValidation.setError("Номер телефона не должен дублировать другие номера в телефонной книге.");
            return contactValidation;
        }

        return contactValidation;
    }

    public ContactValidation addContact(Contact contact) {
        ContactValidation contactValidation = validateContact(contact);

        if (contactValidation.isValid()) {
            contactDao.add(contact);
        }

        return contactValidation;
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public DeleteResults deleteContacts(int[] contactsIds) {
        if (contactsIds.length == 0) {
            return new DeleteResults(false, "Массив с id контантами пуст");
        }

        StringBuilder error = new StringBuilder();
        error.append("Не удалось удалить контакты со следующими id: ");

        boolean deletedAll = true;

        for (int contactId : contactsIds) {
            if (!contactDao.deleteContact(contactId)) {
                deletedAll = false;
                error.append(contactId).append(", ");
            }
        }

        if (deletedAll) {
            return new DeleteResults(deletedAll, "");
        }

        return new DeleteResults(deletedAll, error.delete(error.length() - 2, error.length() - 1).toString());
    }

    public List<Contact> getFilteredContacts(String filter) {
        return contactDao.getFilteredContacts(filter);
    }
}
