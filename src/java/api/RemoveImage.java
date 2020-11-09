package api;

import com.google.gson.Gson;
import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import json.ImageRemovalResponse;

public class RemoveImage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        String id = request.getParameter("image_id");
        
        ImageRemovalResponse irr = new ImageRemovalResponse();
        irr.setRemoved(Database.executeRawQuery("delete from uploads where upload_id = " + id));
        
        String jsonResponse = new Gson().toJson(irr);
        
        PrintWriter pw = response.getWriter();
        pw.write(jsonResponse);
        pw.flush();
        pw.close();
    }
}
