package com.moratorium.locales;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Stream;

public class ApplicationLanguagesContainer extends ArrayList<Locale> {

    private static final String ATTRIBUTE = "languages";
    public static final Locale DEFAULT_LANGUAGE = Locale.US;

    public ApplicationLanguagesContainer(String[] localeNames) {
        super(Stream.of(localeNames).collect(
                ArrayList::new,
                (list, item) -> list.add(Locale.forLanguageTag(item)),
                ArrayList::addAll
        ));
    }

    public ApplicationLanguagesContainer() {
        super(Collections.singletonList(DEFAULT_LANGUAGE));
    }

    public static void setFor(ServletContext servletContext) {
        if(servletContext.getAttribute(ATTRIBUTE) == null) {
            String initLanguages = servletContext.getInitParameter(ATTRIBUTE);
            if(Objects.nonNull(initLanguages)) {
                servletContext.setAttribute(ATTRIBUTE, new ApplicationLanguagesContainer(initLanguages.split(" ")));
            } else {
                servletContext.setAttribute(ATTRIBUTE, new ApplicationLanguagesContainer());
            }
        }
    }

    public static ApplicationLanguagesContainer getCurrentInstance(HttpServletRequest request) {
        return (ApplicationLanguagesContainer) request.getServletContext().getAttribute(ATTRIBUTE);
    }


}
