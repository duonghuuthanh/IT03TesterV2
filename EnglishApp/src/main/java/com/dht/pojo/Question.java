/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.pojo;

import java.util.UUID;

/**
 *
 * @author admin
 */
public class Question {
    private String id;
    private String content;
    private int categoryId;
    
    {
        id = UUID.randomUUID().toString();
    }

    public Question(String name, int categoryId) {
        this.content = name;
        this.categoryId = categoryId;
    }

    public Question(String id, String content, int categoryId) {
        this.id = id;
        this.content = content;
        this.categoryId = categoryId;
    }
    
    

    public Question() {
    }
    
    

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getContent() {
        return content;
    }

    /**
     * @param name the name to set
     */
    public void setContent(String name) {
        this.content = name;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
