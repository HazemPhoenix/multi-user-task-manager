package com.hazem.dao;

import com.hazem.model.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {
    void save(Connection con, Task task) throws SQLException;
    Task findById(Connection con, int id);
    List<Task> findAllByUser(Connection con, int userId);
    void update(Connection con, Task task);
    void delete(Connection con, Task task);
}
