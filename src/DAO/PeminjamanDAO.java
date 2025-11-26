/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import DatabaseConnection.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import Models.PeminjamanSaatIni;


/**
 *
 * @author USER
 */
public class PeminjamanDAO {
    public List<PeminjamanSaatIni> getPeminjamanByUserId(int userId) {
        List<PeminjamanSaatIni> list = new ArrayList<>();

        String sql = """
            SELECT p.pinjam_id, b.judul, p.tanggal_pinjam, p.tanggal_kembali, p.status
            FROM peminjaman p
            JOIN buku b ON p.buku_id = b.buku_id
            WHERE p.user_id = ?
        """;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new PeminjamanSaatIni(
                    rs.getInt("pinjam_id"),
                    rs.getString("judul"),
                    rs.getString("tanggal_pinjam"),
                    rs.getString("tanggal_kembali"),
                    rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<String> getBorrowedBooksByUsername(String username) {
    List<String> books = new ArrayList<>();
    String sql = "SELECT b.judul FROM peminjaman p " +
                 "JOIN buku b ON p.buku_id = b.buku_id " +
                 "JOIN user u ON p.user_id = u.user_id " +
                 "WHERE u.username = ? AND p.status = 'dipinjam'";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            books.add(rs.getString("judul"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return books;
}
    
    public boolean pinjamBuku(String username, String judulBuku) {
    String getUserId = "SELECT user_id FROM user WHERE username = ?";
    String getBookId = "SELECT buku_id FROM buku WHERE judul = ? AND status = 'tersedia'";
    String insertPeminjaman = 
        "INSERT INTO peminjaman (user_id, buku_id, tanggal_pinjam, tanggal_kembali, status, denda) " +
        "VALUES (?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 10 DAY), 'dipinjam', NULL)";
    String updateBookStatus = 
        "UPDATE buku SET status = 'dipinjam' WHERE buku_id = ?";
    
    Connection conn = null;

    try {
        
        conn = DatabaseConnection.getConnection();
        // 1. Ambil user_id
        PreparedStatement stmtUser = conn.prepareStatement(getUserId);
        stmtUser.setString(1, username);
        ResultSet rsUser = stmtUser.executeQuery();

        if (!rsUser.next()) {
            return false; // Username tidak valid
        }

        int userId = rsUser.getInt("user_id");

        // 2. Ambil buku_id
        PreparedStatement stmtBook = conn.prepareStatement(getBookId);
        stmtBook.setString(1, judulBuku);
        ResultSet rsBook = stmtBook.executeQuery();

        if (!rsBook.next()) {
            return false; // Buku tidak tersedia
        }

        int bookId = rsBook.getInt("buku_id");

        // 3. Insert ke tabel peminjaman
        PreparedStatement stmtInsert = conn.prepareStatement(insertPeminjaman);
        stmtInsert.setInt(1, userId);
        stmtInsert.setInt(2, bookId);
        stmtInsert.executeUpdate();

        // 4. Update status buku
        PreparedStatement stmtUpdate = conn.prepareStatement(updateBookStatus);
        stmtUpdate.setInt(1, bookId);
        stmtUpdate.executeUpdate();

        return true;

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}


    public boolean kembalikanBuku(String username, String judulBuku) {
    try (Connection conn = DatabaseConnection.getConnection()) {

        // 1. Dapatkan user_id
        String qUser = "SELECT user_id FROM user WHERE username = ?";
        PreparedStatement psUser = conn.prepareStatement(qUser);
        psUser.setString(1, username);
        ResultSet rUser = psUser.executeQuery();

        if (!rUser.next()) {
            return false; // user tidak ditemukan
        }

        int userId = rUser.getInt("user_id");

        // 2. Dapatkan buku_id
        String qBook = "SELECT buku_id FROM buku WHERE judul = ?";
        PreparedStatement psBook = conn.prepareStatement(qBook);
        psBook.setString(1, judulBuku);
        ResultSet rBook = psBook.executeQuery();

        if (!rBook.next()) {
            return false; // buku tidak ditemukan
        }

        int bukuId = rBook.getInt("buku_id");

        // 3. Update peminjaman → status dikembalikan
        String qUpdatePinjam = 
            "UPDATE peminjaman SET status = 'dikembalikan', tanggal_kembali = NOW() " +
            "WHERE user_id = ? AND buku_id = ? AND status = 'dipinjam'";

        PreparedStatement psUpdatePinjam = conn.prepareStatement(qUpdatePinjam);
        psUpdatePinjam.setInt(1, userId);
        psUpdatePinjam.setInt(2, bukuId);

        int updated1 = psUpdatePinjam.executeUpdate();

        if (updated1 == 0) {
            return false; // tidak ada data dipinjam
        }

        // 4. Update status buku → Tersedia
        String qUpdateBook = 
            "UPDATE buku SET status = 'Tersedia' WHERE buku_id = ?";

        PreparedStatement psUpdateBook = conn.prepareStatement(qUpdateBook);
        psUpdateBook.setInt(1, bukuId);
        psUpdateBook.executeUpdate();

        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}