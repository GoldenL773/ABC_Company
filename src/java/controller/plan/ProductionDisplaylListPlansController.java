/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.plan;

import controller.authentication.BaseRBACController;
import dal.DepartmentDBContext;
import dal.ProductionPlanDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Department;
import model.ProductionPlan;
import model.auth.User;

/**
 *
 * @author Golden Lightning
 */
public class ProductionDisplaylListPlansController extends BaseRBACController {


    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        // Retrieve filter parameters
        String name = request.getParameter("name");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String deptId = request.getParameter("deptId");
        ProductionPlanDBContext pldb = new ProductionPlanDBContext();
        DepartmentDBContext dbDept = new DepartmentDBContext();

        // Get list of departments for the dropdown
        List<Department> departments = dbDept.get("Production"); // Adjust this to your actual method for retrieving departments

        // Call a method to get the filtered list of plans
        List<ProductionPlan> plans = pldb.getPlans(name, month, year, deptId);

        // Set attributes for plans and departments, and forward to the JSP
        request.setAttribute("plans", plans);
        request.setAttribute("depts", departments);
        request.getRequestDispatcher("../view/productionplan/list.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account) 
            throws ServletException, IOException {
  
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
