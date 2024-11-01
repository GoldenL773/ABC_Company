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

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            if (paramName.startsWith("actualQuantity_")) {
                int waid = Integer.parseInt(paramName.split("_")[1]);

                int actualQuantity = Integer.parseInt(request.getParameter("actualQuantity_" + waid));
                float alpha = Float.parseFloat(request.getParameter("alpha_" + waid));
                String note = request.getParameter("note_" + waid);

                attendanceDB.upsertAttendance(waid, actualQuantity, alpha, note);
            }
        }

        String redirectDate = request.getParameter("date");
        String redirectDepartment = request.getParameter("department");

        response.sendRedirect(request.getContextPath() + "/attendance-management/details?date=" + redirectDate + "&department=" + redirectDepartment);
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
