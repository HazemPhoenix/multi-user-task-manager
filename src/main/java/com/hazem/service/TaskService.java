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

    public void addTask(int userId, String name, boolean isDone) {
        Task task = new Task(name, userId, isDone);
        try(Connection con = dbManager.getConnection()) {
            taskDao.save(con, task);
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void deleteTaskById(int id) {
        try(Connection con = dbManager.getConnection()) {
            Task task = taskDao.findById(con, id);
            if(task == null) {
                throw new RuntimeException("Task not found.");
            }
            this.taskDao.delete(con, task);
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(int taskId, String name, boolean isDone) {
        Task task = new Task(taskId, name, isDone);
        try(Connection con = dbManager.getConnection()) {
            this.taskDao.update(con, task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
