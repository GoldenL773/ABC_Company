/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.attendance;

import controller.authentication.BaseRBACController;
import dal.AttendanceDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import model.Attendance;
import model.auth.User;

/**
 *
 * @author Golden Lightning
 */
public class AttendanceUpdateServlet extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        AttendanceDBContext attendanceDB = new AttendanceDBContext();

        Enumeration<String> parameterNames = request.getParameterNames();

        // Process each attendance entry based on parameter names in the request
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            // Look for parameters starting with "actualQuantity_", which indicate attendance data entries
            if (paramName.startsWith("actualQuantity_")) {
                try {
                    // Extract the id from parameter name, e.g., "actualQuantity_1" -> tmpid = 1
                    int tmpid = Integer.parseInt(paramName.split("_")[1]);
                    
                    // Retrieve the corresponding WorkAssignment ID (waid) from the hidden input
                    int waid = Integer.parseInt(request.getParameter("waid_" + tmpid));
                    
                    // Retrieve other attendance details for this atid
                    int actualQuantity = Integer.parseInt(request.getParameter("actualQuantity_" + tmpid));
                    float alpha = Float.parseFloat(request.getParameter("alpha_" + tmpid));
                    String note = request.getParameter("note_" + tmpid);

                    // Upsert the attendance data
                    attendanceDB.upsertAttendance(waid, actualQuantity, alpha, note);

                } catch (NumberFormatException e) {
                    // Handle invalid number formats
                    e.printStackTrace();
                }
            }
        }

        // Redirect back to the attendance details page or display a success message
        String redirectDate = request.getParameter("date");
        String redirectDepartment = request.getParameter("department");

        response.sendRedirect(request.getContextPath() + "/attendance-management/details?date=" + redirectDate + "&department=" + redirectDepartment);
    
    }


@Override
protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    

}
