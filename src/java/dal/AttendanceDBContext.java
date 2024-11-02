package dal;

import model.Attendance;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Employee;
import model.MonthlyWageRecord;
import model.Product;
import model.WorkAssignment;

public class AttendanceDBContext extends DBContext<Attendance> {

    public List<MonthlyWageRecord> getMonthlyAttendanceByDepartment(int departmentId, int month, int year) {
        List<MonthlyWageRecord> monthlyWages = new ArrayList<>();
        String sql = "SELECT \n"
                + "        e.eid AS employeeId, \n"
                + "        e.ename AS employeeName,\n"
                + "        s.salary AS hourlyRate,\n"
                + "        pd.date AS attendanceDate,\n"
                + "        AVG(a.alpha) AS alphaFactor,\n"
                + "        SUM(8 * s.salary * a.alpha) AS dailyWage\n"
                + "    FROM Employees e\n"
                + "    LEFT JOIN WorkAssignments w ON e.eid = w.eid\n"
                + "    LEFT JOIN Attendances a ON w.waid = a.waid\n"
                + "    LEFT JOIN PlanDetails pd ON w.pdid = pd.pdid\n"
                + "    LEFT JOIN Salaries s ON e.sid = s.sid\n"
                + "    WHERE e.did = ? \n"
                + "      AND MONTH(pd.date) = ? \n"
                + "      AND YEAR(pd.date) = ?\n"
                + "    GROUP BY e.eid, e.ename, s.salary, pd.date\n"
                + "    ORDER BY e.eid, pd.date;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            ps.setInt(2, month);
            ps.setInt(3, year);

            ResultSet rs = ps.executeQuery();
            Map<Integer, MonthlyWageRecord> recordsMap = new HashMap<>();

            while (rs.next()) {
                int employeeId = rs.getInt("employeeId");
                MonthlyWageRecord record = recordsMap.getOrDefault(employeeId, new MonthlyWageRecord(employeeId, rs.getString("employeeName"), rs.getFloat("hourlyRate")));

                Date date = rs.getDate("attendanceDate");
                float alphaFactor = rs.getFloat("alphaFactor");
                record.addDailyEffort(date, alphaFactor);

                recordsMap.put(employeeId, record);
            }

            monthlyWages.addAll(recordsMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyWages;
    }

    public void upsertAttendance(int waid, int actualQuantity, float alpha, String note) {
        String checkSql = "SELECT atid FROM Attendances WHERE waid = ?";
        String insertSql = "INSERT INTO Attendances (waid, actualquantity, alpha, note) VALUES (?, ?, ?, ?)";
        String updateSql = "UPDATE Attendances SET actualquantity = ?, alpha = ?, note = ? WHERE atid = ?";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setInt(1, waid);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Attendance record exists, update it
                int atid = rs.getInt("atid");
                try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, actualQuantity);
                    updateStmt.setFloat(2, alpha);
                    updateStmt.setString(3, note);
                    updateStmt.setInt(4, atid);
                    updateStmt.executeUpdate();
                }
            } else {
                // No attendance record exists, insert a new one
                try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, waid);
                    insertStmt.setInt(2, actualQuantity);
                    insertStmt.setFloat(3, alpha);
                    insertStmt.setString(4, note);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Ideally log this
        }
    }

    public List<Attendance> getAttendanceByDateAndDepartment(Date date, int departmentId) {
        List<Attendance> attendances = new ArrayList<>();
        String sql = "SELECT "
                + "    wa.waid AS workAssignmentId, "
                + "    wa.quantity AS orderedQuantity, "
                + "    e.eid AS employeeId, "
                + "    e.ename AS employeeName, "
                + "    p.pid AS productId, "
                + "    p.pname AS productName, "
                + "    a.actualquantity AS actualQuantity, "
                + "    a.alpha AS alpha, "
                + "    a.note AS attendanceNote "
                + "FROM WorkAssignments wa "
                + "LEFT JOIN Employees e ON wa.eid = e.eid "
                + "LEFT JOIN PlanDetails pd ON wa.pdid = pd.pdid "
                + "LEFT JOIN PlanHeaders ph ON pd.phid = ph.phid "
                + "LEFT JOIN Products p ON ph.pid = p.pid "
                + "LEFT JOIN Attendances a ON a.waid = wa.waid "
                + "WHERE e.did = ? AND pd.date = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            ps.setDate(2, date);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setAtid(rs.getInt("workAssignmentId")); // Assign work assignment ID
                attendance.setActualQuantity(rs.getInt("actualQuantity"));
                attendance.setAlpha(rs.getFloat("alpha"));
                attendance.setNote(rs.getString("attendanceNote"));

                WorkAssignment assignment = new WorkAssignment();
                assignment.setId(rs.getInt("workAssignmentId"));
                assignment.setQuantity(rs.getInt("orderedQuantity"));

                Employee employee = new Employee();
                employee.setId(rs.getInt("employeeId"));
                employee.setName(rs.getString("employeeName"));
                assignment.setEmployee(employee);

                Product product = new Product();
                product.setId(rs.getInt("productId"));
                product.setName(rs.getString("productName"));
                assignment.setProduct(product);

                attendance.setWorkAssignment(assignment);
                attendances.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    @Override
    public void insert(Attendance model) {
        String sql = "INSERT INTO Attendances (waid, actualquantity, alpha, note) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, model.getWorkAssignment().getId());
            ps.setInt(2, model.getActualQuantity());
            ps.setFloat(3, model.getAlpha());
            ps.setString(4, model.getNote());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Attendance model) {
        String sql = "UPDATE Attendances SET actualquantity = ?, alpha = ?, note = ? WHERE atid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, model.getActualQuantity());
            ps.setFloat(2, model.getAlpha());
            ps.setString(3, model.getNote());
            ps.setInt(4, model.getAtid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Attendance model) {
        String sql = "DELETE FROM Attendances WHERE atid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, model.getAtid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Attendance> list() {
        ArrayList<Attendance> attendances = new ArrayList<>();
        String sql = """
            SELECT a.atid, a.actualquantity, a.alpha, a.note, 
                   wa.waid, wa.quantity AS orderedQuantity, wa.note AS waNote, 
                   wa.eid
            FROM Attendances a
            JOIN WorkAssignments wa ON a.waid = wa.waid
            """;
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Create Attendance object
                Attendance attendance = new Attendance();
                attendance.setAtid(rs.getInt("atid"));
                attendance.setActualQuantity(rs.getInt("actualquantity"));
                attendance.setAlpha(rs.getFloat("alpha"));
                attendance.setNote(rs.getString("note"));

                // Create WorkAssignment object
                WorkAssignment workAssignment = new WorkAssignment();
                workAssignment.setId(rs.getInt("waid"));
                workAssignment.setQuantity(rs.getInt("orderedQuantity"));
                workAssignment.setNote(rs.getString("waNote"));
                workAssignment.setEmployeeId(rs.getInt("eid"));

                // Set WorkAssignment in Attendance
                attendance.setWorkAssignment(workAssignment);
                attendances.add(attendance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendances;
    }

    @Override
    public Attendance get(int id) {
        Attendance attendance = null;
        String sql = """
            SELECT a.atid, a.actualquantity, a.alpha, a.note,
                   wa.waid, wa.quantity AS orderedQuantity, wa.note AS waNote, 
                   wa.eid
            FROM Attendances a
            JOIN WorkAssignments wa ON a.waid = wa.waid
            WHERE a.atid = ?
            """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                attendance = new Attendance();
                attendance.setAtid(rs.getInt("atid"));
                attendance.setActualQuantity(rs.getInt("actualquantity"));
                attendance.setAlpha(rs.getFloat("alpha"));
                attendance.setNote(rs.getString("note"));

                // Create and set WorkAssignment object
                WorkAssignment workAssignment = new WorkAssignment();
                workAssignment.setId(rs.getInt("waid"));
                workAssignment.setQuantity(rs.getInt("orderedQuantity"));
                workAssignment.setNote(rs.getString("waNote"));
                workAssignment.setEmployeeId(rs.getInt("eid"));

                // Set WorkAssignment in Attendance
                attendance.setWorkAssignment(workAssignment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendance;
    }
}
