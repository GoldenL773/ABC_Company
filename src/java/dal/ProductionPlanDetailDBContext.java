package dal;

import dal.DBContext;
import java.util.ArrayList;
import java.util.List;
import model.PlanDetail;
import model.PlanDetail;
import java.sql.*;
import model.Product;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductionPlanDetailDBContext extends DBContext<PlanDetail> {

    public void insert(List<PlanDetail> details) {
        String sql = "INSERT INTO PlanDetails (phid, sid, date, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (PlanDetail detail : details) {
                ps.setInt(1, detail.getHeader().getId());
                ps.setInt(2, detail.getSid());
                ps.setDate(3, detail.getDate());
                ps.setInt(4, detail.getQuantity());
                ps.addBatch();
            }
            ps.executeBatch(); // Execute batch for better performance when inserting multiple rows
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(PlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PlanDetail> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
   public PlanDetail get(int pdid) {
        PlanDetail planDetail = null;
        String sql = "SELECT pd.pdid, pd.quantity, pd.date, pd.sid, ph.pid, p.pname "
                   + "FROM PlanDetails pd "
                   + "JOIN PlanHeaders ph ON pd.phid = ph.phid "
                   + "JOIN Products p ON ph.pid = p.pid "
                   + "WHERE pd.pdid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pdid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                planDetail = new PlanDetail();
                planDetail.setPdid(rs.getInt("pdid"));
                planDetail.setQuantity(rs.getInt("quantity"));
                planDetail.setDate(rs.getDate("date"));
                planDetail.setSid(rs.getInt("sid"));

                Product product = new Product();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("pname"));
                planDetail.setProduct(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return planDetail;
    }

    public List<PlanDetail> getDetailsByPlanId(int planId) {
        List<PlanDetail> details = new ArrayList<>();

        // SQL query to retrieve detailed shift information for a given plan ID
        String sql = "SELECT pd.pdid, pd.phid, pd.sid, pd.date, pd.quantity, pd.note, "
                + "s.sid, "
                + "p.pid AS product_id, p.pname AS product_name "
                + "FROM PlanDetails pd "
                + "JOIN PlanHeaders ph ON pd.phid = ph.phid "
                + "JOIN Products p ON ph.pid = p.pid "
                + "JOIN Shifts s ON pd.sid = s.sid "
                + "WHERE ph.plid = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, planId);  // Set the plan ID for the query
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Create a new PlanDetail object for each result row
                PlanDetail detail = new PlanDetail();
                detail.setPdid(rs.getInt("pdid")); // Set PlanDetail ID
                detail.setDate(rs.getDate("date")); // Set the date of the shift
                detail.setQuantity(rs.getInt("quantity")); // Set the quantity for the shift
                detail.setNote(rs.getString("note")); // Set the note if available

                detail.setSid(rs.getInt("sid"));

                // Set the associated product
                Product product = new Product();
                product.setId(rs.getInt("product_id")); // Set product ID
                product.setName(rs.getString("product_name")); // Set product name
                detail.setProduct(product);

                details.add(detail); // Add the detail to the list
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging the exception
        }

        return details;
    }

    @Override
    public void insert(PlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
