package com.shema.Hospital_managment_system_Spring.repository;

import com.shema.Hospital_managment_system_Spring.entity.Role;
import com.shema.Hospital_managment_system_Spring.entity.SystemUser;
import com.shema.Hospital_managment_system_Spring.exception.DatabaseException;
import com.shema.Hospital_managment_system_Spring.util.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;


@Repository
public class SystemUserDao {

    public void addUser(SystemUser user) {
        String sql = "INSERT INTO system_users (username, password, full_name, role, is_active) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()); // hashed password
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getRole().name());
            ps.setBoolean(5, user.getIsActive() != null ? user.getIsActive() : true);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setUserId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error adding user", e);
        }
    }

    public SystemUser findByUsername(String username) {
        String sql = "SELECT * FROM system_users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapToUser(rs);
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error finding user", e);
        }
        return null;
    }

    private SystemUser mapToUser(ResultSet rs) throws SQLException {
        return new SystemUser(
                rs.getLong("user_id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("full_name"),
                Role.valueOf(rs.getString("role").toUpperCase()),
                rs.getBoolean("is_active"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
