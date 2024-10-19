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
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author sonng
 */

public class StudentLoadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String indexes = req.getParameter("indexes");
        String[] ids = indexes.split(",");
        ArrayList<Student> students = new ArrayList<>();
        for (String id : ids) {
            int sid = Integer.parseInt(req.getParameter("id" + id));
            String name = req.getParameter("name" + id);
            Date dob = Date.valueOf(req.getParameter("dob" + id));
            boolean gender = req.getParameter("gender" + id).equals("male");
            Student student = new Student(sid, name, gender, dob);
            students.add(student);
        }

        for (Student student : students) {
            resp.getWriter().println("Id: " + student.getId());
            resp.getWriter().println("Name: " + student.getName());
            resp.getWriter().println("Dob: " + student.getDob());
            resp.getWriter().println("gender :" + (student.isGender() ? "male" : "female"));
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
