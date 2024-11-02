/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.report;

import dal.AttendanceDBContext;
import dal.DepartmentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Department;
import model.MonthlyWageRecord;

/**
 *
 * @author Golden Lightning
 */
public class SalaryReportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate currentDate = LocalDate.now();
        int departmentId = 0;
        int month = request.getParameter("month") != null ? Integer.parseInt(request.getParameter("month")) : currentDate.getMonthValue();
        int year = request.getParameter("year") != null ? Integer.parseInt(request.getParameter("year")) : currentDate.getYear();
        request.setAttribute("month", month);
        request.setAttribute("year", year);        
        
        DepartmentDBContext departmentDB = new DepartmentDBContext();
        request.setAttribute("departments", departmentDB.get("Production"));

        try {
            departmentId = Integer.parseInt(request.getParameter("department"));
        } catch (Exception e) {
            request.getRequestDispatcher("/view/report/salary-report.jsp").forward(request, response);
        }

        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        List<MonthlyWageRecord> monthlyWages = attendanceDB.getMonthlyAttendanceByDepartment(departmentId, month, year);

        request.setAttribute("monthlyWages", monthlyWages);
        request.setAttribute("departmentId", departmentId);
        
        request.getRequestDispatcher("/view/report/salary-report.jsp").forward(request, response);
    }

}
