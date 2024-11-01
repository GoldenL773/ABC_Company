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
        int departmentId = Integer.parseInt(request.getParameter("department"));
        int month = Integer.parseInt(request.getParameter("month"));
        int year = Integer.parseInt(request.getParameter("year"));
        DepartmentDBContext departmentDB = new DepartmentDBContext();
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        List<MonthlyWageRecord> monthlyWages = attendanceDB.getMonthlyAttendanceByDepartment(departmentId, month, year);

         request.setAttribute("departments", departmentDB.get("Production"));
        request.setAttribute("monthlyWages", monthlyWages);
        request.setAttribute("departmentId", departmentId);
        request.setAttribute("month", month);
        request.setAttribute("year", year);

        request.getRequestDispatcher("/view/report/salary-report.jsp").forward(request, response);
    }

}
