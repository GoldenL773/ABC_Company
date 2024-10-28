/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.employee;

import controller.authentication.BaseRBACController;
import dal.EmployeeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import model.Employee;
import model.auth.User;

/**
 *
 * @author Golden  Lightning
 */
public class EmployeeAddController extends BaseRBACController {
   
   @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        request.getRequestDispatcher("/view/employee/employee-add.jsp").forward(request, response);
    }

    @Override
   protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account) 
            throws ServletException, IOException {
        String name = request.getParameter("name");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        Date dob = Date.valueOf(request.getParameter("dob"));
        int salaryId = Integer.parseInt(request.getParameter("salaryId"));

        Employee employee = new Employee();
        employee.setName(name);
        employee.setDepartmentId(departmentId);
        employee.setPhoneNumber(phoneNumber);
        employee.setAddress(address);
        employee.setDob(dob);
        employee.setSalaryId(salaryId);

        EmployeeDBContext db = new EmployeeDBContext();
        db.insert(employee);
        
        response.sendRedirect("../employee-management/view-available?workshopId=" + departmentId);
    }

}
