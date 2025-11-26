/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DatabaseConnection.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class BookDAO {
    public List<String> getAvailableBookTitles() {
    List<String> titles = new ArrayList<>();
    String sql = "SELECT judul FROM buku WHERE status = 'tersedia'";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            titles.add(rs.getString("judul"));
        }

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    return titles;
}

}
