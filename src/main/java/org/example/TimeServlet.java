package org.example;


import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timezoneParam = request.getParameter("timezone");
        timezoneParam = timezoneParam.replaceAll(" ", "+");
        TimeZone timeZone;

        System.out.println("TimeZone: " + timezoneParam);

        if (timezoneParam != null && !timezoneParam.isEmpty()) {
            timeZone = TimeZone.getTimeZone(timezoneParam);
        } else {
            timeZone = TimeZone.getTimeZone("UTC");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(timeZone);
        String currentTime = dateFormat.format(new Date());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>[" + timezoneParam + abc +"]<h2>Поточний час (" + timeZone.getID() + "): " + currentTime + "</h2></body></html>");
    }
}
