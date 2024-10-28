/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.assignment;

import controller.authentication.BaseRBACController;
import dal.EmployeeDBContext;
import dal.ProductionPlanDBContext;
import dal.ProductionPlanDetailDBContext;
import dal.WorkAssignmentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Employee;
import model.PlanDetail;
import model.ProductionPlan;
import model.WorkAssignment;
import model.auth.User;

/**
 *
 * @author Golden Lightning
 */
public class AssignmentEmployeeController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        int detailId = Integer.parseInt(request.getParameter("detailId"));
        
        int deptId = Integer.parseInt(request.getParameter("deptid"));
        ProductionPlanDetailDBContext planDetailsDb = new ProductionPlanDetailDBContext();
        EmployeeDBContext empDb = new EmployeeDBContext();
        WorkAssignmentDBContext workDb = new WorkAssignmentDBContext();
      
        PlanDetail planDetail = planDetailsDb.get(detailId);
        // Get available employees for assignment
        List<Employee> availableEmployees = empDb.getAvailableEmployees(planDetail.getDate(), planDetail.getSid(), deptId);
        // Get assigned employees for this detail
        List<WorkAssignment> assignedEmployees = workDb.getAssignedEmployeesByDetailId(detailId);

        // Set attributes for JSP
        request.setAttribute("deptID", deptId);
        request.setAttribute("planDetail", planDetail);
        request.setAttribute("availableEmployees", availableEmployees);
        request.setAttribute("assignedEmployees", assignedEmployees); // Added this line

        // Forward to JSP for displaying assignment details
        request.getRequestDispatcher("/view/assignment/assignment-employee.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account) 
            throws ServletException, IOException {
        int detailId = Integer.parseInt(request.getParameter("detailId"));
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int deptID = Integer.parseInt(request.getParameter("deptid"));
        String note = request.getParameter("note");

        // Create a new WorkAssignment object
        WorkAssignment assignment = new WorkAssignment();
        assignment.setDetailId(detailId);
        assignment.setEmployeeId(employeeId);
        assignment.setQuantity(quantity);
        assignment.setNote(note);

        // Insert the work assignment into the database
        WorkAssignmentDBContext workDb = new WorkAssignmentDBContext();
        workDb.insert(assignment);

        // Redirect back to the assignment details page with a success message
        response.sendRedirect("assign?detailId=" + detailId+"&deptid="+deptID);
    }
}
