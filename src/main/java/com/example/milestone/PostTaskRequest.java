package com.example.milestone;

public class PostTaskRequest {
    private String title;
    private String author;
    private String classification;
    private String dueDate; // Date 型から String 型に変更

    public PostTaskRequest() {
        this.title = "";
        this.author = "";
        this.classification = TaskClassification.GENERAL.name();
        this.dueDate = "";
    }

    // getters and setters
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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    } 

    //   @Override
    //   public String toString() {
    //     return author + "『" + title + "』(" + numOfPages + "ページ, " + classification + ")";
    //   }
    
}
