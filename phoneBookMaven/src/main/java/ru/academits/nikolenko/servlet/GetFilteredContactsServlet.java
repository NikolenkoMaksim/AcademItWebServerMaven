package ru.academits.nikolenko.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.academits.nikolenko.PhoneBook;
import ru.academits.nikolenko.coverter.ContactConverter;
import ru.academits.nikolenko.coverter.FilterConverter;
import ru.academits.nikolenko.model.Contact;
import ru.academits.nikolenko.model.Filter;
import ru.academits.nikolenko.service.ContactService;

import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class GetFilteredContactsServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 6607163531760027756L;

    private ContactService phoneBookService = PhoneBook.phoneBookService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;
    private FilterConverter filterConverter = PhoneBook.filterConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String filterJson = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            Filter filter = filterConverter.convertFormJson(filterJson);
            List<Contact> contactList = phoneBookService.getFilteredContacts(filter.getFilter());

            String contactListJson = contactConverter.convertToJson(contactList);
            resp.getOutputStream().write(contactListJson.getBytes(StandardCharsets.UTF_8));
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } catch (Exception e) {
            System.out.println("error in GetFilteredContactsServlet: ");
            e.printStackTrace();
        }
    }
}
