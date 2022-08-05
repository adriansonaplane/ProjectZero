package dev.ledesma.dao;

import dev.ledesma.entities.Expense;
import dev.ledesma.utils.ConnectionUtility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresExpenseDAO implements ExpenseDAO{

    static Logger logger = Logger.getLogger(PostgresExpenseDAO.class.getName());
    @Override
    public boolean createExpense(Expense expense) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "insert into expense values (default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expense.getAmount());
            ps.setLong(2, expense.getDate());
            ps.setString(3, expense.getCategory());
            ps.setString(4, expense.getDescription());
            ps.setString(5, expense.getStatus().toString());
            ps.setInt( 6, expense.getEmployeeId());
            ps.execute();

//            ResultSet rs = ps.getGeneratedKeys();
//            rs.next();
//
//            int key = rs.getInt("id");
//            expense.setId(key);

            return true;
        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Create Expense: " + expense, e);
        }
        return false;
    }

    @Override
    public boolean deleteExpense(int id) {
        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            if(rs.next()) {
                if (Expense.expenseStatus.valueOf(rs.getString("status")) == Expense.expenseStatus.APPROVED) {
                    return false;
                } else if (Expense.expenseStatus.valueOf(rs.getString("status")) == Expense.expenseStatus.DENIED) {
                    return false;
                } else {
                    sql = "delete from expense where id = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, id);
                    ps.execute();
                    return true;
                }
            }else return false;

        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Delete Expense Id: " + id, e);
        }
        return false;
    }

    @Override
    public boolean updateExpense(Expense expense) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "update expense set amount = ?, date = ?, category = ?, description = ?, status = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expense.getAmount());
            ps.setLong(2, expense.getDate());
            ps.setString(3, expense.getCategory());
            ps.setString(4, expense.getDescription());
            ps.setString(5, expense.getStatus().toString());
            ps.setInt( 6, expense.getId());
            ps.executeUpdate();

            return true;

        }catch(SQLException e ){
            e.printStackTrace();
            logger.error("Could Not Update Expense", e);
        }
        return false;
    }

    @Override
    public boolean modifyExpense(int id, Expense.expenseStatus status) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            if(Expense.expenseStatus.valueOf(rs.getString("status")) == Expense.expenseStatus.APPROVED){
                return false;
            }else if(Expense.expenseStatus.valueOf(rs.getString("status")) == Expense.expenseStatus.DENIED){
                return false;
            }else {
                sql = "update expense set status = ? where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, status.toString());
                ps.setInt(2, id);
                ps.executeUpdate();
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Could Not Modify Expense by Id: " + id +"\nand Status: " + status.toString(), e);
        }
        return false;
    }

    @Override
    public Expense getExpenseById(int id) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setAmount(rs.getInt("amount"));
                expense.setDate(rs.getLong("date"));
                expense.setCategory(rs.getString("category"));
                expense.setDescription(rs.getString("description"));
                expense.setStatus(Expense.expenseStatus.valueOf(rs.getString("status")));
                expense.setEmployeeId(rs.getInt("employee_id"));
                return expense;
            }else return null;
        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Retrieve Expense by Id: " + id, e);
        }
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Expense> expenseSet = new ArrayList<>();

            while(rs.next()){
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setAmount(rs.getInt("amount"));
                expense.setDate(rs.getLong("date"));
                expense.setCategory(rs.getString("category"));
                expense.setDescription(rs.getString("description"));
                expense.setStatus(Expense.expenseStatus.valueOf(rs.getString("status")));
                expense.setEmployeeId(rs.getInt("employee_id"));
                expenseSet.add(expense);
            }
            return expenseSet;

        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Retrieve All Expenses", e);
        }
        return null;
    }

    @Override
    public List<Expense> getAllEmployeeExpenseById(int id) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            List<Expense> expenseSet = new ArrayList<>();

            while(rs.next()){
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setAmount(rs.getInt("amount"));
                expense.setDate(rs.getLong("date"));
                expense.setCategory(rs.getString("category"));
                expense.setDescription(rs.getString("description"));
                expense.setStatus(Expense.expenseStatus.valueOf(rs.getString("status")));
                expense.setEmployeeId(rs.getInt("employee_id"));
                expense.setEmployeeId(rs.getInt("employee_id"));
                expenseSet.add(expense);
            }
            return expenseSet;

        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Retrieve All Expenses by Id: " + id, e);
        }
        return null;
    }

    @Override
    public List<Expense> getAllExpenseByStatus(Expense.expenseStatus status) {
        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense where status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status.toString());
            ResultSet rs = ps.executeQuery();

            List<Expense> expenseSet = new ArrayList<>();

            while(rs.next()){
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setAmount(rs.getInt("amount"));
                expense.setDate(rs.getLong("date"));
                expense.setCategory(rs.getString("category"));
                expense.setDescription(rs.getString("description"));
                expense.setStatus(Expense.expenseStatus.valueOf(rs.getString("status")));
                expense.setEmployeeId(rs.getInt("employee_id"));
                expense.setEmployeeId(rs.getInt("employee_id"));
                expenseSet.add(expense);
            }
            return expenseSet;

        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Retrieve All Expense by Status: " + status.toString(), e);
        }
        return null;
    }

}
