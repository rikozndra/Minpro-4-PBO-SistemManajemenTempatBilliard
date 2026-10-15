
package com.mycompany.miniproject4_pbo.dao;

import com.mycompany.miniproject4_pbo.database.DatabaseConnection;
import com.mycompany.miniproject3_pbo.model.Transaksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    // CREATE: menyimpan transaksi baru
    public boolean tambahTransaksi(Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO transaksi "
                + "(nomor_meja, nama_pelanggan, durasi_jam, "
                + "total_bayar, status_transaksi) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, transaksi.getNomorMeja());
            ps.setString(2, transaksi.getNamaPelanggan());
            ps.setInt(3, transaksi.getDurasiJam());
            ps.setDouble(4, transaksi.getTotalBayar());
            ps.setString(5, transaksi.getStatusTransaksi());

            return ps.executeUpdate() > 0;
        }
    }

    // READ: mencari transaksi aktif berdasarkan nomor meja
    public Transaksi cariTransaksiAktif(int nomorMeja) throws SQLException {
        String sql = "SELECT * FROM transaksi "
                + "WHERE nomor_meja = ? AND status_transaksi = 'Aktif' "
                + "ORDER BY id_transaksi DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, nomorMeja);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Transaksi(
                            rs.getInt("id_transaksi"),
                            rs.getInt("nomor_meja"),
                            rs.getString("nama_pelanggan"),
                            rs.getInt("durasi_jam"),
                            rs.getDouble("total_bayar")
                    );
                }
            }
        }

        return null;
    }

    // UPDATE: memperbarui durasi dan biaya transaksi
    public boolean perbaruiTransaksi(Transaksi transaksi) throws SQLException {
        String sql = "UPDATE transaksi "
                + "SET durasi_jam = ?, total_bayar = ? "
                + "WHERE id_transaksi = ? AND status_transaksi = 'Aktif'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, transaksi.getDurasiJam());
            ps.setDouble(2, transaksi.getTotalBayar());
            ps.setInt(3, transaksi.getIdTransaksi());

            return ps.executeUpdate() > 0;
        }
    }

    // UPDATE: menandai transaksi selesai
    public boolean selesaikanTransaksi(int idTransaksi) throws SQLException {
        String sql = "UPDATE transaksi SET status_transaksi = 'Selesai' "
                + "WHERE id_transaksi = ? AND status_transaksi = 'Aktif'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTransaksi);
            return ps.executeUpdate() > 0;
        }
    }

    // READ: mengambil seluruh riwayat transaksi
    public List<Transaksi> getRiwayatTransaksi() throws SQLException {
        List<Transaksi> daftarTransaksi = new ArrayList<>();

        String sql = "SELECT * FROM transaksi ORDER BY id_transaksi";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Transaksi transaksi = new Transaksi(
                        rs.getInt("id_transaksi"),
                        rs.getInt("nomor_meja"),
                        rs.getString("nama_pelanggan"),
                        rs.getInt("durasi_jam"),
                        rs.getDouble("total_bayar")
                );

                transaksi.setStatusTransaksi(
                        rs.getString("status_transaksi")
                );

                daftarTransaksi.add(transaksi);
            }
        }

        return daftarTransaksi;
    }
}
