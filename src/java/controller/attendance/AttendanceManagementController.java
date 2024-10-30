package controller.attendance;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import controller.authentication.BaseRBACController;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import model.auth.User;

public class AttendanceManagementController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {

        
        

        req.getRequestDispatcher("/view/attendance/attendance-dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        doAuthorizedGet(req, resp, account);
    }
}
