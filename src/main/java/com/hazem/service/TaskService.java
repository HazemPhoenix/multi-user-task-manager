package com.hazem.service;

import com.hazem.config.DatabaseConnectionManager;
import com.hazem.dao.TaskDAO;
import com.hazem.model.Task;

import java.sql.Connection;
import java.sql.SQLException;

public class TaskService {
    private final DatabaseConnectionManager dbManager;
    private final TaskDAO taskDao;

    public TaskService(DatabaseConnectionManager dbManager, TaskDAO taskDAO) {
        this.dbManager = dbManager;
        this.taskDao = taskDAO;
    }

    public boolean addTask(int userId, String name, boolean isDone) {
        Task task = new Task(name, userId, isDone);
        try(Connection con = dbManager.getConnection()) {
            taskDao.save(con, task);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
