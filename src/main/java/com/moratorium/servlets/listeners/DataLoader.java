package com.moratorium.servlets.listeners;

import com.moratorium.data.ResultContainer;
import com.moratorium.locales.ApplicationLanguagesContainer;
import com.moratorium.servlets.ChangeLocaleServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;


public class DataLoader implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("results", new ResultContainer());
        context.log("ResultsContainer initialized");
        ApplicationLanguagesContainer.setFor(context);
        context.log("Application will run with available languages: "+ApplicationLanguagesContainer.getCurrentInstance(context).toString());
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
