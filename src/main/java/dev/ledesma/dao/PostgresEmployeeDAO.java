package dev.ledesma.dao;

import dev.ledesma.entities.Employee;
import dev.ledesma.utils.ConnectionUtility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class PostgresEmployeeDAO implements EmployeeDAO{

    static Logger logger = Logger.getLogger(PostgresEmployeeDAO.class.getName());
    @Override
    public boolean createEmployee(Employee employee) {

        try {
        Connection conn = ConnectionUtility.createConnection();
        String sql = "insert into employee values (default, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, employee.getFirstName());
        ps.setString(2, employee.getLastName());
        ps.setString(3, employee.getTitle());
        ps.execute();
//        ResultSet rs = ps.getGeneratedKeys();
//        rs.next();
//        int key = rs.getInt("id");
//        employee.setId(key);

        return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
        }

        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "update employee set f_name = ?, l_name = ?, title = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getTitle());
            ps.setInt(4,employee.getId());
            ps.executeUpdate();

            return true;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
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
        }
        return null;
    }

    @Override
    public Set<Employee> getAllEmployees() {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Set<Employee> employeeSet = new HashSet<>();

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
        }
        return null;
    }

    @Override
    public boolean deleteAllEmpoyees() {
        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "delete from employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
