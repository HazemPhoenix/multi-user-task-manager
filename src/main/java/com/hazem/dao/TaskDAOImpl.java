package com.hazem.dao;

import com.hazem.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public Task findById(Connection con, int id) {
        return null;
    }

    @Override
    public List<Task> findAllByUser(Connection con, int userId) {
        return List.of();
    }

    @Override
    public void update(Connection con, Task task) {

    }

    @Override
    public void delete(Connection con, Task task) {

    }
}
