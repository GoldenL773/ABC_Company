package dal;

import dal.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductionPlan;
import java.sql.*;
import java.util.List;
import model.PlanDetail;
import model.Product;
import model.ProductionPlanHeader;

public class ProductionPlanDBContext extends DBContext<ProductionPlan> {

    @Override
    public void insert(ProductionPlan model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_plan = "INSERT INTO [Plans]\n"
                    + "           ([plname]\n"
                    + "           ,[startdate]\n"
                    + "           ,[enddate]\n"
                    + "           ,[did])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setString(1, model.getName());
            stm_insert_plan.setDate(2, model.getStart());
            stm_insert_plan.setDate(3, model.getEnd());
            stm_insert_plan.setInt(4, model.getDept().getId());
            stm_insert_plan.executeUpdate();

            String sql_select_plan = "SELECT @@IDENTITY as plid";
            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("plid"));
            }

            String sql_insert_header = "INSERT INTO [PlanHeaders]\n"
                    + "           ([plid]\n"
                    + "           ,[pid]\n"
                    + "           ,[quantity]\n"
                    + "           ,[estimatedeffort])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            for (ProductionPlanHeader header : model.getHeaders()) {
                PreparedStatement stm_insert_header = connection.prepareStatement(sql_insert_header);
                stm_insert_header.setInt(1, model.getId());
                stm_insert_header.setInt(2, header.getProduct().getId());
                stm_insert_header.setInt(3, header.getQuantity());
                stm_insert_header.setFloat(4, header.getEstimatedeffort());
                stm_insert_header.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(ProductionPlan model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(ProductionPlan model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ProductionPlan> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<ProductionPlanHeader> getHeadersByPlanId(int planId) {
        List<ProductionPlanHeader> headers = new ArrayList<>();
        String sql = "SELECT ph.phid, ph.plid, ph.pid, ph.quantity, ph.estimatedeffort, "
                + "p.pname AS product_name "
                + "FROM PlanHeaders ph "
                + "JOIN Products p ON ph.pid = p.pid "
                + "WHERE ph.plid = ?";

        try ( PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, planId); // Set the planId to the query
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductionPlanHeader header = new ProductionPlanHeader();
                header.setId(rs.getInt("phid")); // Set phid
               // header.getPlan().setId(rs.getInt("plid")); // Set plid
                header.setQuantity(rs.getInt("quantity")); // Set quantity
                header.setEstimatedeffort(rs.getFloat("estimatedeffort")); // Set estimatedeffort

                // Set associated product
                Product product = new Product();
                product.setId(rs.getInt("pid")); // Set product id (pid)
                product.setName(rs.getString("product_name")); // Set product name (pname)
                header.setProduct(product); // Link product to the header

                headers.add(header); // Add to headers list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return headers;
    }

    @Override
    public ProductionPlan get(int id) {
        ProductionPlan plan = null;

        // Corrected SQL Query
        String sql = "SELECT p.plid AS planId, \n"
                + "       p.plname AS planName,\n"
                + "	   startdate, enddate,\n"
                + "       h.phid AS headerId, \n"
                + "       h.pid AS planIdInHeader, \n"
                + "       pr.pid AS productId, \n"
                + "       pr.pname AS productName\n"
                + "FROM Plans p\n"
                + "JOIN PlanHeaders h ON p.plid = h.plid\n"
                + "JOIN Products pr ON h.pid = pr.pid\n"
                + "WHERE p.plid = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);  // Setting the plan ID parameter
            ResultSet rs = stmt.executeQuery();

            // Checking if we have results
            if (rs.next()) {
                plan = new ProductionPlan();
                plan.setId(rs.getInt("planId"));  // Set the Plan ID
                plan.setName(rs.getString("planName"));  // Set the Plan Name

                plan.setStart(rs.getDate("startdate"));
                plan.setEnd(rs.getDate("enddate"));
                plan.setHeaders(new ArrayList<>());  // Initialize the headers list

                // Iterate through the result set and build the plan headers
                do {
                    ProductionPlanHeader header = new ProductionPlanHeader();
                    header.setId(rs.getInt("headerId"));  // Set the Header ID

                    // Set the associated product details for each header
                    Product product = new Product();
                    product.setId(rs.getInt("productId"));  // Set the Product ID
                    product.setName(rs.getString("productName"));  // Set the Product Name
                    header.setProduct(product);  // Assign the product to the header

                    // Add the header to the plan
                    plan.getHeaders().add(header);
                } while (rs.next());  // Continue processing if more rows are found
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Consider logging errors instead of printing
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();  // Ensure the connection is closed after use
                }
            } catch (SQLException ex) {
                ex.printStackTrace();  // Handle potential closing errors
            }
        }

        return plan;  // Return the constructed plan
    }

}
