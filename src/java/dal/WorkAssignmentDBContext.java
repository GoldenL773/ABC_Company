/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.WorkAssignment;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;

/**
 *
 * @author Golden Lightning
 */
public class WorkAssignmentDBContext extends DBContext<WorkAssignment> {

    public void deleteAssignment(int assignmentId) {
        String sql = "DELETE FROM WorkAssignments WHERE waid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, assignmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void insert(WorkAssignment assignment) {
        String sql = "INSERT INTO WorkAssignments (pdid, eid, quantity, note) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, assignment.getDetailId());
            ps.setInt(2, assignment.getEmployeeId());
            ps.setInt(3, assignment.getQuantity());
            ps.setString(4, assignment.getNote());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<WorkAssignment> getAssignedEmployeesByDetailId(int detailId) {
        List<WorkAssignment> assignments = new ArrayList<>();
        String sql = "SELECT wa.waid, wa.pdid, wa.eid, wa.quantity, wa.note, e.ename "
                + "FROM WorkAssignments wa "
                + "JOIN Employees e ON wa.eid = e.eid "
                + "WHERE wa.pdid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, detailId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WorkAssignment assignment = new WorkAssignment();
                assignment.setId(rs.getInt("waid")); // Set the WorkAssignment ID (waid)
                assignment.setDetailId(rs.getInt("pdid"));
                assignment.setEmployeeId(rs.getInt("eid"));
                assignment.setQuantity(rs.getInt("quantity"));
                assignment.setNote(rs.getString("note"));

                Employee employee = new Employee();
                employee.setId(rs.getInt("eid"));
                employee.setName(rs.getString("ename"));
                assignment.setEmployee(employee); // Setting the associated employee details

                assignments.add(assignment);
            }
        } catch (SQLException e) {
            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return assignments;
    }

    @Override
    public void update(WorkAssignment model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(WorkAssignment model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<WorkAssignment> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public WorkAssignment get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
