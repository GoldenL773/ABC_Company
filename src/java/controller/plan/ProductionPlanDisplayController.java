package controller.plan;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import controller.authentication.BaseRBACController;
import dal.ProductDBContext;
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
 * @author Golden Lightning
 */
public class ProductionPlanDisplayController extends BaseRBACController {

 
    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("plid"));
        ProductionPlanDBContext plandb = new ProductionPlanDBContext();
        ProductionPlanDetailDBContext pdetaildb = new ProductionPlanDetailDBContext();
        
        // Retrieve the plan using the existing method
        ProductionPlan plan = plandb.get(planId);

        // Retrieve detailed production plan details for the given plan ID
        List<PlanDetail> details = pdetaildb.getDetailsByPlanId(planId);

        // Add the retrieved data to the request scope to be accessible in the JSP
        request.setAttribute("plan", plan);
        request.setAttribute("details", details);

        // Forward the request to the JSP page for rendering the plan details
        request.getRequestDispatcher("/view/productionplan/listDetails.jsp").forward(request, response);
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
