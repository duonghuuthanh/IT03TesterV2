/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

import com.dht.pojo.Category;
import com.dht.unittestdemo.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryService {

    public List<Category> getCategories() throws SQLException {
        List<Category> cates = new ArrayList<>();
        try ( Connection conn = JdbcUtils.getConn()) {
            // B3 Truy van
            Statement stm = conn.createStatement();
            // Truy van lay du lieu --> select
            ResultSet rs = stm.executeQuery("SELECT * FROM category");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category c = new Category(id, name);
                cates.add(c);
            }
        }
        
        return cates;
    }
}
