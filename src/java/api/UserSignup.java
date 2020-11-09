package api;

import com.google.gson.Gson;
import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import json.UserLoginSignupResponse;

public class UserSignup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Database.executeRawQuery("insert into user (email, password) values(\"" + email + "\", \"" + password + "\");");
        Database.destroyDb();
        
        UserLoginSignupResponse signupResponse = new UserLoginSignupResponse();
        signupResponse.setResponseCode("true");
        
        String toJson = new Gson().toJson(signupResponse);

        response.getWriter().write(toJson);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
