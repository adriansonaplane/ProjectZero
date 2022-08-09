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
    public Expense createExpense(Expense expense) {

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

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int key = rs.getInt("id");
            expense.setId(key);

            return expense;
        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Create Expense: " + expense, e);
            return null;
        }
    }

    public Expense createExpense(int id, Expense expense){
        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "insert into expense values (default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expense.getAmount());
            ps.setLong(2, expense.getDate());
            ps.setString(3, expense.getCategory());
            ps.setString(4, expense.getDescription());
            ps.setString(5, expense.getStatus().toString());
            ps.setInt( 6, id);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int key = rs.getInt("id");
            expense.setEmployeeId(key);

            return expense;

        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Could Not Create Expense: " + id + ", " + expense, e);
            return null;
        }
    }
    @Override
    public boolean deleteExpense(int id) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "select * from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Expense.expenseStatus status = Expense.expenseStatus.valueOf(
                    rs.getString("status"));
            if (status.equals(Expense.expenseStatus.PENDING)){
                sql = "delete from expense where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.execute();
                return true;
            } else {
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            logger.error("Could Not Delete Expense Id: " + id, e);
            return false;
        }
    }

    @Override
    public Expense updateExpense(Expense expense) {

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

            return expense;

        }catch(SQLException e ){
            e.printStackTrace();
            logger.error("Could Not Update Expense: " + expense, e);
            return null;
        }
    }

    @Override //Fix
    public Expense modifyExpense(int id, Expense.expenseStatus status) {

        try(Connection conn = ConnectionUtility.createConnection()){
            String sql = "update expense set status = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status.toString());
            ps.setInt(2, id);
            ps.executeUpdate();

            sql = "select * from expense where id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql);
            ps2.setInt(1, id);
            ResultSet rs = ps2.executeQuery();
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

        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Could Not Modify Expense Status: " + id + ", " + status, e);
            return null;
        }
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
            logger.error("Could Not Retrieve Expense by Id: " + id, e);
            return null;
        }
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
            return null;
        }
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
            return null;
        }
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
