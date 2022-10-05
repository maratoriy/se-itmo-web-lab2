package com.moratorium.servlets.filters;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "UrlFilter")
public class UrlFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req,
                         HttpServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        if (req.getRequestURI().matches(".*(css|ico|js|woff)")) {
            chain.doFilter(req, resp);
            return;
        }

        if (!Objects.equals(req.getHttpServletMapping().getServletName(), "ControllerServlet")) {
            if(req.getHttpServletMapping().getMatchValue().isEmpty()) {
                displayErrorPage(req, resp, HttpServletResponse.SC_NOT_FOUND);//, "Requested resource is not found");
            } else {
                displayErrorPage(req, resp, HttpServletResponse.SC_FORBIDDEN);//, "Access to the requested resource is forbidden");
            }
            return;
        }

        try {
            chain.doFilter(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            displayErrorPage(req, resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//, "An error occurred on the server");
        }
    }

    public void displayErrorPage(HttpServletRequest req,
                                 HttpServletResponse resp,
                                 int error) throws ServletException, IOException {
        resp.setStatus(error);
        //req.setAttribute("message", message);
        req.setAttribute("error", error);
        req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
    }

}