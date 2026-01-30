package com.shema.Hospital_managment_system_Spring.repository;

import com.shema.Hospital_managment_system_Spring.entity.Appointment;
import com.shema.Hospital_managment_system_Spring.entity.Prescription;
import com.shema.Hospital_managment_system_Spring.exception.DatabaseException;
import com.shema.Hospital_managment_system_Spring.util.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PrescriptionDao {

    public void addPrescription(Prescription prescription) {

        String sql = """
            INSERT INTO prescriptions (appointment_id, date_issued, notes)
            VALUES (?, ?, ?)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, prescription.getAppointmentId());
            ps.setTimestamp(2, Timestamp.valueOf(prescription.getPrescriptionDate()));
            ps.setString(3, prescription.getNotes());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {

                prescription.setPrescriptionId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error adding prescription ", e);
        }

    }

    // READ BY APPOINTMENT
    public List<Prescription> findByAppointmentId(Long appointmentId) {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, appointmentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prescriptions.add(mapRowToPrescription(rs));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching appointment by ID in prescription", e);
        }
        return prescriptions;
    }
    public Prescription findById(Long id){
        String sql = "SELECT * FROM prescriptions WHERE prescription_id =?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,id);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapRowToPrescription(rs);
                }
            }

        }catch (SQLException e){
            throw new DatabaseException("Error fetching prescription by ID", e);
        }
        return null;
    }

    // READ ALL
    public List<Prescription> findAll() {

        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions ORDER BY date_issued DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                prescriptions.add(mapRowToPrescription(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching prescriptions", e);
        }
        return prescriptions;
    }


    // UPDATE (NOTES)
    public boolean updatePrescription(Long id,String notes) {

        String sql = "UPDATE prescriptions SET notes = ? WHERE prescription_id = ? ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ;
            ps.setString(1, notes);
            ps.setLong(2,id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Error updating notes", e);
        }

    }

    // DELETE
    public boolean deletePrescription(Long prescriptionId) {

        String sql = "DELETE FROM prescriptions WHERE prescription_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, prescriptionId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting prescription", e);
        }
    }

    //  Mapper
    private Prescription mapRowToPrescription(ResultSet rs) throws SQLException {

        return new Prescription(
                rs.getLong("prescription_id"),
                rs.getLong("appointment_id"),
                rs.getTimestamp("date_issued").toLocalDateTime(),
                rs.getString("notes")
        );
    }
}
