package controller.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.auth.User;
import dal.UserDBContext;
import java.io.IOException;

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
        // Get user directly with plaintext password matching
        User user = userDB.get(param_user, param_pass);  // This method now expects a plaintext password

        if (user != null) {
            // Load the roles and features into the user object
            user.setRoles(userDB.getRoles(user.getUsername()));
            // Set the user into the session
            req.getSession().setAttribute("account", user);
            resp.sendRedirect("dashboard");
        } else {
            req.setAttribute("errorMessage", "Invalid username or password!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
