package com.shema.Hospital_managment_system_Spring.repository;

import com.shema.Hospital_managment_system_Spring.entity.PrescriptionItem;
import com.shema.Hospital_managment_system_Spring.util.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PrescriptionItemDao {


    public void add(PrescriptionItem item) {
        String sql = """
            INSERT INTO prescription_items
            (prescription_id, item_id, dosage_instruction, quantity_dispensed)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, item.getPrescriptionId());
            ps.setLong(2, item.getItemId());
            ps.setString(3, item.getDosageInstruction());
            ps.setInt(4, item.getQuantityDispensed());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add prescription item", e);
        }
    }

    // GET ALL prescription items
    public List<PrescriptionItem> findAll() {
        List<PrescriptionItem> items = new ArrayList<>();

        String sql = """
            SELECT prescription_id, item_id, dosage_instruction, quantity_dispensed
            FROM prescription_items
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                items.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch prescription items", e);
        }
        return items;
    }

    private PrescriptionItem mapRow(ResultSet rs) throws SQLException {
        return new PrescriptionItem(
                rs.getLong("prescription_id"),
                rs.getLong("item_id"),
                rs.getString("dosage_instruction"),
                rs.getInt("quantity_dispensed")
        );
    }
}
