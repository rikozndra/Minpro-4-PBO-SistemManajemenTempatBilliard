
package com.mycompany.miniproject3_pbo.controller;

import com.mycompany.miniproject3_pbo.model.MejaBilliard;
import com.mycompany.miniproject3_pbo.model.MejaReguler;
import com.mycompany.miniproject3_pbo.model.MejaVIP;
import com.mycompany.miniproject3_pbo.model.Transaksi;
import com.mycompany.miniproject4_pbo.dao.MejaDAO;
import com.mycompany.miniproject4_pbo.dao.TransaksiDAO;

import java.sql.SQLException;
import java.util.List;

public class BilliardController {

    private final MejaDAO mejaDAO;
    private final TransaksiDAO transaksiDAO;

    public BilliardController() {
        mejaDAO = new MejaDAO();
        transaksiDAO = new TransaksiDAO();
    }

    // CREATE
    public boolean tambahMeja(int nomorMeja, String jenisMeja) {
        if (nomorMeja <= 0) {
            return false;
        }

        try {
            for (MejaBilliard meja : mejaDAO.getAllMeja()) {
                if (meja.getNomorMeja() == nomorMeja) {
                    return false;
                }
            }

            MejaBilliard meja = buatMeja(nomorMeja, jenisMeja);

            if (meja == null) {
                return false;
            }

            return mejaDAO.tambahMeja(meja);

        } catch (SQLException e) {
            tampilkanError("menambahkan meja", e);
            return false;
        }
    }

    // READ
    public void tampilkanMeja() {
        try {
            List<MejaBilliard> daftarMeja = mejaDAO.getAllMeja();

            if (daftarMeja.isEmpty()) {
                System.out.println("Belum ada data meja.");
                return;
            }

            System.out.println("\n========== DATA MEJA ==========");

            for (MejaBilliard meja : daftarMeja) {
                System.out.println("Nomor Meja : " + meja.getNomorMeja());
                System.out.println("Jenis Meja : " + meja.getJenisMeja());
                System.out.println("Harga/Jam  : Rp" + meja.getHargaPerJam());
                System.out.println("Status     : " + meja.getStatus());
                System.out.println("-------------------------------");
            }

        } catch (SQLException e) {
            tampilkanError("menampilkan data meja", e);
        }
    }

    // UPDATE
    public boolean ubahMeja(int nomorMeja, String jenisBaru) {
        try {
            MejaBilliard mejaLama = cariMeja(nomorMeja);

            if (mejaLama == null
                    || !mejaLama.getStatus().equals("Tersedia")) {
                return false;
            }

            MejaBilliard mejaBaru = buatMeja(nomorMeja, jenisBaru);

            if (mejaBaru == null) {
                return false;
            }

            mejaBaru.setStatus(mejaLama.getStatus());
            return mejaDAO.ubahMeja(mejaBaru);

        } catch (SQLException e) {
            tampilkanError("mengubah data meja", e);
            return false;
        }
    }

    // DELETE
    public boolean hapusMeja(int nomorMeja) {
        try {
            MejaBilliard meja = cariMeja(nomorMeja);

            if (meja == null
                    || !meja.getStatus().equals("Tersedia")) {
                return false;
            }

            return mejaDAO.hapusMeja(nomorMeja);

        } catch (SQLException e) {
            tampilkanError("menghapus meja", e);
            return false;
        }
    }

    // CEK KETERSEDIAAN
    public boolean mejaBisaDibuka(int nomorMeja) {
        try {
            MejaBilliard meja = cariMeja(nomorMeja);
            return meja != null && meja.dapatDisewa();

        } catch (SQLException e) {
            tampilkanError("memeriksa ketersediaan meja", e);
            return false;
        }
    }

    // BUKA MEJA
    public boolean bukaMeja(
            int nomorMeja, String namaPelanggan, int durasiJam) {

        if (namaPelanggan == null
                || namaPelanggan.trim().isEmpty()
                || durasiJam <= 0) {
            return false;
        }

        try {
            MejaBilliard meja = cariMeja(nomorMeja);

            if (meja == null || !meja.dapatDisewa()) {
                return false;
            }

            double totalBayar = hitungTotal(meja, durasiJam);

            // ID sementara 0; ID sebenarnya dibuat oleh database.
            Transaksi transaksi = new Transaksi(
                    0,
                    nomorMeja,
                    namaPelanggan.trim(),
                    durasiJam,
                    totalBayar
            );

            if (!transaksiDAO.tambahTransaksi(transaksi)) {
                return false;
            }

            return mejaDAO.ubahStatusMeja(nomorMeja, "Digunakan");

        } catch (SQLException e) {
            tampilkanError("membuka meja", e);
            return false;
        }
    }

    // TAMBAH WAKTU
    public boolean tambahWaktu(int nomorMeja, int tambahanJam) {
        if (tambahanJam <= 0) {
            return false;
        }

        try {
            MejaBilliard meja = cariMeja(nomorMeja);

            if (meja == null
                    || !meja.getStatus().equals("Digunakan")) {
                return false;
            }

            Transaksi transaksi =
                    transaksiDAO.cariTransaksiAktif(nomorMeja);

            if (transaksi == null) {
                return false;
            }

            int durasiBaru =
                    transaksi.getDurasiJam() + tambahanJam;

            transaksi.setDurasiJam(durasiBaru);
            transaksi.setTotalBayar(hitungTotal(meja, durasiBaru));

            return transaksiDAO.perbaruiTransaksi(transaksi);

        } catch (SQLException e) {
            tampilkanError("menambahkan waktu", e);
            return false;
        }
    }

    // TUTUP MEJA
    public Transaksi tutupMeja(int nomorMeja) {
        try {
            MejaBilliard meja = cariMeja(nomorMeja);

            if (meja == null
                    || !meja.getStatus().equals("Digunakan")) {
                return null;
            }

            Transaksi transaksi =
                    transaksiDAO.cariTransaksiAktif(nomorMeja);

            if (transaksi == null) {
                return null;
            }

            if (!transaksiDAO.selesaikanTransaksi(
                    transaksi.getIdTransaksi())) {
                return null;
            }

            if (!mejaDAO.ubahStatusMeja(nomorMeja, "Tersedia")) {
                System.out.println(
                        "Peringatan: status meja gagal diperbarui."
                );
            }

            transaksi.setStatusTransaksi("Selesai");
            return transaksi;

        } catch (SQLException e) {
            tampilkanError("menutup meja", e);
            return null;
        }
    }

    // RIWAYAT TRANSAKSI
    public void tampilkanRiwayatTransaksi() {
        try {
            List<Transaksi> daftarTransaksi =
                    transaksiDAO.getRiwayatTransaksi();

            if (daftarTransaksi.isEmpty()) {
                System.out.println("Belum ada riwayat transaksi.");
                return;
            }

            System.out.println("\n======= RIWAYAT TRANSAKSI =======");

            for (Transaksi transaksi : daftarTransaksi) {
                System.out.println("ID Transaksi : "
                        + transaksi.getIdTransaksi());
                System.out.println("Nomor Meja   : "
                        + transaksi.getNomorMeja());
                System.out.println("Pelanggan    : "
                        + transaksi.getNamaPelanggan());
                System.out.println("Durasi       : "
                        + transaksi.getDurasiJam() + " jam");
                System.out.println("Total Bayar  : Rp"
                        + transaksi.getTotalBayar());
                System.out.println("Status       : "
                        + transaksi.getStatusTransaksi());
                System.out.println("--------------------------------");
            }

        } catch (SQLException e) {
            tampilkanError("menampilkan riwayat transaksi", e);
        }
    }

    // MENGHITUNG BIAYA DAN DISKON VIP
    private double hitungTotal(MejaBilliard meja, int durasiJam) {
        if (meja.getJenisMeja().equalsIgnoreCase("VIP")) {
            return meja.hitungBiaya(durasiJam, 10);
        }

        return meja.hitungBiaya(durasiJam);
    }

    // MEMBUAT OBJEK MEJA
    private MejaBilliard buatMeja(
            int nomorMeja, String jenisMeja) {

        if (jenisMeja == null) {
            return null;
        }

        if (jenisMeja.equalsIgnoreCase("Reguler")) {
            return new MejaReguler(nomorMeja);
        }

        if (jenisMeja.equalsIgnoreCase("VIP")) {
            return new MejaVIP(nomorMeja);
        }

        return null;
    }

    // MENCARI MEJA DI DATABASE
    private MejaBilliard cariMeja(int nomorMeja)
            throws SQLException {

        for (MejaBilliard meja : mejaDAO.getAllMeja()) {
            if (meja.getNomorMeja() == nomorMeja) {
                return meja;
            }
        }

        return null;
    }

    private void tampilkanError(String operasi, SQLException e) {
        System.out.println("Gagal " + operasi + ".");
        System.out.println("Pesan database: " + e.getMessage());
    }
}
