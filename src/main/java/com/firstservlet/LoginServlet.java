package com.firstservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Divyansh"),
                @WebInitParam(name = "password", value = "Xy7@secure")
        }
)

public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Regex for username and password validation
        String nameRegex = "^[A-Z]{1}[a-z]{3,}$";
        String passRegex = "(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%]).{8,20}";

        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        // Check if username and password match regex
        if ((userID.equals(user) && password.equals(pwd)) &&
                Pattern.matches(nameRegex, userID) &&
                Pattern.matches(passRegex, password)) {

            req.setAttribute("user", user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color='red'>Either username or password is incorrect</font>");
            rd.include(req, resp);
        }
    }
}
