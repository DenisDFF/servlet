package org.example;


import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timezoneParam = request.getParameter("timezone");

        TimeZone timeZone;
        if (timezoneParam != null && !timezoneParam.isEmpty()) {
            timeZone = TimeZone.getTimeZone(timezoneParam);
        } else {
            timeZone = TimeZone.getDefault();
        }

        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dataFormat.setTimeZone(timeZone);

        String currentTime = dataFormat.format(new Date());

        response.setContentType("text/html");

        String htmlResponse = "<html><body><h2>Поточний час (" + timeZone.getDisplayName() + "): " + currentTime + "</h2></body></html>";

        response.getWriter().write(htmlResponse);
    }
}
