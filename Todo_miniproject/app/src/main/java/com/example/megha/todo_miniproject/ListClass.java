package com.example.megha.todo_miniproject;

import android.text.Editable;

/**
 * Created by megha on 3/8/2015.
 */
public class ListClass
{
    int id;
    String title;
    String description;
    String date;
    int status;

    public ListClass()
    {
    }

    public ListClass(int id, String title, String description, String date, int status)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date=date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
