/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author USER
 */
public class PeminjamanSaatIni {
    private int pinjamId;
    private String judul;
    private String tglPinjam;
    private String tglKembali;
    private String status;

    public PeminjamanSaatIni(int pinjamId, String judul, String tglPinjam, String tglKembali, String status) {
        this.pinjamId = pinjamId;
        this.judul = judul;
        this.tglPinjam = tglPinjam;
        this.tglKembali = tglKembali;
        this.status = status;
    }

    public int getPinjamId() { return pinjamId; }
    public String getJudul() { return judul; }
    public String getTglPinjam() { return tglPinjam; }
    public String getTglKembali() { return tglKembali; }
    public String getStatus() { return status; }
}
