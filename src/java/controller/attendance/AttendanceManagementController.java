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

        // Get current date
        LocalDate currentDate = LocalDate.now();
        int selectedMonth = req.getParameter("month") != null ? Integer.parseInt(req.getParameter("month")) : currentDate.getMonthValue();
        int selectedYear = req.getParameter("year") != null ? Integer.parseInt(req.getParameter("year")) : currentDate.getYear();

        req.setAttribute("selectedMonth", selectedMonth);
        req.setAttribute("selectedYear", selectedYear);

        req.getRequestDispatcher("/view/attendance/attendance-dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        doAuthorizedGet(req, resp, account);
    }
}
