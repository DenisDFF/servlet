package org.example;
import java.io.IOException;
import java.util.TimeZone;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(value = "/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String timezoneParam = request.getParameter("timezone");

        if (timezoneParam == null || timezoneParam.isEmpty()) {
            chain.doFilter(request, response);
        } else if (isValidTimezone(timezoneParam)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(400);
            response.getWriter().write("Invalid timezone");
        }
    }

    private boolean isValidTimezone(String timezone) {
        try {
            TimeZone.getTimeZone(timezone);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
