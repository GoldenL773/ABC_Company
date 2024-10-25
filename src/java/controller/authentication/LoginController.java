package controller.authentication;

import controller.BaseRBACController;
import model.auth.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dal.UserDBContext;
import jakarta.servlet.http.HttpServlet;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param_user = req.getParameter("username");
        String param_pass = req.getParameter("password");

        UserDBContext userDB = new UserDBContext();
        User user = userDB.get(param_user, param_pass);

        if (user != null) {
            // Load the roles and features into the user object.
            user.setRoles(userDB.getRoles(user.getUsername()));

            // Set the user into the session.
            req.getSession().setAttribute("account", user);

            // Redirect to the dashboard (or any other authorized page).
            resp.sendRedirect("dashboard");
        } else {
            req.setAttribute("errorMessage", "Invalid username or password!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }
    
    
    
}
