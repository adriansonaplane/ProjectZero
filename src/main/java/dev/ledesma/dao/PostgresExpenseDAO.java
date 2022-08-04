package dev.ledesma.dao;

import dev.ledesma.entities.Expense;
import dev.ledesma.utils.ConnectionUtility;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

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
        }
        return false;
    }

    @Override
    public boolean deleteExpense(int id) {
        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "delete from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
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
        }
        return false;
    }

    @Override
    public boolean modifyExpense(int id) {
        return false;
    }

    @Override
    public Expense getExpenseById(int id) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Expense expense = new Expense();
            expense.setId(rs.getInt("id"));
            expense.setAmount(rs.getInt("amount"));
            expense.setDate(rs.getLong("date"));
            expense.setCategory(rs.getString("category"));
            expense.setDescription(rs.getString("description"));
            expense.setStatus(Expense.expenseStatus.valueOf(rs.getString("status")));
            expense.setEmployeeId(rs.getInt("employee_id"));

            return expense;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Expense> getAllExpenses() {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Set<Expense> expenseSet = new HashSet<>();

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
        }
        return null;
    }

    @Override
    public Set<Expense> getAllEmployeeExpenseById(int id) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Set<Expense> expenseSet = new HashSet<>();

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
        }
        return null;
    }

    @Override
    public Set<Expense> getAllExpenseByStatus(int id) {
        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense where status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Set<Expense> expenseSet = new HashSet<>();

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
        }
        return null;
    }

}
