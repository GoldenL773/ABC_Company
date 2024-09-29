/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package view;

import entity.Rect;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author sonnt-local
 */
public class GenerateRectView extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ArrayList<Rect> rects = (ArrayList<Rect>)request.getAttribute("rects");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RectGenerateController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<canvas id=\"myCanvas\" width=\"500\" height=\"500\" style=\"border:1px solid grey\"></canvas>");
            out.println("<script>");
            out.println("const c = document.getElementById(\"myCanvas\");");
            out.println("const ctx = c.getContext(\"2d\");");
            for (Rect rect : rects) {
                out.println("ctx.fillStyle = \"rgb("+rect.getRed()+","+rect.getGreen()+","+rect.getBlue()+")\";");
                out.println("ctx.fillRect("+rect.getX()+", "+rect.getY()+", "+rect.getW()+", "+rect.getH()+");");
                out.println("ctx.beginPath();");
                out.println("ctx.rect("+rect.getX()+", "+rect.getY()+", "+rect.getW()+", "+rect.getH()+");");
                out.println("ctx.stroke();");
            }
            out.println("</script>");

            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(req, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
