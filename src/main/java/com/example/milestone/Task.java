package com.example.milestone;

import java.util.Date;
import java.util.UUID;

public class Task {
    private String id;
    private String title;
    private String author;
    private TaskClassification classification;
    private Date dueDate;

    public Task() {
        this.title = "";
        this.author = "";
        this.classification = TaskClassification.GENERAL;
        this.dueDate = new Date(); // デフォルト値
    }

    // コンストラクタ
    public Task(String title, String author, TaskClassification classification, Date dueDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.classification = classification;
        this.dueDate = dueDate;
    }

    // コンストラクタ
    public Task(String title, String author, int classificationNumber, Date dueDate) {
        this(title, author, TaskClassification.fromNumber(classificationNumber), dueDate);
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public TaskClassification getClassification() {
        return classification;
    }

    public void setClassification(TaskClassification classification) {
        this.classification = classification;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
      return id+":"+author + "『" + title + "』, " + classification + ") , 期日:"+ dueDate ;
    }
  }
  