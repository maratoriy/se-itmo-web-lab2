package com.moratorium.servlets;

import com.moratorium.data.Coordinates;
import com.moratorium.data.Result;
import com.moratorium.data.ResultContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@WebServlet(name = "AreaCheckServlet", value = "/check")
public class AreaCheckServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.nanoTime();
        ResultContainer resultList = (ResultContainer) req.getServletContext().getAttribute("results");

        Coordinates coordinates = null;
        Result.Type result = Result.Type.NONE;
        try {
            int r = Integer.parseInt(req.getParameter("r"));
            double x = Double.parseDouble(req.getParameter("x"));
            double y = Double.parseDouble(req.getParameter("y"));
            boolean dot = Boolean.parseBoolean(req.getParameter("dot"));

            log(String.format("Check dot (%s,%s,%s,%s)", x, y, r, dot));
            coordinates = new Coordinates(x, y, r);
            if (validate(x, y, r, dot)) {
                boolean inArea = inArea(x, y, r);
                result = inArea ? Result.Type.BINGO : Result.Type.FAIL;
            } else {
                result = Result.Type.FAILED_VALIDATING;
            }
        } catch (NumberFormatException e) {
            result = Result.Type.WRONG_FORMAT;
        } finally {
            resultList.add(new Result(
                    coordinates,
                    new Date(),
                    (double) (System.nanoTime() - startTime) / 1000000,
                    result
            ));
            req.getRequestDispatcher("/jsp/table.jsp").forward(req, resp);
        }
    }

    public static final Set<Integer> availableR = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

    public static boolean validate(double x, double y, int r, boolean dot) {
        return (dot||(x >= -5 && x <= 5)
                && (y >= -5 && y <= 5))
                && availableR.contains(r);

    }

    public static boolean inArea(double x, double y, double r) {
        return (x <= 0 && y <= 0 && y >= -1D / 2 * (x + r))
                || (x >= 0 && y >= 0 && (x * x + y * y <= (r * r / 4)))
                || (x <= 0 && y >= 0 && x >= -r / 2 && y <= r);
    }


}
