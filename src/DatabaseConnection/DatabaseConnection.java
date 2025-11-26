/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseConnection;

/**
 *
 * @author USER
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

//    private static Connection connection;

    public static Connection getConnection() {
//        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/perpus_sebisaku";
                String user = "root";
                String pass = "";

                Connection conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi Berhasil!");
                return conn;

            } catch (Exception e) {
                System.out.println("Koneksi Gagal!" + e.getMessage());
                return null;
            }
        }
//        return connection;
}