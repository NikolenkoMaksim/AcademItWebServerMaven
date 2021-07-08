package ru.academits.nikolenko.servlet;

import ru.academits.nikolenko.PhoneBook;
import ru.academits.nikolenko.coverter.ContactConverter;
import ru.academits.nikolenko.model.Contact;
import ru.academits.nikolenko.service.ContactService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetAllContactsServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -5962962709388820457L;

    private ContactService phoneBookService = PhoneBook.phoneBookService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Contact> contactList = phoneBookService.getAllContacts();
            String contactListJson = contactConverter.convertToJson(contactList);

            resp.getOutputStream().write(contactListJson.getBytes(StandardCharsets.UTF_8));
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } catch (Exception e) {
            System.out.println("error in GetAllContactsServlet: ");
            e.printStackTrace();
        }
    }
}
