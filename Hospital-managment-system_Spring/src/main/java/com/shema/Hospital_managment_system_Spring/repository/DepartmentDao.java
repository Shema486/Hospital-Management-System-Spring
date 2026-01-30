package com.shema.Hospital_managment_system_Spring.repository;
import com.shema.Hospital_managment_system_Spring.entity.Department;
import com.shema.Hospital_managment_system_Spring.exception.DatabaseException;
import com.shema.Hospital_managment_system_Spring.util.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentDao {
    public void addDepartment(Department department) {
        String sql = "INSERT INTO departments (dept_name, location_floor) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, department.getDeptName());
            ps.setInt(2, department.getLocationFloor());
            ps.executeUpdate();
            System.out.println("Department added successfully: ");
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {

            department.setDeptId(rs.getLong(1));
            }
        } catch (SQLException e) {
           throw new DatabaseException("Error in adding department",e);

        }

    }

    public List<Department> getAllDepartment(){
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    list.add(mapToDepartment(rs));
                }
            }

        }catch (SQLException e){
            throw new DatabaseException("Unable to fetch departments from database", e);
        }
        return list;
    }
    public void updateDepartment(Department department) {
        String sql = "UPDATE departments SET dept_name = ?, location_floor = ? WHERE dept_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, department.getDeptName());
            ps.setInt(2, department.getLocationFloor());
            ps.setLong(3, department.getDeptId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Unable to update departments", e);
        }
    }
    public boolean existByID(Long id){
        String sql = "SELECT 1 FROM department WHERE dept_id =?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql) ) {
            ps.setLong(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();  // true if exists
            }
        }catch (SQLException e){
            throw new DatabaseException("Error checking department",e);
        }
    }

    public Department findById(Long deptId){
        String sql = "SELECT * FROM departments WHERE dept_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,deptId);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return mapToDepartment(rs);
                }
            }
        }catch (SQLException e){
            throw new DatabaseException("Unable to fetch department from database", e);
        }
        return  null;
    }
    public void deleteDepartment(Long deptId) {
        String sql = "DELETE FROM departments WHERE dept_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, deptId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete department", e);
        }
    }

    public Department mapToDepartment(ResultSet rs)throws SQLException{
        return new Department(
                rs.getLong("dept_id"),
                rs.getInt("location_floor"),
                rs.getString("dept_name")
        );
    }

}
