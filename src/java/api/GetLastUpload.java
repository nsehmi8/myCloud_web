package api;

import com.google.gson.Gson;
import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import json.ImageData;

public class GetLastUpload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String userEmail = request.getParameter("email");

        int userId = -1;
        try {
            ResultSet resultSet = Database.executeQuery("select user_id from user where email = \"" + userEmail + "\";");
            resultSet.next();
            userId = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        ImageData data = null;
        try {
            ResultSet set = Database.executeQuery("select upload_id, time_stamp, path from uploads where user_id = " + userId + " order by upload_id desc limit 1");
            set.next();
            data = new ImageData();
            data.setImageId(set.getInt(1));
            data.setTimestamp(Long.parseLong(set.getString(2)));
            data.setImagePath(set.getString(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(new Gson().toJson(data));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
