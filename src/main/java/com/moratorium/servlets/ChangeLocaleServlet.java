package com.moratorium.servlets;


import com.moratorium.locales.ApplicationLanguagesContainer;
import com.moratorium.locales.SessionResourceBundle;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Objects;

@WebServlet(name = "ChangeLocaleServlet", value = "/locale/change")
public class ChangeLocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter("language");
        String country = req.getParameter("country");
        if (Objects.nonNull(language) && Objects.nonNull(country)) {
            ApplicationLanguagesContainer locales = ApplicationLanguagesContainer.getCurrentInstance(req);
            SessionResourceBundle currentBundle = SessionResourceBundle.getCurrentInstance(req);
            Locale locale = new Locale(language, country);
            if (locales.contains(locale)) {
                log(String.format("Changing locale to %s from %s", locale, currentBundle.getLocale()));
                currentBundle.setLocale(locale);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                log(String.format("Wrong locale to change: %s", locale));
                resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
