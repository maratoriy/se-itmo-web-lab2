package com.moratorium.servlets.listeners;

import com.moratorium.data.ResultContainer;
import com.moratorium.locales.ApplicationLanguagesContainer;
import com.moratorium.servlets.ChangeLocaleServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;


@WebListener
public class DataLoader implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("results", new ResultContainer());
        ApplicationLanguagesContainer.setFor(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
