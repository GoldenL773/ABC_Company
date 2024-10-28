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
import java.util.ArrayList;
import model.Employee;
import model.auth.User;

/**
 *
 * @author Golden  Lightning
 */
public class EmployeeViewListController extends BaseRBACController {
   
   
    
    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        EmployeeDBContext db = new EmployeeDBContext();
        String workshopIdParam = request.getParameter("workshopId");
        int workshopId = Integer.parseInt(workshopIdParam);
        
        ArrayList<Employee> employees = db.listByDepartment(workshopId);
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/view/employee/view-available.jsp").forward(request, response);
    }
    
    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account) 
            throws ServletException, IOException {
        
    }

   
}
