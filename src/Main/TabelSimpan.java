/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author USER
 */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import DatabaseConnection.DatabaseConnection;

public class TabelSimpan extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public TabelSimpan() {
        setLayout(new BorderLayout());
        
        // Kolom tabel
        String[] kolom = {
                "ID", "Judul", "Penulis", "Deskripsi", "Status"
        };

        // Model tabel
        model = new DefaultTableModel(kolom, 0);
        table = new JTable(model);

        // Tinggi baris
        table.setRowHeight(25);

        // ================================
        // 1️⃣ MENAMBAHKAN GARIS KOLOM & BARIS
        // ================================
        table.setShowGrid(true);
        table.setGridColor(Color.GRAY);

        // ================================
        // 2️⃣ MENGUBAH WARNA HEADER KOLOM
        // ================================
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(230, 230, 230)); // abu muda
        header.setForeground(Color.BLACK);
        header.setFont(new Font("SansSerif", Font.BOLD, 12));

        // ================================
        // 3️⃣ MENGUBAH WARNA ISI TABEL
        // ================================
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

        // (opsional) warna saat baris dipilih
        table.setSelectionBackground(new Color(200, 200, 255));
        table.setSelectionForeground(Color.BLACK);

        // ================================
        // 4️⃣ MENGATUR LEBAR KHUSUS UNTUK KOLOM "No"
        // ================================
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(0).setMaxWidth(50);
        
        columnModel.getColumn(3).setPreferredWidth(550);
        columnModel.getColumn(3).setMaxWidth(550);

        // ================================
        // Scroll Pane
        // ================================
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        if(!java.beans.Beans.isDesignTime()) {
            loadData();
        }
    }
    
    public void loadData() {
        model.setRowCount(0); // hapus data lama

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = "SELECT buku_id, judul, penulis, deskripsi, status FROM buku";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("buku_id"),
                    rs.getString("judul"),
                    rs.getString("penulis"),
                    rs.getString("deskripsi"),
                    rs.getString("status")
                };

                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Gagal memuat data: " + e.getMessage());
        }
    }

    public JTable getTable() {
        return table;
    }
}