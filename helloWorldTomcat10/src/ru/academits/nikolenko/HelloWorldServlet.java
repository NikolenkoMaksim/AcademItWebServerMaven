package ru.academits.nikolenko;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.nio.charset.StandardCharsets;

public class HelloWorldServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 6056418773018318646L;

    protected void doGet(HttpServletRequest reg, HttpServletResponse resp) throws IOException {
        resp.getOutputStream().write("Hello from HelloWorldServlet. Method: GET".getBytes(StandardCharsets.UTF_8));
        resp.getOutputStream().flush();
        resp.getOutputStream().close();
    }

    protected void doPost(HttpServletRequest reg, HttpServletResponse resp) throws IOException {
        resp.getOutputStream().write("Hello from HelloWorldServlet. Method: POST".getBytes(StandardCharsets.UTF_8));
        resp.getOutputStream().flush();
        resp.getOutputStream().close();
    }
}
