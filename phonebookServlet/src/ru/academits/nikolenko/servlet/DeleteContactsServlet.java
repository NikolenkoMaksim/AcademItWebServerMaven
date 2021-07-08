package ru.academits.nikolenko.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.academits.nikolenko.PhoneBook;
import ru.academits.nikolenko.coverter.ContactsIdsConverter;
import ru.academits.nikolenko.coverter.DeleteResultsConverter;
import ru.academits.nikolenko.service.ContactService;
import ru.academits.nikolenko.service.DeleteResults;

import java.io.OutputStream;
import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class DeleteContactsServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 3244259754264550630L;

    private DeleteResultsConverter deleteResultsConverter = PhoneBook.deleteResultsConverter;
    private ContactsIdsConverter contactsIdsConverter = PhoneBook.contactsIdsConverter;

    private ContactService phoneBookService = PhoneBook.phoneBookService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (OutputStream responseStream = resp.getOutputStream()) {
            String contactsIdsJson = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            int[] contactsIds = contactsIdsConverter.convertFormJson(contactsIdsJson);

            if (contactsIds == null) {
                resp.setStatus(500);
                resp.setHeader("Server message", "no array with contact id found");
            }

            DeleteResults deleteResults = phoneBookService.deleteContacts(contactsIds);

            String deleteResultsJson = deleteResultsConverter.convertToJson(deleteResults);
            responseStream.write(deleteResultsJson.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("error in DeleteContactsServlet: ");
            e.printStackTrace();
        }
    }
}
