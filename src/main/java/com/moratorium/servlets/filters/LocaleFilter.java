package com.moratorium.servlets.filters;

import com.moratorium.locales.ApplicationLanguagesContainer;
import com.moratorium.locales.SessionResourceBundle;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        ApplicationLanguagesContainer locales = ApplicationLanguagesContainer.getCurrentInstance(req);
        SessionResourceBundle.setFor(req,
                (locales.contains(req.getLocale()) ? req.getLocale()
                                                   : locales.stream().findAny().orElse(ApplicationLanguagesContainer.DEFAULT_LANGUAGE)));
        chain.doFilter(req, resp);
    }
}
