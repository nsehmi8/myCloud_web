package api;

import com.google.gson.Gson;
import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import json.ImageData;
import json.RecentUploads;
import util.Constants;

public class GetRecents extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String currentUser = request.getParameter(Constants.USER_EMAIL);
        int currentUserId = -1;

        try {
            ResultSet set = Database.executeQuery("select user_id from user where email = \"" + currentUser + "\";");
            set.next();
            currentUserId = set.getInt(1);
            Database.destroyDb();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        ArrayList<ImageData> list = new ArrayList<>();
        
        try {
            ResultSet query = Database.executeQuery("select upload_id, time_stamp, path from uploads inner join user on user.user_id = uploads.user_id where user.user_id = " + currentUserId);
            while (query.next()) {
                int uploadId = query.getInt(1);
                long timestamp = Long.parseLong(query.getString(2));
                String imagePath = query.getString(3);
                
                ImageData data = new ImageData();
                data.setImageId(uploadId);
                data.setTimestamp(timestamp);
                data.setImagePath(imagePath);
                list.add(data);
            }
            Database.destroyDb();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        ImageData[] arr = new ImageData[list.size()];
        int index = 0;
        for (ImageData data : list) {
            arr[index] = list.get(index);
            index++;
        }
        
        RecentUploads uploads = new RecentUploads();
        uploads.setTotalImages(arr.length);
        uploads.setImages(arr);
        
        String jsonRecents = new Gson().toJson(uploads);
        System.out.println(jsonRecents);
    }
}
