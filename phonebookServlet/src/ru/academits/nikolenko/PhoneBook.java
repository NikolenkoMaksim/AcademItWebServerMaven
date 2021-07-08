package ru.academits.nikolenko;

import ru.academits.nikolenko.coverter.*;
import ru.academits.nikolenko.dao.ContactDao;
import ru.academits.nikolenko.service.ContactService;

/**
 * Created by Anna on 17.06.2017.
 * Modified by Max on 08.07.2021
 */
public class PhoneBook {
    public static ContactDao contactDao = new ContactDao();

    public static ContactService phoneBookService = new ContactService();

    public static ContactConverter contactConverter = new ContactConverter();

    public static ContactValidationConverter contactValidationConverter = new ContactValidationConverter();

    public static DeleteResultsConverter deleteResultsConverter = new DeleteResultsConverter();

    public static ContactsIdsConverter contactsIdsConverter = new ContactsIdsConverter();

    public static FilterConverter filterConverter = new FilterConverter();
}
