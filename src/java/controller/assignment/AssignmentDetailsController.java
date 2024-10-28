/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.assignment;

import controller.authentication.BaseRBACController;
import dal.ProductionPlanDBContext;
import dal.ProductionPlanDetailDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.PlanDetail;
import model.ProductionPlan;
import model.auth.User;

/**
 *
 * @author Golden  Lightning
 */
public class AssignmentDetailsController extends BaseRBACController {
   
    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("id"));
        ProductionPlanDBContext planDb = new ProductionPlanDBContext();
        ProductionPlanDetailDBContext planDetailsDb = new ProductionPlanDetailDBContext();
        
        // Get production plan by ID
        ProductionPlan plan = planDb.get(planId);
        
        // Assuming we have a method to get details of a specific plan
        List<PlanDetail> planDetails = planDetailsDb.getDetailsByPlanId(planId);
        
        // Set plan and its details as attributes
        request.setAttribute("plan", plan);
        request.setAttribute("planDetails", planDetails);
        
        // Forward to JSP to display the plan details
        request.getRequestDispatcher("/view/assignment/assignment-details.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
