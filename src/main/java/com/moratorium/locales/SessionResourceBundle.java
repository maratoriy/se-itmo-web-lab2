package com.moratorium.locales;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SessionResourceBundle extends ResourceBundle {

    private static final String ATTRIBUTE = "bundle";
    private static final String BASE_NAME = "gui";

    private SessionResourceBundle(Locale locale) {
        setLocale(locale);
    }

    @Override
    public Locale getLocale() {
        return parent.getLocale();
    }

    public static void setFor(HttpServletRequest request, Locale locale) {
        if (request.getSession().getAttribute(ATTRIBUTE) == null) {
            request.getSession().setAttribute(ATTRIBUTE, new SessionResourceBundle(locale));
        }
    }

    public static SessionResourceBundle getCurrentInstance(HttpServletRequest request) {
        return (SessionResourceBundle) request.getSession().getAttribute(ATTRIBUTE);
    }

    public void setLocale(Locale locale) {
        setParent(getBundle(BASE_NAME, locale, new UTF8Control()));
    }

    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }

    static public class UTF8Control extends ResourceBundle.Control {
        public ResourceBundle newBundle
                (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IOException {
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }
}
