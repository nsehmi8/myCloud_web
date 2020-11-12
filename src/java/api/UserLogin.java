package api;

import com.google.gson.Gson;
import database.Database;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import json.UserLoginSignupResponse;
import util.Constants;

public class UserLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter(Constants.USER_EMAIL);
        String password = request.getParameter(Constants.USER_PASSWORD);

        UserLoginSignupResponse res = new UserLoginSignupResponse();
        
        try {
            if (Database.executeQuery("select * from user where email = \"" + email + "\" and password = \"" + password + "\";").next())
                res.setResponseCode("true");
            else
                res.setResponseCode("false");
            Database.destroyDb();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter pw = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        pw.write(new Gson().toJson(res));
        pw.flush();
        pw.close();
    }
}
