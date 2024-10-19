/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 *
 * @author sonng
 */

public class StudentCreateController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Date dob = Date.valueOf(req.getParameter("dob"));
        boolean gender = req.getParameter("gender").equals("male");
        
        Student student = new Student(id, name, gender, dob);
        
        resp.getWriter().println("Id: " + student.getId());
        resp.getWriter().println("Name: " + student.getName());
        resp.getWriter().println("Dob: "+ student.getDob());
        resp.getWriter().println("gender :" + (student.isGender()?"male":"female"));
    
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //code logic here
        
        req.getRequestDispatcher("../index.html").forward(req, resp);
    }
    
}
