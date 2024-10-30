package controller.home;

import controller.authentication.BaseRBACController;
import controller.authentication.BaseRequiredAuthenticationController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.auth.User;
import java.io.IOException;


public class DashboardController extends BaseRequiredAuthenticationController {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        req.setAttribute("displayName", account.getUsername());
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);  }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
