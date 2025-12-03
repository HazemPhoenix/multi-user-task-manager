package com.hazem.service;

import com.hazem.config.DatabaseConnectionManager;
import com.hazem.dao.TaskDAO;
import com.hazem.model.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Task> getUserTasks(int userId) {
        try(Connection con = dbManager.getConnection()){
            return this.taskDao.findAllByUser(con, userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean deleteTaskById(int id) {
        try(Connection con = dbManager.getConnection()) {
            Task task = taskDao.findById(con, id);
            if(task == null) {
                throw new RuntimeException("Task not found.");
            }
            this.taskDao.delete(con, task);
            return true;
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }
}
