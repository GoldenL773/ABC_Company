package controller.attendance;

import controller.authentication.BaseRBACController;
import dal.AttendanceDBContext;
import dal.DepartmentDBContext;
import model.Attendance;
import model.Department;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import model.auth.User;

public class AttendanceDetailsServlet extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        String dateParam = req.getParameter("date");

        String departmentIdParam = req.getParameter("department");

        DepartmentDBContext departmentDB = new DepartmentDBContext();
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        Date date = Date.valueOf(dateParam);
        req.setAttribute("date", date);

        // Check if date or department parameters are missing
        if (departmentIdParam.isEmpty()) {
            // Pass empty data and a message to prompt the user to select a department and date
            req.setAttribute("message", "Please select a department and date to view attendance details.");
            req.setAttribute("attendances", null);
            req.setAttribute("departments", departmentDB.get("Production"));
            req.getRequestDispatcher("/view/attendance/attendance-details.jsp").forward(req, resp);
            return;
        }

        // Convert date and department ID
        int departmentId = Integer.parseInt(departmentIdParam);

        // Fetch department details and attendance records
        Department department = departmentDB.get(departmentId);
        List<Attendance> attendances = attendanceDB.getAttendanceByDateAndDepartment(date, departmentId);

        int totalEmployees = attendances.size();
        int totalCompletedOutput = attendances.stream().mapToInt(Attendance::getActualQuantity).sum();

        req.setAttribute("department", department);
        req.setAttribute("attendances", attendances);
        req.setAttribute("totalEmployees", totalEmployees);
        req.setAttribute("totalCompletedOutput", totalCompletedOutput);
        req.setAttribute("departments", departmentDB.get("Production"));
        req.getRequestDispatcher("/view/attendance/attendance-details.jsp").forward(req, resp);
    }
}
