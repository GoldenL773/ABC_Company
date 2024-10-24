/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.plan;

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

/**
 *
 * @author Golden Lightning
 */
public class ProductionDisplaylListPlanController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductionPlanController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductionPlanController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
