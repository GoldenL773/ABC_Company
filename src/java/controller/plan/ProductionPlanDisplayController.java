package controller.plan;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

/**
 *
 * @author Golden Lightning
 */
public class ProductionPlanDisplayController extends HttpServlet {

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
            out.println("<title>Servlet ProductionPlanDisplayController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductionPlanDisplayController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
