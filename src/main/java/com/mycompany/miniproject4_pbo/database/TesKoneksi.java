
package com.mycompany.miniproject4_pbo.database;

import java.sql.Connection;
import java.sql.SQLException;

public class TesKoneksi {

    public static void main(String[] args) {
        try (Connection koneksi = DatabaseConnection.getConnection()) {
            if (koneksi != null && !koneksi.isClosed()) {
                System.out.println("Koneksi database berhasil!");
                System.out.println("Database: "
                        + koneksi.getCatalog());
            }
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal!");
            System.out.println("Pesan error: " + e.getMessage());
        }
    }
}
