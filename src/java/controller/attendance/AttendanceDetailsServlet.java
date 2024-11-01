package controller.attendance;

import controller.authentication.BaseRBACController;
import dal.AttendanceDBContext;
import dal.DepartmentDBContext;
import model.Attendance;
import model.Department;
import model.auth.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;


public class AttendanceDetailsServlet extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        // No post operation required
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        String dateParam = req.getParameter("date");
        String departmentIdParam = req.getParameter("department");
        Date date = Date.valueOf(dateParam);
        req.setAttribute("date", date);

        DepartmentDBContext departmentDB = new DepartmentDBContext();
        AttendanceDBContext attendanceDB = new AttendanceDBContext();

        // If no department selected, show message and reload the form
        if (departmentIdParam == null || departmentIdParam.isEmpty() || dateParam == null || dateParam.isEmpty()) {
            req.setAttribute("message", "Please select a department and date to view attendance details.");
            req.setAttribute("departments", departmentDB.get("Production"));
            req.getRequestDispatcher("/view/attendance/attendance-details.jsp").forward(req, resp);
            return;
        }

        int departmentId = Integer.parseInt(departmentIdParam);
        

        // Fetch department details and attendance records
        Department department = departmentDB.get(departmentId);
        List<Attendance> attendances = attendanceDB.getAttendanceByDateAndDepartment(date, departmentId);

        int totalEmployees = (int) attendances.stream().map(attendance -> attendance.getWorkAssignment().getEmployee().getId()).distinct().count();
        int totalCompletedOutput = attendances.stream().mapToInt(Attendance::getActualQuantity).sum();

        req.setAttribute("department", department);
        req.setAttribute("attendances", attendances);
        req.setAttribute("totalEmployees", totalEmployees);
        req.setAttribute("totalCompletedOutput", totalCompletedOutput);
        req.setAttribute("departments", departmentDB.get("Production"));
        req.getRequestDispatcher("/view/attendance/attendance-details.jsp").forward(req, resp);
    }
}
