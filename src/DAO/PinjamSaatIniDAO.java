///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package DAO;
//import DatabaseConnection.DatabaseConnection;
//import java.sql.*;
//import Models.User;
//
//
///**
// *
// * @author USER
// */
//public class PinjamSaatIniDAO {
//    private Connection conn = DatabaseConnection.getConnection();
//    
//    public User login(String username, String password) {
//        String sql = "SELECT * FROM user WHERE username=? AND password=?";
//        
//        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            
//            ResultSet rs = stmt.executeQuery();
//            if(rs.next()) {
//                return new User(
//                    rs.getString("username"),
//                    rs.getString("password")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        };
//        
//        return null;
//    };
//    
//    public boolean isUsernameExists(String username) {
//    String sql = "SELECT * FROM user WHERE username = ?";
//    
//    try (Connection conn = DatabaseConnection.getConnection();
//         PreparedStatement pst = conn.prepareStatement(sql)) {
//
//        pst.setString(1, username);
//        ResultSet rs = pst.executeQuery();
//
//        return rs.next(); // true jika username ditemukan
//
//    } catch (Exception e) {
//        System.out.println("Error: " + e.getMessage());
//    }
//
//    return false;
//}
//}
