package api;

import com.google.gson.Gson;
import database.Database;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import json.ImageUploadResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 10 * 10)
public class ImageUploader extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            return;
        }
        String userEmail = request.getHeader("email");

        int userId = -1;
        try {
            ResultSet resultSet = Database.executeQuery("select user_id from user where email = \"" + userEmail + "\";");
            resultSet.next();
            userId = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String timeStamp = String.valueOf(System.currentTimeMillis());

        String fileName = timeStamp + ".jpg";

        String wholeFilePath = getServletContext().getRealPath("/images") + File.separator + fileName;

        List<Part> multiparts = (List<Part>) request.getParts();

        for (Part part : multiparts) {
            if (part.getName().equals("file")) {
                part.write(wholeFilePath);
            }
        }

        if (!new File(wholeFilePath).exists()) {
            return;
        }

        System.out.println("insert into uploads (user_id, time_stamp, path) values (" + userId + ", \"" + timeStamp + "\", \"/images/" + fileName + "\");");

        Database.executeRawQuery("insert into uploads (user_id, time_stamp, path) values (" + userId + ", \"" + timeStamp + "\", \"/images/" + fileName + "\");");
        Database.destroyDb();

        ImageUploadResponse uploadResponse = new ImageUploadResponse();
        uploadResponse.setUplaodStatus(true);
        uploadResponse.setImagePath("./images/" + fileName);

        response.getWriter().write(new Gson().toJson(uploadResponse, ImageUploadResponse.class));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
