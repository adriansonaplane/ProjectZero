package dev.ledesma.dao;

import dev.ledesma.entities.Employee;
import dev.ledesma.utils.ConnectionUtility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PostgresEmployeeDAO implements EmployeeDAO{

    static Logger logger = Logger.getLogger(PostgresEmployeeDAO.class.getName());
    @Override
    public Employee createEmployee(Employee employee) {

        try(Connection conn = ConnectionUtility.createConnection()) {

        String sql = "insert into employee values (default, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//Statement.RETURN_GENERATED_KEYS
        ps.setString(1, employee.getFirstName());
        ps.setString(2, employee.getLastName());
        ps.setString(3, employee.getTitle());
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        int key = rs.getInt("id");
        employee.setId(key);

        return employee;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Could Not Create Employee: " + employee, e);
            return null;
        }

    }

    @Override
    public boolean deleteEmployee(int id) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "delete from employee where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Could Not Delete Employee Id: " + id, e);
            return false;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "update employee set f_name = ?, l_name = ?, title = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getTitle());
            ps.setInt(4,employee.getId());
            ps.executeUpdate();

            return employee;

        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Update Employee: " + employee, e);
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int id) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from employee where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFirstName(rs.getString("f_name"));
            employee.setLastName(rs.getString("l_name"));
            employee.setTitle(rs.getString("title"));

            return employee;

        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Retrieve Employee by Id: " + id, e);
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Employee> employeeSet = new ArrayList<>();

            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("f_name"));
                employee.setLastName(rs.getString("l_name"));
                employee.setTitle(rs.getString("title"));
                employeeSet.add(employee);
            }
            return employeeSet;

        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Retrieve All Employees", e);
            return null;
        }
    }

}
