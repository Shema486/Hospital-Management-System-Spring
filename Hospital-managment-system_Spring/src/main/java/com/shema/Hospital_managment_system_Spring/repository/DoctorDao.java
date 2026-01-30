package com.shema.Hospital_managment_system_Spring.repository;

import com.shema.Hospital_managment_system_Spring.entity.Doctor;
import com.shema.Hospital_managment_system_Spring.exception.DatabaseException;
import com.shema.Hospital_managment_system_Spring.util.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class DoctorDao {
    public void addDoctor(Doctor doctor){
        String sql = "INSERT INTO doctors (first_name, last_name, email, specialization, phone, dept_id) VALUES(?,?,?,?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,doctor.getFirstName());
            ps.setString(2,doctor.getLastName());
            ps.setString(3,doctor.getEmail());
            ps.setString(4, doctor.getSpecialization());
            ps.setString(5, doctor.getPhone());
            ps.setLong(6, doctor.getDepartment());

            ps.executeUpdate();
            System.out.println("Doctor added successfully: ");
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                doctor.setDoctorId(rs.getLong(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
        public int countByDepartmentId(Long departmentId) {
            String sql = "SELECT COUNT(*) FROM doctors WHERE dept_id = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setLong(1, departmentId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }

            } catch (SQLException e) {
                throw new DatabaseException("Error counting doctors by department ID", e);
            }
            return 0; // no doctors found
        }


    public boolean existsById(Long doctorId) {
        String sql = "SELECT 1 FROM doctors WHERE doctor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();  // true if exists
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error checking doctor existence", e);
        }
    }
    public List<Doctor> getAllDoctors(){
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                doctors.add(mapRowToDoctor(rs));
            }

        }catch (SQLException e ){
            e.printStackTrace();
        }
        return doctors;
    }
    public void updateDoctor(Doctor doctor){
        String sql = "UPDATE doctors SET first_name = ?, last_name = ?, email = ?, specialization = ?, phone = ? WHERE doctor_id = ? ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getEmail());
            ps.setString(4, doctor.getSpecialization());
            ps.setString(5, doctor.getPhone());
            ps.setLong(6,doctor.getDoctorId());

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteDoctor(Long doctorId){
        String sql = "UPDATE doctors SET is_active = false WHERE doctor_id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setLong(1,doctorId);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Doctor> findDoctorsBySpecialization(String specialization){
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors WHERE specialization ILIKE ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,"%" + specialization.trim() + "%");
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    doctors.add(mapRowToDoctor(rs));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return doctors;
    }
    public List<Doctor> getDoctorsPaginated(int limit, int offset){
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors ORDER BY doctor_id LIMIT ? OFFSET ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    doctors.add(mapRowToDoctor(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }
    public Doctor searchDoctorById(Long Id){
        String sql = "SELECT * FROM doctors WHERE doctor_id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1,Id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    return mapRowToDoctor(rs);

                }
            }

        }catch (SQLException e){
            throw new DatabaseException("Error fetching Doctor by Id",e);
        }
        return null;
    }
    public boolean emailExists(String email, long excludeDoctorId) {
        String sql = "SELECT COUNT(*) FROM doctors WHERE email = ? AND doctor_id != ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setLong(2, excludeDoctorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean contactExistsInDoctors(String contactNumber, long excludeDoctorId) {
        String sql = "SELECT COUNT(*) FROM doctors WHERE phone = ? AND doctor_id != ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, contactNumber);
            ps.setLong(2, excludeDoctorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private Doctor mapRowToDoctor(ResultSet rs) throws SQLException {
        return new Doctor(
                rs.getLong("doctor_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("specialization"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getLong("dept_id")
        );
    }
}
