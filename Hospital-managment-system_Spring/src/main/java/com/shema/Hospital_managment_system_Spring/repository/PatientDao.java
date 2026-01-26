package com.shema.Hospital_managment_system_Spring.repository;

import com.shema.Hospital_managment_system_Spring.entity.Patient;
import com.shema.Hospital_managment_system_Spring.exception.DatabaseException;
import com.shema.Hospital_managment_system_Spring.util.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PatientDao {
    public void addPatient(Patient patient)  {
        String sql = "INSERT INTO patients " +
                "(first_name, last_name, dob, gender, contact_number, address) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, patient.getFirstName());
            ps.setString(2, patient.getLastName());
            ps.setDate(3, java.sql.Date.valueOf(patient.getDob()));
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getContactNumber());
            ps.setString(6, patient.getAddress());

            ps.executeUpdate();
            System.out.println("Insertion successful");
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {

                patient.setPatientId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error adding patient", e);
        }
    }
    public boolean existsById(Long patientId) {
        String sql = "SELECT 1 FROM patients WHERE patient_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();  // true if exists
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error checking patient existence", e);
        }
    }
    public List<Patient> searchPatientByLastName(String lastName){
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE last_name ILIKE ? ORDER BY last_name ASC";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" +lastName + "%");

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    patients.add(mapRowToPatient(rs));
                }
            }
        }catch(SQLException e){
            throw new DatabaseException("Error searching patients by last name", e);
        }
        return patients;
    }
    public List<Patient> getAllPatients(){
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY last_name ASC";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                patients.add(mapRowToPatient(rs));

            }

        }catch (SQLException e ){
            throw new DatabaseException("Error fetching all patients", e);
        }
        return patients;
    }
    public void deletePatient( long patientId){
        String sql = "DELETE FROM patients WHERE patient_id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)){
            ps.setLong(1,patientId);
            ps.executeUpdate();
            System.out.println("Patient(s) deleted");

        }catch (SQLException e){
            throw new DatabaseException("Error deleting patient", e);
        }
    }
    public void updatePatient(Patient patient) {
        String sql = "UPDATE patients SET " +
                "first_name = ?, last_name = ?, dob = ?, gender = ?, contact_number = ?, address = ? " +
                "WHERE patient_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patient.getFirstName());
            ps.setString(2, patient.getLastName());
            ps.setDate(3, java.sql.Date.valueOf(patient.getDob()));
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getContactNumber());
            ps.setString(6, patient.getAddress());
            ps.setLong(7, patient.getPatientId());

            int rows = ps.executeUpdate();
            System.out.println(rows + " patient(s) updated");

        } catch (SQLException e) {
            throw new DatabaseException("Error updating patient", e);
        }
    }
    public Patient searchPatientById(long patientId) {
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPatient(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean contactExistsInPatients(String contactNumber, long excludePatientId) {
        String sql = "SELECT COUNT(*) FROM patients WHERE contact_number = ? AND patient_id != ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, contactNumber);
            ps.setLong(2, excludePatientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error checking contact uniqueness", e);
        }
        return false;
    }

    private Patient mapRowToPatient(ResultSet rs) throws SQLException {
        return new Patient(
                rs.getLong("patient_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getDate("dob").toLocalDate(),
                rs.getString("gender"),
                rs.getString("contact_number"),
                rs.getString("address")
        );
    }
}
