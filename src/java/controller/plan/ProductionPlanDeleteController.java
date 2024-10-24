/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.plan;

import dal.ProductionPlanDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Golden  Lightning
 */
public class ProductionPlanDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
    
   
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String planIdStr = request.getParameter("plid");
    if (planIdStr != null) {
        int planId = Integer.parseInt(planIdStr);
        ProductionPlanDBContext dbContext = new ProductionPlanDBContext();
        dbContext.hidePlan(planId);
    }
    response.sendRedirect("list");
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
