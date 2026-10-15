
package com.mycompany.miniproject4_pbo.dao;

import com.mycompany.miniproject3_pbo.model.MejaBilliard;
import java.sql.SQLException;
import java.util.List;

public class TestMejaDAO {

    public static void main(String[] args) {
        MejaDAO mejaDAO = new MejaDAO();

        try {
            List<MejaBilliard> daftarMeja = mejaDAO.getAllMeja();

            System.out.println("===== DATA MEJA DARI DATABASE =====");

            if (daftarMeja.isEmpty()) {
                System.out.println("Belum ada data meja.");
            }

            for (MejaBilliard meja : daftarMeja) {
                System.out.println("Nomor Meja : " + meja.getNomorMeja());
                System.out.println("Jenis Meja : " + meja.getJenisMeja());
                System.out.println("Harga/Jam  : Rp" + meja.getHargaPerJam());
                System.out.println("Status     : " + meja.getStatus());
                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Gagal membaca data meja.");
            System.out.println("Pesan error: " + e.getMessage());
        }
    }
}
