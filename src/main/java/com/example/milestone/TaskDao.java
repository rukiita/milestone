package com.example.milestone;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

public class TaskDao {

    private Connection conn;

    public  TaskDao(){
        String url = "jdbc:h2:./milestone";
        String username = "sa";
        String password="";
        try{
            conn = DriverManager.getConnection(url,username,password);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Task createTask(Task task){
        String sql = "INSERT INTO task (id,title,author,classification,dueDate) VALUES (?,?,?,?,?)";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1,task.getId());
            statement.setString(2,task.getTitle());
            statement.setString(3,task.getAuthor());
            statement.setInt(4,task.getClassification().ordinal());
            statement.setDate(5,new java.sql.Date(task.getDueDate().getTime()));
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return task;
    }

    public List<Task> findAll(){
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task";
        try(Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Task task =  createTaskFromResultSet(resultSet);
                tasks.add(task);
            }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return tasks;
    }
    public Task findTaskById(String id){
        String sql = "SELECT * FROM task WHERE id =?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return createTaskFromResultSet(resultSet);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateTask(Task task){
        String sql = "UPDATE task SET title = ?, author = ?, classification = ? dueDate = ? WHERE id =?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, task.getTitle());
            statement.setString(2,task.getAuthor());
            statement.setInt(3,task.getClassification().ordinal());
            statement.setDate(4, new java.sql.Date(task.getDueDate().getTime()));
            statement.setString(5,task.getId());
            return statement.executeUpdate()==1;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTaskById(@PathVariable String id){
        String sql = "DELETE FROM task WHERE id =?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1,id);
            return statement.executeUpdate() == 1;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    private Task createTaskFromResultSet(ResultSet resultSet) throws SQLException{
        String id = resultSet.getString("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        TaskClassification classification = TaskClassification.fromNumber(resultSet.getInt("classification"));
        Date dueDate = resultSet.getDate("dueDate");
        Task task = new Task(title,author,classification,dueDate);
        task.setId(id);
        return task;
    }
}
