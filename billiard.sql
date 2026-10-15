
CREATE DATABASE IF NOT EXISTS db_billiard_minpro4;
USE db_billiard_minpro4;

CREATE TABLE IF NOT EXISTS meja_billiard (
    nomor_meja INT PRIMARY KEY,
    jenis_meja VARCHAR(20) NOT NULL,
    harga_per_jam DECIMAL(12,2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Tersedia'
);

CREATE TABLE IF NOT EXISTS transaksi (
    id_transaksi INT AUTO_INCREMENT PRIMARY KEY,
    nomor_meja INT NOT NULL,
    nama_pelanggan VARCHAR(100) NOT NULL,
    durasi_jam INT NOT NULL,
    total_bayar DECIMAL(12,2) NOT NULL,
    status_transaksi VARCHAR(20) NOT NULL DEFAULT 'Aktif',
    tanggal_transaksi TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT IGNORE INTO meja_billiard
    (nomor_meja, jenis_meja, harga_per_jam, status)
VALUES
    (1, 'Reguler', 30000, 'Tersedia'),
    (2, 'VIP', 60000, 'Tersedia');

SELECT * FROM meja_billiard;
SELECT * FROM transaksi;
