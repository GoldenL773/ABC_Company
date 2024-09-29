/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.dog.register;

import entity.Dog;
import entity.Skill;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sonnt-local hand-some
 */
public class DogRegisterSkillController extends HttpServlet {
   
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Dog dog = (Dog) request.getSession().getAttribute("dog");
        if (dog == null) {
            response.sendRedirect("profile");
        }
        else
        {
            //add request dog to request scope
            request.getRequestDispatcher("../../view/dog/register_skill.jsp").forward(request, response);
        }
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
        Dog dog = (Dog) request.getSession().getAttribute("dog");
        if (dog == null) {
            response.sendRedirect("profile");
        }
        
        String action = request.getParameter("action");
        if(action.equals("profile"))
        {
            response.sendRedirect("profile");
        }
        else if(action.equals("skill"))
        {
            //read param
            String name = request.getParameter("name");
            String level = request.getParameter("level");
            Skill skill = new Skill();
            skill.setName(name);
            skill.setLevel(level);
            dog.getSkills().add(skill);
            request.getSession().setAttribute("dog", dog);
            response.sendRedirect("skill");
        }
        else
        {
            //save to database (session.dog)
            request.getSession().setAttribute("dog", null);
            //code display data here
            response.getWriter().println("dog created!");
        }
        
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
