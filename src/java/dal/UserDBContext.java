package dal;

import java.util.ArrayList;
import model.auth.User;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.auth.Feature;
import model.auth.Role;

public class UserDBContext extends DBContext<User> {

    public ArrayList<Role> getRoles(String username) {
        PreparedStatement stm = null;
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.rid, r.rname, f.fid, f.fname, f.[url] FROM [Users] u \n"
                    + "    INNER JOIN UserRoles ur ON u.uid = ur.uid\n"
                    + "    INNER JOIN [Roles] r ON r.rid = ur.rid\n"
                    + "    INNER JOIN RoleFeatures rf ON rf.rid = r.rid\n"
                    + "    INNER JOIN Features f ON f.fid = rf.fid\n"
                    + "WHERE u.username = ?";

            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();

            // Use a Map to avoid creating duplicate Roles
            Map<Integer, Role> roleMap = new HashMap<>();
            while (rs.next()) {
                int rid = rs.getInt("rid");

                // If the role doesn't exist in the map, create it
                Role role = roleMap.get(rid);
                if (role == null) {
                    role = new Role();
                    role.setId(rid);
                    role.setName(rs.getString("rname"));
                    roleMap.put(rid, role);
                    roles.add(role);
                }

                // Add the feature to the existing Role
                Feature feature = new Feature();
                feature.setId(rs.getInt("fid"));
                feature.setName(rs.getString("fname"));
                feature.setUrl(rs.getString("url"));
                role.getFeatures().add(feature);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }

    public User get(String username, String password) {
        User user = null;
        PreparedStatement stm = null;
        try {
            // Simple query to match username and password (in plaintext)
            String sql = "SELECT [username], [password] FROM [Users]\n"
                    + "WHERE [username] = ? AND [password] = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(username);
                // You can add any other user details here if needed
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
