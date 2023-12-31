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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timezoneParam = request.getParameter("timezone");
        TimeZone timeZone;

        if (timezoneParam != null && !timezoneParam.isEmpty()) {
            timezoneParam = timezoneParam.replaceAll(" ", "+").replaceAll("UTC", "GMT");
            timeZone = TimeZone.getTimeZone(timezoneParam);
        } else {
            timeZone = TimeZone.getTimeZone("GMT");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(timeZone);
        String currentTime = dateFormat.format(new Date());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (timeZone != null) {
            out.println("<html><body><h2>Поточний час (" + timeZone.getID().replaceAll("GMT", "UTC") + "): " + currentTime.replaceAll("GMT", "UTC") + "</h2></body></html>");
        } else {
            out.println("<html><body><h2>Поточний час (" + timeZone.getID() + "): " + currentTime + "</h2></body></html>");
        }
        out.println("<meta http-equiv=\"refresh\" content=\"1\">");
    }
}
