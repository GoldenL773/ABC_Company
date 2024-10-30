package controller.attendance;

import dal.AttendanceDBContext;
import dal.DepartmentDBContext;
import model.Attendance;
import model.Department;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public class AttendanceDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateParam = request.getParameter("date");
       
        String departmentIdParam = request.getParameter("department");

        DepartmentDBContext departmentDB = new DepartmentDBContext();
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
                Date date = Date.valueOf(dateParam);
                  request.setAttribute("date", date);

        // Check if date or department parameters are missing
        if (departmentIdParam.isEmpty()) {
            // Pass empty data and a message to prompt the user to select a department and date
            request.setAttribute("message", "Please select a department and date to view attendance details.");
            request.setAttribute("attendances", null);
            request.setAttribute("departments", departmentDB.get("Production"));
            request.getRequestDispatcher("/view/attendance/attendance-details.jsp").forward(request, response);
            return;
        }

        // Convert date and department ID
        int departmentId = Integer.parseInt(departmentIdParam);

        // Fetch department details and attendance records
        Department department = departmentDB.get(departmentId);
        List<Attendance> attendances = attendanceDB.getAttendanceByDateAndDepartment(date, departmentId);

        int totalEmployees = attendances.size();
        int totalCompletedOutput = attendances.stream().mapToInt(Attendance::getActualQuantity).sum();

     
        
        request.setAttribute("department", department);
        request.setAttribute("attendances", attendances);
        request.setAttribute("totalEmployees", totalEmployees);
        request.setAttribute("totalCompletedOutput", totalCompletedOutput);
        request.setAttribute("departments", departmentDB.get("Production"));
        request.getRequestDispatcher("/view/attendance/attendance-details.jsp").forward(request, response);
    }
}
