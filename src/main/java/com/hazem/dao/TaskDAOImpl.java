package com.hazem.dao;

import com.hazem.model.Task;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    @Override
    public void save(Connection con, Task task) throws SQLException {
        String sql = "INSERT INTO tasks (name, user_id, is_done) values (?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, task.getName());
        preparedStatement.setInt(2, task.getUserId());
        preparedStatement.setBoolean(3, task.isDone());
        preparedStatement.executeUpdate();
    }

    @Override
    public Task findById(Connection con, int id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) return new Task(rs.getInt("id"), rs.getString("name"), rs.getInt("user_id"), rs.getBoolean("is_done"));
        return null;
    }

    @Override
    public List<Task> findAllByUser(Connection con, int userId) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE user_id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        List<Task> taskList = new ArrayList<>();
        while(rs.next()) {
            Task task = new Task(rs.getInt("id"), rs.getString("name"), rs.getInt("user_id"), rs.getBoolean("is_done"));
            taskList.add(task);
        }
        return taskList;
    }

    @Override
    public void update(Connection con, Task task) {

    }

    @Override
    public void delete(Connection con, Task task) throws SQLException {
        int id = task.getId();
        String sql = "DELETE FROM tasks WHERE id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
