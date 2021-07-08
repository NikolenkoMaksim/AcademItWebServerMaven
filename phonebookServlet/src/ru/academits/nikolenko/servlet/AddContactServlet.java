package ru.academits.nikolenko.servlet;

import ru.academits.nikolenko.PhoneBook;
import ru.academits.nikolenko.coverter.ContactConverter;
import ru.academits.nikolenko.coverter.ContactValidationConverter;
import ru.academits.nikolenko.model.Contact;
import ru.academits.nikolenko.service.ContactService;
import ru.academits.nikolenko.service.ContactValidation;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class AddContactServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 8452549545910443303L;

    private ContactService phoneBookService = PhoneBook.phoneBookService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;
    private ContactValidationConverter contactValidationConverter = PhoneBook.contactValidationConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (OutputStream responseStream = resp.getOutputStream()) {
            String contactJson = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            Contact contact = contactConverter.convertFormJson(contactJson);
            ContactValidation contactValidation = phoneBookService.addContact(contact);

            if (!contactValidation.isValid()) {
                resp.setStatus(500);
            }

            String contactValidationJson = contactValidationConverter.convertToJson(contactValidation);
            responseStream.write(contactValidationJson.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("error in AddContactsServlet: ");
            e.printStackTrace();
        }
    }
}
