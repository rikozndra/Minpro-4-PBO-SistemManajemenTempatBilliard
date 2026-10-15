
package com.mycompany.miniproject4_pbo.dao;

import com.mycompany.miniproject4_pbo.database.DatabaseConnection;
import com.mycompany.miniproject3_pbo.model.MejaBilliard;
import com.mycompany.miniproject3_pbo.model.MejaReguler;
import com.mycompany.miniproject3_pbo.model.MejaVIP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MejaDAO {

    // READ: mengambil seluruh data meja dari database
    public List<MejaBilliard> getAllMeja() throws SQLException {
        List<MejaBilliard> daftarMeja = new ArrayList<>();

        String sql = "SELECT nomor_meja, jenis_meja, harga_per_jam, status "
                   + "FROM meja_billiard ORDER BY nomor_meja";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int nomor = rs.getInt("nomor_meja");
                String jenis = rs.getString("jenis_meja");
                String status = rs.getString("status");

                MejaBilliard meja;

                if (jenis.equalsIgnoreCase("Reguler")) {
                    meja = new MejaReguler(nomor);
                } else if (jenis.equalsIgnoreCase("VIP")) {
                    meja = new MejaVIP(nomor);
                } else {
                    continue;
                }

                meja.setStatus(status);
                daftarMeja.add(meja);
            }
        }

        return daftarMeja;
    }

    // CREATE: menambahkan meja ke database
    public boolean tambahMeja(MejaBilliard meja) throws SQLException {
        String sql = "INSERT INTO meja_billiard "
                   + "(nomor_meja, jenis_meja, harga_per_jam, status) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, meja.getNomorMeja());
            ps.setString(2, meja.getJenisMeja());
            ps.setDouble(3, meja.getHargaPerJam());
            ps.setString(4, meja.getStatus());

            return ps.executeUpdate() > 0;
        }
    }

    // UPDATE: mengubah jenis, harga, dan status meja
    public boolean ubahMeja(MejaBilliard meja) throws SQLException {
        String sql = "UPDATE meja_billiard "
                   + "SET jenis_meja = ?, harga_per_jam = ?, status = ? "
                   + "WHERE nomor_meja = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, meja.getJenisMeja());
            ps.setDouble(2, meja.getHargaPerJam());
            ps.setString(3, meja.getStatus());
            ps.setInt(4, meja.getNomorMeja());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE: menghapus meja berdasarkan nomor
    public boolean hapusMeja(int nomorMeja) throws SQLException {
        String sql = "DELETE FROM meja_billiard WHERE nomor_meja = ? "
                   + "AND status = 'Tersedia'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, nomorMeja);
            return ps.executeUpdate() > 0;
        }
    }
    
    // UPDATE: mengubah status meja
    public boolean ubahStatusMeja(int nomorMeja, String status)
            throws SQLException {

        String sql = "UPDATE meja_billiard SET status = ? "
                   + "WHERE nomor_meja = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, nomorMeja);

            return ps.executeUpdate() > 0;
        }
    }
}
