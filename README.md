# MINI PROJECT 4 PBO
## SISTEM MANAJEMEN TEMPAT BILLIARD

**Nama:** Riko Azkarazendra Russianto  
**NIM:** 2509116104  
**Kelas:** C-2025  
**Mata Kuliah:** Pemrograman Berorientasi Objek (PBO)

# 1. Deskripsi Program

Sistem Manajemen Tempat Billiard merupakan program berbasis Java Console yang digunakan untuk mengelola data meja billiard dan transaksi pelanggan.

Program ini merupakan pengembangan dari Mini Project sebelumnya dengan menerapkan konsep Pemrograman Berorientasi Objek (PBO) secara lebih terstruktur.

Pada Mini Project 4, fitur dan konsep PBO dari versi sebelumnya tetap dipertahankan. Pengembangan utama pada versi ini adalah integrasi database MySQL menggunakan JDBC, pemisahan akses data melalui DAO, serta penggunaan `PreparedStatement` untuk menjalankan perintah SQL.

- Encapsulation
- Inheritance
- Polymorphism
- Abstraction
- Interface
- JDBC (Java Database Connectivity)
- MySQL sebagai penyimpanan data permanen
- DAO (Data Access Object)
- PreparedStatement
- Input Validation
- Model-View-Controller (MVC)

Program digunakan untuk mengelola meja billiard, transaksi pelanggan, durasi bermain, pembayaran, status meja, serta riwayat transaksi. Berbeda dari versi sebelumnya yang menyimpan data selama program berjalan menggunakan `ArrayList`, Minpro 4 menyimpan data meja dan transaksi ke database MySQL sehingga data tetap tersedia setelah program ditutup.

## Fitur Program

Program memiliki beberapa fitur utama:

- Menambahkan data meja.
- Menampilkan data meja.
- Mengubah jenis meja.
- Menghapus data meja.
- Membuka meja untuk pelanggan.
- Menambahkan waktu bermain.
- Menutup meja.
- Menampilkan struk pembayaran.
- Menampilkan riwayat transaksi.
- Mengubah status transaksi dari `Aktif` menjadi `Selesai`.
- Diskon 10% untuk meja VIP.
- Validasi input pengguna.
- Mencegah nomor meja duplikat.
- Mencegah meja yang sedang digunakan untuk diubah.
- Mencegah meja yang sedang digunakan untuk dihapus.
- Mencegah meja yang sedang digunakan untuk dibuka kembali.

## Jenis Meja

| Jenis Meja | Harga per Jam |
|---|---:|
| Reguler | Rp30.000 |
| VIP | Rp60.000 |

Meja VIP mendapatkan diskon sebesar 10% dari total biaya.

---

# 2. Tujuan Program

Program ini dibuat untuk menerapkan konsep Pemrograman Berorientasi Objek dalam sebuah sistem sederhana yang dapat digunakan untuk mengelola tempat billiard.

Tujuan program:

1. Mengelola data meja billiard.
2. Mengelola transaksi pelanggan.
3. Menghitung biaya berdasarkan durasi penggunaan meja.
4. Menerapkan diskon pada meja VIP.
5. Menyimpan riwayat transaksi.
6. Menerapkan konsep Pemrograman Berorientasi Objek.
7. Menerapkan struktur MVC.
8. Menerapkan abstraction dan polymorphism.
9. Menerapkan interface sebagai pengembangan tambahan.
10. Menerapkan validasi untuk mengurangi kesalahan input pengguna.

---

# 3. Struktur Package Program

Program menggunakan struktur Model-View-Controller (MVC) untuk memisahkan bagian model, view, dan controller.

```text
src/main/java
├── com.mycompany.miniproject3_pbo
│   ├── Miniproject3_PBO.java
│   ├── controller
│   │   └── BilliardController.java
│   ├── model
│   │   ├── MejaBilliard.java
│   │   ├── MejaReguler.java
│   │   ├── MejaVIP.java
│   │   ├── Transaksi.java
│   │   └── DapatDisewa.java
│   └── view
│       └── Menu.java
└── com.mycompany.miniproject4_pbo
    ├── dao
    │   ├── MejaDAO.java
    │   └── TransaksiDAO.java
    └── database
        └── DatabaseConnection.java
```

## Penjelasan Package

### Model

Package `model` berisi class yang merepresentasikan objek dan data yang digunakan dalam sistem.

- `MejaBilliard.java`  
  Abstract class yang menjadi dasar dari seluruh jenis meja billiard.

- `MejaReguler.java`  
  Subclass untuk meja jenis Reguler dengan harga Rp30.000 per jam.

- `MejaVIP.java`  
  Subclass untuk meja jenis VIP dengan harga Rp60.000 per jam.

- `Transaksi.java`  
  Menyimpan informasi transaksi pelanggan.

- `DapatDisewa.java`  
  Interface yang mendefinisikan fungsi yang berkaitan dengan kemampuan meja untuk disewa.

### View

Package `view` berisi class yang berhubungan dengan tampilan dan input pengguna.

- `Menu.java`  
  Menampilkan menu utama, menerima input pengguna, dan melakukan validasi input.

### Controller

Package `controller` berisi proses utama sistem.

- `BilliardController.java`  
  Mengatur proses CRUD meja, transaksi, perhitungan biaya, status meja, dan riwayat transaksi.

### Main

- `Miniproject3_PBO.java`  
  Merupakan class utama yang digunakan untuk menjalankan program.

## Screenshot Struktur Package

<img width="258" height="417" alt="image" src="https://github.com/user-attachments/assets/1f2445d9-828d-4399-bad2-ec3413dced6a" />

**Penjelasan:**

Screenshot menunjukkan struktur project yang mempertahankan package Model, View, dan Controller dari versi sebelumnya, serta menambahkan package `dao` dan `database` untuk kebutuhan koneksi dan pengelolaan data MySQL.

---

# 4. Alur Program

Alur program secara keseluruhan adalah sebagai berikut:

1. Program dijalankan melalui `Miniproject3_PBO.java`.
2. Sistem membuat objek `Menu`.
3. `Menu` membuat objek `BilliardController`.
4. Controller menggunakan `MejaDAO` dan `TransaksiDAO` untuk berkomunikasi dengan database.
5. Data awal meja dibaca dari database MySQL yang telah diisi menggunakan file `billiard.sql`.
6. Sistem menampilkan menu utama.
7. Pengguna memilih menu yang tersedia.
8. Sistem memproses pilihan pengguna.
9. Jika pengguna memilih CRUD, sistem melakukan proses sesuai pilihan.
10. Jika pengguna membuka meja, sistem memeriksa ketersediaan meja.
11. Pengguna memasukkan nama pelanggan dan durasi bermain.
12. Sistem menghitung total biaya.
13. Jika meja VIP, sistem memberikan diskon 10%.
14. Transaksi disimpan dengan status `Aktif`.
15. Pengguna dapat menambahkan waktu bermain.
16. Sistem menghitung kembali durasi dan total pembayaran.
17. Ketika meja ditutup, status meja berubah menjadi `Tersedia`.
18. Status transaksi berubah dari `Aktif` menjadi `Selesai`.
19. Transaksi yang telah selesai tetap disimpan sebagai riwayat.
20. Pengguna dapat melihat seluruh riwayat transaksi.
21. Program kembali ke menu utama.
22. Program berhenti ketika pengguna memilih menu `9. Keluar`.

## Diagram Alur Sederhana

```text
Mulai
  │
  ▼
Miniproject3_PBO
  │
  ▼
Menu Utama
  │
  ├── Tambah Meja
  ├── Tampilkan Data
  ├── Ubah Meja
  ├── Hapus Meja
  │
  ├── Buka Meja
  │      │
  │      ▼
  │   Transaksi Aktif
  │      │
  │      ├── Tambah Waktu
  │      │
  │      ▼
  │   Tutup Meja
  │      │
  │      ▼
  │   Transaksi Selesai
  │      │
  │      ▼
  │   Riwayat Transaksi
  │
  └── Keluar
          │
          ▼
        Selesai
```

## Screenshot Menu Utama

<img width="177" height="168" alt="image" src="https://github.com/user-attachments/assets/007fe238-8af0-4cf3-9bd2-6b14c097181c" />

**Penjelasan:**

Menu utama menyediakan seluruh fungsi sistem mulai dari pengelolaan meja, transaksi, riwayat transaksi, hingga keluar dari program.

---

# 5. Menu Program

Program memiliki menu utama:

```text
==============================
   SISTEM MANAJEMEN BILLIARD
==============================
1. Tambah Meja
2. Tampilkan Data Meja
3. Ubah Data Meja
4. Hapus Meja
5. Buka Meja
6. Tambah Waktu
7. Tutup Meja
8. Riwayat Transaksi
9. Keluar
==============================
```

| Menu | Fungsi |
|---|---|
| 1 | Menambahkan meja baru |
| 2 | Menampilkan seluruh data meja |
| 3 | Mengubah jenis meja |
| 4 | Menghapus meja |
| 5 | Membuka meja untuk pelanggan |
| 6 | Menambahkan waktu bermain |
| 7 | Menutup meja dan menyelesaikan transaksi |
| 8 | Menampilkan riwayat transaksi |
| 9 | Keluar dari program |

---

# 6. Implementasi CRUD

## 6.1 Create - Menambah Data Meja

Fitur Create digunakan untuk menambahkan meja baru.

Pengguna memasukkan:

- Nomor meja.
- Jenis meja.

Jenis meja yang dapat dipilih:

```text
Reguler
VIP
```

Sistem melakukan pemeriksaan agar nomor meja tidak boleh sama.

Contoh:

```text
--- TAMBAH MEJA ---
Nomor meja: 2
Jenis meja (Reguler/VIP): VIP
Meja berhasil ditambahkan.
```

Pada Minpro 4, data meja disimpan ke tabel `meja_billiard` melalui `MejaDAO`, bukan hanya di dalam `ArrayList`.

## Screenshot Create

<img width="177" height="235" alt="image" src="https://github.com/user-attachments/assets/691e38ef-ce03-4a16-9c9d-7aaa27ceb35b" />

**Penjelasan:**

Screenshot menunjukkan bahwa pengguna berhasil menambahkan meja baru. Sistem juga melakukan validasi agar nomor meja tidak boleh digunakan lebih dari satu kali.

---

## 6.2 Read - Menampilkan Data Meja

Fitur Read digunakan untuk menampilkan seluruh data meja yang tersimpan.

Informasi yang ditampilkan:

- Nomor meja.
- Jenis meja.
- Harga per jam.
- Status meja.

Contoh:

```text
========== DATA MEJA ==========

Nomor Meja : 1
Jenis Meja : Reguler
Harga/Jam  : Rp30000.0
Status     : Tersedia
-------------------------------

Nomor Meja : 2
Jenis Meja : VIP
Harga/Jam  : Rp60000.0
Status     : Tersedia
-------------------------------
```

## Screenshot Read

<img width="157" height="272" alt="image" src="https://github.com/user-attachments/assets/ed245042-278e-438c-a7bd-762eccdf9b8d" />

**Penjelasan:**

Screenshot menunjukkan data nomor meja, jenis meja, harga per jam, dan status meja yang tersimpan dalam sistem.

---

## 6.3 Update - Mengubah Data Meja

Fitur Update digunakan untuk mengubah jenis meja.

Contoh:

```text
--- UBAH DATA MEJA ---
Nomor meja yang ingin diubah: 1
Jenis meja (Reguler/VIP): VIP
Data meja berhasil diubah.
```

Jika sebelumnya meja merupakan Reguler, maka setelah diubah menjadi VIP harga per jam juga berubah.

Meja yang sedang digunakan tidak dapat diubah.

Contoh:

```text
Gagal mengubah data. Meja tidak ditemukan atau sedang digunakan.
```

## Screenshot Update

<img width="157" height="200" alt="image" src="https://github.com/user-attachments/assets/50c280e0-27c1-4963-a7d2-8267715b4822" />

**Penjelasan:**

Screenshot menunjukkan bahwa jenis meja berhasil diubah dan harga per jam menyesuaikan jenis meja yang baru.

---

## 6.4 Delete - Menghapus Data Meja

Fitur Delete digunakan untuk menghapus data meja.

Meja hanya dapat dihapus apabila:

- Meja ditemukan.
- Meja berstatus `Tersedia`.

Contoh:

```text
--- HAPUS MEJA ---
Nomor meja yang ingin dihapus: 2
Meja berhasil dihapus.
```

Jika meja sedang digunakan:

```text
Meja tidak ditemukan atau sedang digunakan.
```

Hal ini dilakukan agar meja yang sedang memiliki transaksi tidak dapat dihapus.

## Screenshot Delete

<img width="157" height="191" alt="image" src="https://github.com/user-attachments/assets/8bb5d266-9459-43f3-9de0-1686774d3b4b" />

<img width="161" height="263" alt="image" src="https://github.com/user-attachments/assets/49ec63d5-1635-4bb3-b60f-33aa4c8c9bb5" />

**Penjelasan:**

Screenshot menunjukkan bahwa meja yang masih tersedia berhasil dihapus dari daftar meja.

---

# 7. Fitur Transaksi

## 7.1 Membuka Meja

Fitur Buka Meja digunakan untuk membuat transaksi pelanggan.

Pengguna memasukkan:

- Nomor meja.
- Nama pelanggan.
- Durasi awal.

Sebelum transaksi dibuat, sistem memeriksa apakah meja tersedia.

Jika berhasil, status meja berubah:

```text
Tersedia → Digunakan
```

Dan transaksi dibuat dengan status:

```text
Aktif
```

Contoh:

```text
--- BUKA MEJA ---
Nomor meja: 2
Nama pelanggan: Riko
Durasi awal (jam): 3
Meja berhasil dibuka.
```

## Screenshot Buka Meja

> **Tambahkan screenshot terbaru ketika meja berhasil dibuka.**

<img width="151" height="284" alt="image" src="https://github.com/user-attachments/assets/a6ec8b74-66f9-40ec-89ec-cecee1aa9faa" />

**Penjelasan:**

Setelah meja dibuka, status meja berubah menjadi `Digunakan` dan sistem mencatat transaksi dengan status `Aktif`.

---

# 8. Perhitungan Biaya

## 8.1 Meja Reguler

Meja Reguler memiliki harga:

```text
Rp30.000 / jam
```

Rumus:

```text
Total = Harga per Jam × Durasi
```

Contoh:

```text
Rp30.000 × 3 jam = Rp90.000
```

---

## 8.2 Meja VIP

Meja VIP memiliki harga:

```text
Rp60.000 / jam
```

Meja VIP mendapatkan diskon sebesar 10%.

Contoh:

```text
Harga per jam = Rp60.000
Durasi        = 3 jam

Total awal = Rp60.000 × 3
           = Rp180.000

Diskon 10% = Rp18.000

Total bayar = Rp162.000
```

## Screenshot Perhitungan VIP

<img width="159" height="274" alt="image" src="https://github.com/user-attachments/assets/b29a5c74-d923-473a-b5f8-d6d0939002b2" />

**Penjelasan:**

Screenshot menunjukkan bahwa sistem berhasil menerapkan diskon 10% pada transaksi meja VIP.

---

# 9. Menambahkan Waktu

Fitur Tambah Waktu digunakan ketika pelanggan ingin memperpanjang waktu bermain.

Contoh:

```text
Durasi awal : 2 jam
Tambahan    : 2 jam
Durasi baru : 4 jam
```

Sistem kemudian menghitung ulang total pembayaran.

Contoh untuk meja VIP:

```text
Rp60.000 × 4 jam = Rp240.000

Diskon 10% = Rp24.000

Total bayar = Rp216.000
```

Transaksi yang sedang aktif akan diperbarui:

```text
Durasi
Total pembayaran
```

## Screenshot Tambah Waktu

<img width="152" height="263" alt="image" src="https://github.com/user-attachments/assets/92a32d6b-37ea-4d7d-af0e-fee55f184174" />

<img width="146" height="275" alt="image" src="https://github.com/user-attachments/assets/1464be4c-8fa8-45e5-a33d-830ced508d92" />

**Penjelasan:**

Durasi dan total pembayaran transaksi diperbarui berdasarkan tambahan waktu yang diberikan pengguna.

---

# 10. Menutup Meja

Fitur Tutup Meja digunakan untuk menyelesaikan transaksi.

Ketika meja ditutup, sistem:

1. Mencari meja.
2. Memastikan meja sedang digunakan.
3. Mencari transaksi aktif.
4. Mengubah status meja menjadi `Tersedia`.
5. Mengubah status transaksi menjadi `Selesai`.
6. Menampilkan struk pembayaran.
7. Menyimpan transaksi ke dalam riwayat.

Contoh:

```text
====== STRUK PEMBAYARAN ======
Nama Pelanggan : riko
Nomor Meja     : 1
Durasi         : 4 jam
Total Bayar    : Rp216000.0
Status         : Selesai
===============================
Meja berhasil ditutup.
```

## Screenshot Tutup Meja

<img width="146" height="275" alt="image" src="https://github.com/user-attachments/assets/c0a4bd39-b7f3-4d9a-bd01-cbb0f23d1b92" />

**Penjelasan:**

Setelah transaksi ditutup, status transaksi menjadi `Selesai` dan status meja kembali menjadi `Tersedia`.

---

# 11. Riwayat Transaksi

Riwayat transaksi yang sebelumnya dikelola selama program berjalan kini disimpan secara permanen pada tabel `transaksi` di database MySQL.

Pada Minpro 4, transaksi yang selesai **tidak dihapus dari database**.

Status transaksi diubah:

```text
Aktif → Selesai
```

Transaksi kemudian tetap tersimpan pada tabel `transaksi` dan dapat dibaca kembali menggunakan `TransaksiDAO`.

Fitur ini memungkinkan pengguna melihat transaksi yang telah selesai.

Contoh:

```text
======= RIWAYAT TRANSAKSI =======

ID Transaksi : 1
Nomor Meja   : 2
Pelanggan    : Riko
Durasi       : 3 jam
Total Bayar  : Rp162000.0
Status       : Selesai
--------------------------------
```

Jika pelanggan melakukan transaksi berikutnya, transaksi baru akan mendapatkan ID yang berbeda.

## Screenshot Riwayat Transaksi

<img width="140" height="272" alt="image" src="https://github.com/user-attachments/assets/3534fea9-6c5a-4441-9eaf-ae04357f0b5c" />

**Penjelasan:**

Screenshot menunjukkan bahwa transaksi yang telah selesai tetap tersimpan dan dapat ditampilkan kembali sebagai riwayat transaksi.

---

# 12. Encapsulation

Encapsulation diterapkan dengan menggunakan access modifier `private` pada atribut class.

Contoh pada `MejaBilliard.java`:

```java
private int nomorMeja;
private double hargaPerJam;
private String status;
```

Contoh pada `Transaksi.java`:

```java
private int idTransaksi;
private int nomorMeja;
private String namaPelanggan;
private int durasiJam;
private double totalBayar;
private String statusTransaksi;
```

Atribut tersebut tidak dapat diakses secara langsung dari luar class.

Pengaksesan dilakukan melalui method yang disediakan.

Contoh:

```java
public int getNomorMeja()
```

```java
public double getHargaPerJam()
```

```java
public String getStatus()
```

```java
public void setStatus(String status)
```

## Tujuan Encapsulation

Encapsulation digunakan untuk:

- Melindungi data.
- Mengontrol akses terhadap atribut.
- Menghindari perubahan data secara langsung dari luar class.
- Membuat class lebih terstruktur.

## Letak Implementasi

```text
MejaBilliard.java
Transaksi.java
```
---

# 13. Inheritance

Inheritance diterapkan menggunakan `MejaBilliard` sebagai superclass dan dua subclass.

Struktur inheritance:

```text
             MejaBilliard
              /        \
             /          \
   MejaReguler          MejaVIP
```

Penerapan pada program:

```java
public class MejaReguler extends MejaBilliard
```

```java
public class MejaVIP extends MejaBilliard
```

Subclass mewarisi atribut dan method dari superclass.

Perbedaan subclass terdapat pada:

- Jenis meja.
- Harga per jam.

## Letak Implementasi

```text
Superclass:
MejaBilliard.java

Subclass:
MejaReguler.java
MejaVIP.java
```
---

# 14. Abstraction

Abstraction diterapkan menggunakan abstract class dan abstract method.

Class `MejaBilliard` dibuat sebagai abstract class:

```java
public abstract class MejaBilliard implements DapatDisewa
```

Class tersebut memiliki abstract method:

```java
public abstract String getJenisMeja();
```

Method tersebut tidak memiliki implementasi pada superclass.

Implementasi dilakukan oleh subclass.

Pada `MejaReguler`:

```java
@Override
public String getJenisMeja() {
    return "Reguler";
}
```

Pada `MejaVIP`:

```java
@Override
public String getJenisMeja() {
    return "VIP";
}
```

## Tujuan Abstraction

Abstraction digunakan agar `MejaBilliard` dapat menjadi dasar umum untuk berbagai jenis meja.

Program tidak membuat objek langsung dari `MejaBilliard`, tetapi menggunakan subclass:

```text
MejaReguler
MejaVIP
```

Dengan demikian, abstract class memiliki fungsi nyata dalam struktur program.

## Screenshot Abstraction

<img width="374" height="44" alt="image" src="https://github.com/user-attachments/assets/c80ca0e7-43af-445c-a201-26c12a5f600c" />

<img width="253" height="31" alt="image" src="https://github.com/user-attachments/assets/e19d0cc0-fb8d-45ba-8372-500eebb1585d" />

---

# 15. Polymorphism

Polymorphism pada program diterapkan melalui:

1. Overriding.
2. Overloading.

---

## 15.1 Polymorphism - Overriding

Overriding diterapkan pada method:

```java
getJenisMeja()
```

Pada `MejaReguler`:

```java
@Override
public String getJenisMeja() {
    return "Reguler";
}
```

Pada `MejaVIP`:

```java
@Override
public String getJenisMeja() {
    return "VIP";
}
```

Method memiliki nama yang sama tetapi memberikan hasil yang berbeda sesuai dengan subclass.

Polymorphism juga terlihat pada:

```java
ArrayList<MejaBilliard> daftarMeja;
```

ArrayList tersebut dapat menyimpan objek:

```java
new MejaReguler(1)
```

dan:

```java
new MejaVIP(2)
```

Contoh:

```java
daftarMeja.add(new MejaReguler(1));
daftarMeja.add(new MejaVIP(2));
```

---

## 15.2 Polymorphism - Overloading

Overloading diterapkan pada method `hitungBiaya()` di class `MejaBilliard`.

Method pertama:

```java
public double hitungBiaya(int durasiJam)
```

Method kedua:

```java
public double hitungBiaya(
        int durasiJam,
        double diskonPersen)
```

Kedua method memiliki nama yang sama tetapi parameter berbeda.

### Perhitungan Normal

```java
meja.hitungBiaya(durasiJam);
```

Digunakan untuk menghitung biaya tanpa diskon.

### Perhitungan dengan Diskon

```java
meja.hitungBiaya(durasiJam, 10);
```

Digunakan untuk menghitung biaya dengan diskon 10%.

Dengan demikian, overloading memiliki fungsi dalam proses perhitungan biaya transaksi.

## Screenshot Polymorphism

<img width="220" height="74" alt="image" src="https://github.com/user-attachments/assets/8dec0d4a-57b7-48c9-9fe4-b33722aacb02" />

<img width="235" height="67" alt="image" src="https://github.com/user-attachments/assets/a2dc899d-eec4-4005-b908-297a5117afbf" />

<img width="409" height="279" alt="image" src="https://github.com/user-attachments/assets/789a0e1c-f643-431d-a3bd-5f1601d4cbb1" />

---

# 16. Interface

Interface digunakan sebagai salah satu pengembangan tambahan pada Minpro 3.

Interface yang digunakan:

```text
DapatDisewa.java
```

Isi interface:

```java
public interface DapatDisewa {

    double getHargaSewa();

    boolean dapatDisewa();
}
```

Interface kemudian diimplementasikan oleh:

```java
public abstract class MejaBilliard implements DapatDisewa
```

Implementasi method:

```java
@Override
public double getHargaSewa() {
    return hargaPerJam;
}
```

```java
@Override
public boolean dapatDisewa() {
    return status.equals("Tersedia");
}
```

Method:

```java
dapatDisewa()
```

digunakan oleh controller untuk memeriksa apakah meja dapat digunakan oleh pelanggan.

## Letak Implementasi

```text
Interface:
DapatDisewa.java

Implementasi:
MejaBilliard.java

Penggunaan:
BilliardController.java
```

Interface menjadi nilai tambah karena memberikan kontrak yang jelas untuk pengecekan kemampuan meja untuk disewa.

## Screenshot Interface

<img width="294" height="170" alt="image" src="https://github.com/user-attachments/assets/dbd34838-0a03-42a2-aedd-e7fc598ebfbb" />

---

# 17. Struktur MVC

Program menerapkan pola Model-View-Controller (MVC).

## 17.1 Model

Package:

```text
model
```

Berisi:

```text
MejaBilliard.java
MejaReguler.java
MejaVIP.java
Transaksi.java
DapatDisewa.java
```

Model bertanggung jawab terhadap data dan objek dalam sistem.

---

## 17.2 View

Package:

```text
view
```

Berisi:

```text
Menu.java
```

View bertanggung jawab terhadap:

- Menampilkan menu.
- Menerima input pengguna.
- Melakukan validasi input.
- Menampilkan hasil proses.

---

## 17.3 Controller

Package:

```text
controller
```

Berisi:

```text
BilliardController.java
```

Controller bertanggung jawab terhadap:

- CRUD meja.
- Membuat objek meja.
- Mencari meja.
- Membuka meja.
- Menambahkan waktu.
- Menutup meja.
- Menghitung biaya.
- Mengatur status meja.
- Mengatur status transaksi.
- Menampilkan riwayat transaksi.

---

## 17.4 Main

Class:

```text
Miniproject3_PBO.java
```

Digunakan untuk menjalankan program.

---

# 18. Perbaikan Berdasarkan Evaluasi Minpro Sebelumnya

Pengembangan sebelumnya menjadi dasar perbaikan pada Minpro 4.

Beberapa perbaikan yang dilakukan adalah sebagai berikut.

---

## 18.1 Mengurangi Kode Perhitungan yang Duplikat

Pada versi sebelumnya, perhitungan biaya pada subclass memiliki kode yang sama.

Pada versi sebelumnya, perhitungan dasar dipindahkan ke superclass:

```java
public double hitungBiaya(int durasiJam) {
    return hargaPerJam * durasiJam;
}
```

Dengan demikian, `MejaReguler` dan `MejaVIP` tidak lagi memiliki kode perhitungan yang sama.

Hal ini membuat kode lebih sederhana dan mengurangi duplikasi.

---

## 18.2 Abstraction Digunakan Secara Fungsional

Pada versi sebelumnya, `MejaBilliard` dijadikan abstract class karena memang berfungsi sebagai dasar dari berbagai jenis meja.

Abstract method:

```java
public abstract String getJenisMeja();
```

Kemudian setiap subclass memberikan implementasinya masing-masing.

Dengan demikian, abstraction tidak hanya digunakan untuk memenuhi requirement, tetapi memang digunakan dalam struktur program.

---

## 18.3 Mengurangi Duplikasi pada Controller

Pada proses tambah dan ubah meja terdapat kebutuhan untuk membuat objek Reguler atau VIP.

Untuk mengurangi pengulangan kode, dibuat method:

```java
private MejaBilliard buatMeja(
        int nomorMeja,
        String jenisMeja)
```

Method tersebut bertugas membuat objek berdasarkan jenis meja.

Jika jenisnya Reguler:

```java
return new MejaReguler(nomorMeja);
```

Jika jenisnya VIP:

```java
return new MejaVIP(nomorMeja);
```

Dengan cara tersebut, proses pembuatan objek menjadi lebih terpusat dan controller lebih rapi.

---

## 18.4 Membersihkan Method yang Tidak Digunakan

Method getter dan setter yang tidak digunakan dalam sistem dihapus.

Hal ini dilakukan agar setiap method dalam class memiliki fungsi yang jelas dan tidak terdapat kode yang tidak diperlukan.

---

## 18.5 Menambahkan Riwayat Transaksi

Fitur riwayat transaksi yang telah tersedia pada versi sebelumnya kini dihubungkan ke database MySQL.

Transaksi yang telah selesai tidak lagi dihapus.

Status transaksi diubah:

```text
Aktif → Selesai
```

Transaksi kemudian tetap tersimpan pada tabel `transaksi` dan dapat dibaca kembali menggunakan `TransaksiDAO`.

Pengguna dapat melihat transaksi melalui:

```text
8. Riwayat Transaksi
```

Dengan fitur ini, transaksi sebelumnya tetap dapat dilihat setelah meja ditutup.

---

# 19. Input Validation

Program menerapkan input validation untuk mengurangi kesalahan pengguna.

Validasi yang diterapkan:

- Nomor meja harus lebih dari 0.
- Durasi harus lebih dari 0.
- Tambahan waktu harus lebih dari 0.
- Input angka tidak boleh berupa huruf.
- Jenis meja hanya Reguler atau VIP.
- Nama pelanggan tidak boleh kosong.
- Nomor meja tidak boleh duplikat.
- Meja yang sedang digunakan tidak dapat diubah.
- Meja yang sedang digunakan tidak dapat dihapus.
- Meja yang sedang digunakan tidak dapat dibuka kembali.

Method validasi yang digunakan:

```java
bacaAngka()
```

```java
bacaAngkaPositif()
```

```java
bacaJenisMeja()
```

### Letak Implementasi

```text
Menu.java
```

Validasi dilakukan sebelum data dikirim untuk diproses oleh controller.

## Screenshot Input Validation

<img width="137" height="193" alt="image" src="https://github.com/user-attachments/assets/aa060879-334d-4a50-85d7-a14a71072940" />

**Contoh pengujian:**

```text
Nomor meja: -1
Input harus lebih dari 0.
```

atau:

```text
Nomor meja: abc
Input harus berupa angka.
```

---

# 20. Pencegahan Meja Digunakan Dua Kali

Program melakukan pengecekan status meja sebelum transaksi dibuat.

Jika meja berstatus:

```text
Digunakan
```

maka pengguna tidak dapat membuka meja tersebut kembali.

Sistem langsung memberikan pesan:

```text
Gagal membuka meja. Periksa nomor, durasi, atau status meja.
```

Sistem tidak melanjutkan ke input nama pelanggan dan durasi.

Hal ini mencegah satu meja memiliki transaksi aktif lebih dari satu pada waktu yang sama.

## Screenshot Pencegahan Double Booking

<img width="253" height="239" alt="image" src="https://github.com/user-attachments/assets/19e2900e-8bd8-4a5d-92f8-cc8a3af81d80" />

**Penjelasan:**

Sistem langsung menolak pembukaan meja yang sedang digunakan sehingga tidak terjadi lebih dari satu transaksi aktif pada meja yang sama.

---

# 21. Penggunaan ArrayList

Pada Minpro 4, database MySQL menjadi penyimpanan utama untuk data meja dan transaksi. `ArrayList` tidak lagi digunakan sebagai penyimpanan permanen data utama seperti pada versi sebelumnya.

Pada versi sebelumnya, `ArrayList` digunakan untuk menampung data meja dan transaksi selama program berjalan. Pada Minpro 4, proses penyimpanan dan pengambilan data utama dialihkan ke database MySQL melalui DAO. Dengan demikian, data tidak hilang ketika aplikasi ditutup.

---

# 22. Data Awal dari File SQL

Pada Minpro 4, data awal tidak lagi dibuat melalui constructor `BilliardController`. Data awal dimasukkan ke database melalui file `billiard.sql`.

Data awal yang dimasukkan melalui SQL mencakup meja Reguler nomor 1 dan meja VIP nomor 2. Contoh perintah pada file `billiard.sql`:

```sql
INSERT IGNORE INTO meja_billiard
    (nomor_meja, jenis_meja, harga_per_jam, status)
VALUES
    (1, 'Reguler', 30000, 'Tersedia'),
    (2, 'VIP', 60000, 'Tersedia');
```

Dengan cara ini, data awal dikelola melalui SQL dan bukan ditanam langsung ke dalam kode Java.

## Screenshot Data Awal dari Database

<img width="138" height="191" alt="image" src="https://github.com/user-attachments/assets/7eea7d2d-4059-4626-8bbf-5f55a9102dd6" />

---

# 23. Pengujian Program

Pengujian dilakukan untuk memastikan setiap fitur berjalan sesuai dengan fungsi yang dirancang.

| No. | Pengujian | Hasil |
|---|---|---|
| 1 | Menampilkan data dummy | Berhasil |
| 2 | Menambah meja Reguler | Berhasil |
| 3 | Menambah meja VIP | Berhasil |
| 4 | Menolak nomor meja duplikat | Berhasil |
| 5 | Menampilkan data meja | Berhasil |
| 6 | Mengubah jenis meja | Berhasil |
| 7 | Menolak perubahan meja yang sedang digunakan | Berhasil |
| 8 | Menghapus meja | Berhasil |
| 9 | Menolak penghapusan meja yang sedang digunakan | Berhasil |
| 10 | Membuka meja Reguler | Berhasil |
| 11 | Membuka meja VIP | Berhasil |
| 12 | Perhitungan diskon VIP 10% | Berhasil |
| 13 | Menolak pembukaan meja yang sedang digunakan | Berhasil |
| 14 | Menambahkan waktu bermain | Berhasil |
| 15 | Menutup meja | Berhasil |
| 16 | Mengubah status transaksi menjadi Selesai | Berhasil |
| 17 | Menampilkan riwayat transaksi | Berhasil |
| 18 | Menolak input angka negatif | Berhasil |
| 19 | Menolak input angka 0 untuk data positif | Berhasil |
| 20 | Menolak input huruf pada input angka | Berhasil |
| 21 | Menolak nama pelanggan kosong | Berhasil |

---

# 24. Contoh Pengujian Transaksi VIP

Contoh pengujian transaksi VIP:

```text
Jenis Meja   : VIP
Harga/Jam    : Rp60.000
Durasi       : 3 jam
```

Perhitungan:

```text
Total awal = Rp60.000 × 3
           = Rp180.000

Diskon 10% = Rp18.000

Total bayar = Rp162.000
```

Hasil:

```text
====== STRUK PEMBAYARAN ======
Nama Pelanggan : Riko
Nomor Meja     : 2
Durasi         : 3 jam
Total Bayar    : Rp162000.0
Status         : Selesai
===============================
```

## Screenshot Pengujian VIP

<img width="146" height="271" alt="image" src="https://github.com/user-attachments/assets/5b4e4a8c-5508-4da2-b5b3-34e6825ba0f9" />

---

# 25. Contoh Pengujian Tambah Waktu

Contoh transaksi VIP:

```text
Durasi awal : 2 jam
Tambahan    : 2 jam
Durasi baru : 4 jam
```

Perhitungan:

```text
Harga awal = Rp60.000 × 4
           = Rp240.000

Diskon 10% = Rp24.000

Total bayar = Rp216.000
```

Transaksi kemudian diperbarui menjadi:

```text
Durasi       : 4 jam
Total Bayar  : Rp216000
Status       : Aktif
```

Setelah meja ditutup:

```text
Status       : Selesai
```

## Screenshot Pengujian Tambah Waktu

> **Tambahkan screenshot terbaru yang menunjukkan durasi berubah setelah menggunakan fitur Tambah Waktu.**

<img width="127" height="245" alt="image" src="https://github.com/user-attachments/assets/839111cb-0284-4e8f-ac34-35e89ad852b8" />

<img width="134" height="239" alt="image" src="https://github.com/user-attachments/assets/18d7b2c9-89ad-4687-8762-cdd99c55263d" />

---

# 26. Contoh Riwayat Transaksi

Setelah transaksi selesai, transaksi tetap tersimpan.

Contoh:

```text
======= RIWAYAT TRANSAKSI =======

ID Transaksi : 1
Nomor Meja   : 2
Pelanggan    : Riko
Durasi       : 3 jam
Total Bayar  : Rp162000
Status       : Selesai
--------------------------------

ID Transaksi : 2
Nomor Meja   : 2
Pelanggan    : Riko
Durasi       : 4 jam
Total Bayar  : Rp216000
Status       : Selesai
--------------------------------
```

Setiap transaksi memiliki ID yang berbeda sehingga riwayat transaksi dapat dibedakan.

## Screenshot Riwayat

<img width="140" height="271" alt="image" src="https://github.com/user-attachments/assets/c6a989c7-61c5-403a-83e4-8b0fc3b9dea9" />

---

# 27. Hasil Build Program

Program telah melalui proses build menggunakan Maven dan berhasil dikompilasi.

Contoh hasil:

```text
BUILD SUCCESS
```
---

# 28. Perbaikan Berdasarkan Evaluasi Minpro Sebelumnya

Pengembangan sebelumnya menjadi dasar perbaikan pada Minpro 4.

Beberapa perbaikan yang dilakukan adalah sebagai berikut.

## 28.1 Mengurangi Kode Perhitungan yang Duplikat

Pada versi sebelumnya, perhitungan biaya pada subclass memiliki kode yang sama.

Pada versi sebelumnya, perhitungan dasar dipindahkan ke superclass:

```java
public double hitungBiaya(int durasiJam) {
    return hargaPerJam * durasiJam;
}
```

Dengan demikian, `MejaReguler` dan `MejaVIP` tidak lagi memiliki kode perhitungan yang sama.

Hal ini membuat kode lebih sederhana dan mengurangi duplikasi.

---

## 28.2 Abstraction Digunakan Secara Fungsional

Pada versi sebelumnya, `MejaBilliard` dijadikan abstract class karena memang berfungsi sebagai dasar dari berbagai jenis meja.

Abstract method:

```java
public abstract String getJenisMeja();
```

Kemudian setiap subclass memberikan implementasinya masing-masing.

Dengan demikian, abstraction tidak hanya digunakan untuk memenuhi requirement, tetapi memang digunakan dalam struktur program.

---

## 28.3 Mengurangi Duplikasi pada Controller

Pada proses tambah dan ubah meja terdapat kebutuhan untuk membuat objek Reguler atau VIP.

Untuk mengurangi pengulangan kode, dibuat method:

```java
private MejaBilliard buatMeja(
        int nomorMeja,
        String jenisMeja)
```

Method tersebut bertugas membuat objek berdasarkan jenis meja.

Jika jenisnya Reguler:

```java
return new MejaReguler(nomorMeja);
```

Jika jenisnya VIP:

```java
return new MejaVIP(nomorMeja);
```

Dengan cara tersebut, proses pembuatan objek menjadi lebih terpusat dan controller lebih rapi.

---

## 28.4 Membersihkan Method yang Tidak Digunakan

Method getter dan setter yang tidak digunakan dalam sistem dihapus.

Hal ini dilakukan agar setiap method dalam class memiliki fungsi yang jelas dan tidak terdapat kode yang tidak diperlukan.

---

## 28.5 Menambahkan Riwayat Transaksi

Fitur riwayat transaksi yang telah tersedia pada versi sebelumnya kini dihubungkan ke database MySQL.

Transaksi yang telah selesai tidak lagi dihapus.

Status transaksi diubah:

```text
Aktif → Selesai
```

Transaksi kemudian tetap tersimpan pada tabel `transaksi` dan dapat dibaca kembali menggunakan `TransaksiDAO`.

Pengguna dapat melihat transaksi melalui:

```text
8. Riwayat Transaksi
```

Dengan fitur ini, transaksi sebelumnya tetap dapat dilihat setelah meja ditutup.

---

# 29. Kesimpulan

Program Sistem Manajemen Tempat Billiard berhasil dikembangkan menggunakan bahasa Java dan konsep Pemrograman Berorientasi Objek.

Pada Minpro 3, program tidak hanya memiliki fitur CRUD dan transaksi, tetapi juga menerapkan konsep PBO secara lebih terstruktur, yaitu:

- Encapsulation
- Inheritance
- Polymorphism
- Abstraction
- Interface
- JDBC (Java Database Connectivity)
- MySQL sebagai penyimpanan data permanen
- DAO (Data Access Object)
- PreparedStatement
- MVC
- Input Validation

Polymorphism diterapkan melalui overriding dan overloading.

Overriding diterapkan melalui method `getJenisMeja()` pada subclass `MejaReguler` dan `MejaVIP`.

Overloading diterapkan melalui dua method `hitungBiaya()` dengan parameter yang berbeda.

Abstraction diterapkan melalui abstract class `MejaBilliard` dan abstract method `getJenisMeja()`.

Interface `DapatDisewa` digunakan untuk mendefinisikan fungsi yang berkaitan dengan kemampuan meja untuk disewa.

Program juga mengalami beberapa perbaikan berdasarkan evaluasi dari Mini Project sebelumnya, seperti:

- Mengurangi kode perhitungan yang duplikat.
- Memindahkan perhitungan biaya dasar ke superclass.
- Mengurangi duplikasi pada controller menggunakan method `buatMeja()`.
- Menghapus method yang tidak digunakan.
- Menambahkan riwayat transaksi.
- Mengubah status transaksi dari `Aktif` menjadi `Selesai`.
- Menambahkan diskon 10% untuk meja VIP.
- Menambahkan validasi terhadap meja yang sedang digunakan.
- Meningkatkan struktur program menggunakan MVC.

Dengan penerapan konsep tersebut, program menjadi lebih terstruktur, mudah dikembangkan, dan lebih sesuai dengan prinsip Pemrograman Berorientasi Objek.

---


---

# 30. Pengembangan Minpro 4: Integrasi Database MySQL

Minpro 4 merupakan pengembangan dari sistem manajemen tempat billiard pada versi sebelumnya. Fitur CRUD meja, transaksi, perhitungan biaya, diskon VIP, dan riwayat transaksi tetap dipertahankan. Perubahan utama adalah penggunaan database MySQL sebagai penyimpanan permanen.

Pada versi sebelumnya, data meja dan transaksi disimpan menggunakan `ArrayList` selama program berjalan. Pada Minpro 4, data meja disimpan pada tabel `meja_billiard`, sedangkan data transaksi disimpan pada tabel `transaksi`. Pengelolaan data dilakukan melalui JDBC dan class DAO.

Perubahan ini membuat data tetap tersedia setelah program ditutup dan dijalankan kembali, selama database tidak dihapus.

## 30.1 Perubahan Utama Minpro 4

- Mengintegrasikan Java dengan MySQL menggunakan JDBC.
- Membuat class `DatabaseConnection.java` untuk mengelola koneksi.
- Membuat `MejaDAO.java` untuk operasi data meja.
- Membuat `TransaksiDAO.java` untuk operasi transaksi.
- Menggunakan `PreparedStatement` untuk menjalankan query SQL dengan parameter.
- Memindahkan data awal dari kode Java ke file `billiard.sql`.
- Menyimpan riwayat transaksi secara permanen di database.

---

# 31. Struktur Database

Database yang digunakan pada Minpro 4 bernama `db_billiard_minpro4`.

Database memiliki dua tabel utama, yaitu `meja_billiard` dan `transaksi`.

## 31.1 Tabel `meja_billiard`

Tabel ini menyimpan informasi setiap meja billiard.

| Kolom | Fungsi |
|---|---|
| `nomor_meja` | Nomor unik meja dan primary key |
| `jenis_meja` | Jenis meja, yaitu Reguler atau VIP |
| `harga_per_jam` | Harga sewa meja per jam |
| `status` | Status meja, misalnya Tersedia atau Digunakan |

## 31.2 Tabel `transaksi`

Tabel ini menyimpan data transaksi pelanggan.

| Kolom | Fungsi |
|---|---|
| `id_transaksi` | ID transaksi otomatis dan primary key |
| `nomor_meja` | Nomor meja yang digunakan |
| `nama_pelanggan` | Nama pelanggan |
| `durasi_jam` | Lama penggunaan meja |
| `total_bayar` | Total biaya transaksi |
| `status_transaksi` | Status transaksi Aktif atau Selesai |
| `tanggal_transaksi` | Waktu transaksi tercatat |

Struktur tersebut memungkinkan data meja dan riwayat transaksi disimpan terpisah sesuai fungsinya.

## Screenshot Struktur Database

<img width="958" height="290" alt="image" src="https://github.com/user-attachments/assets/22a26215-613c-418d-8c4c-c0cb46b10127" />

**Penjelasan:** Screenshot memperlihatkan database dan tabel yang digunakan aplikasi Minpro 4 untuk menyimpan data meja serta transaksi pelanggan.

## Screenshot Isi Tabel

<img width="626" height="254" alt="image" src="https://github.com/user-attachments/assets/6d299064-cdf6-4c99-93ed-db2af6e6601b" />

**Penjelasan:** Data awal meja telah dimasukkan ke database dan dapat dilihat langsung melalui phpMyAdmin.

---

# 32. File SQL untuk Struktur dan Data Awal

File `billiard.sql` digunakan untuk membuat database, membuat tabel, serta memasukkan data awal. Dengan demikian, data awal tidak lagi ditulis secara langsung pada constructor controller.

Contoh data awal meja:

```sql
INSERT IGNORE INTO meja_billiard
    (nomor_meja, jenis_meja, harga_per_jam, status)
VALUES
    (1, 'Reguler', 30000, 'Tersedia'),
    (2, 'VIP', 60000, 'Tersedia');
```

Perintah `INSERT IGNORE` membantu mencegah error akibat primary key yang sudah ada ketika skrip dijalankan kembali. File SQL juga dapat memuat perintah `CREATE DATABASE`, `USE`, pembuatan tabel, dan query `SELECT` untuk memeriksa data.

## Screenshot File SQL

<img width="248" height="260" alt="image" src="https://github.com/user-attachments/assets/fafa956f-8e6a-4be2-85a1-3e86cd055e09" />

**Penjelasan:** File SQL menjadi sumber struktur database dan data awal yang dapat dijalankan melalui phpMyAdmin. Pendekatan ini memisahkan data awal dari kode Java.

---

# 33. Koneksi Java dengan MySQL Menggunakan JDBC

JDBC (*Java Database Connectivity*) digunakan agar program Java dapat berkomunikasi dengan database MySQL. Pada Minpro 4, koneksi dikelola melalui class `DatabaseConnection.java`.

Class ini menggunakan `DriverManager.getConnection()` dengan URL database, nama pengguna, dan kata sandi sesuai konfigurasi MySQL lokal.

Contoh konfigurasi koneksi yang digunakan:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/db_billiard_minpro4";
private static final String USER = "root";
private static final String PASSWORD = "";
```

Konfigurasi tersebut menyesuaikan pengaturan lokal Laragon. Apabila MySQL menggunakan port, username, atau password berbeda, nilai konfigurasi harus disesuaikan.

Method `getConnection()` mengembalikan objek `Connection` yang digunakan DAO untuk menjalankan query.

## Screenshot Koneksi JDBC

<img width="427" height="236" alt="image" src="https://github.com/user-attachments/assets/5223d529-2dc1-4455-b835-e6c83432ff5f" />

**Penjelasan:** Class koneksi memusatkan pengaturan JDBC sehingga class DAO dapat menggunakan koneksi yang sama tanpa menulis konfigurasi koneksi berulang kali.

---

# 34. Implementasi DAO dan PreparedStatement

DAO (*Data Access Object*) memisahkan operasi database dari logika utama program. Pada Minpro 4, operasi meja dan transaksi dipisahkan ke dalam dua class DAO.

## 34.1 `MejaDAO.java`

Class `MejaDAO` menangani data pada tabel `meja_billiard`. Method yang digunakan meliputi:

- `getAllMeja()` untuk mengambil seluruh data meja.
- `tambahMeja()` untuk menyimpan meja baru.
- `ubahMeja()` untuk memperbarui informasi meja.
- `hapusMeja()` untuk menghapus meja yang memenuhi ketentuan.
- `ubahStatusMeja()` untuk memperbarui status meja.

Ketika data meja dibaca, DAO membentuk objek `MejaReguler` atau `MejaVIP` berdasarkan nilai `jenis_meja` dari database.

## 34.2 `TransaksiDAO.java`

Class `TransaksiDAO` menangani penyimpanan dan pembacaan transaksi. Method yang digunakan meliputi:

- `tambahTransaksi()` untuk menyimpan transaksi baru.
- `cariTransaksiAktif()` untuk mencari transaksi aktif berdasarkan nomor meja.
- `perbaruiTransaksi()` untuk memperbarui durasi dan total pembayaran.
- `selesaikanTransaksi()` untuk mengubah status transaksi menjadi `Selesai`.
- `getRiwayatTransaksi()` untuk mengambil riwayat transaksi dari database.

Dengan pemisahan ini, `BilliardController` dapat memanggil operasi DAO tanpa menuliskan seluruh query SQL pada controller.

## 34.3 PreparedStatement

`PreparedStatement` digunakan untuk menjalankan query SQL dengan parameter. Nilai dari program dimasukkan melalui method seperti `setInt()`, `setString()`, dan `setDouble()`, sesuai tipe data kolom.

Penggunaan `PreparedStatement` membantu memisahkan perintah SQL dari nilai input dan mengurangi risiko kesalahan ketika input berisi karakter khusus. Parameter tetap harus digunakan secara konsisten untuk setiap nilai yang berasal dari pengguna.

## Screenshot DAO

<img width="188" height="55" alt="image" src="https://github.com/user-attachments/assets/9fc2f3be-b770-49c2-8b9e-86f2d44ad201" />

**Penjelasan:** Struktur tersebut menunjukkan bahwa pengaksesan database dipisahkan berdasarkan jenis data yang dikelola.

<img width="434" height="406" alt="image" src="https://github.com/user-attachments/assets/627fe3b4-9b39-4f12-b674-5332e13122d9" />
<img width="431" height="394" alt="image" src="https://github.com/user-attachments/assets/d4a32e0e-d31f-4dcd-b515-23db2e4f4156" />

**Penjelasan:** Screenshot memperlihatkan penggunaan query berparameter untuk menjalankan operasi database.

---

# 35. Integrasi Database dengan Fitur Program

Fitur program yang sudah tersedia tetap diakses melalui menu console. Perbedaannya, controller kini meminta DAO untuk membaca atau mengubah data pada database.

## 35.1 CRUD Meja

- **Create:** data meja baru dimasukkan ke tabel `meja_billiard`.
- **Read:** data meja diambil dari database menggunakan `getAllMeja()`.
- **Update:** perubahan jenis meja dan harga disimpan melalui `ubahMeja()`.
- **Delete:** data meja dihapus melalui `hapusMeja()` apabila meja memenuhi ketentuan penghapusan.

## 35.2 Transaksi Pelanggan

Saat meja dibuka, transaksi baru disimpan ke tabel `transaksi` dan status meja diperbarui. Ketika pelanggan menambah waktu, durasi serta total biaya transaksi diperbarui. Saat meja ditutup, status transaksi diubah menjadi `Selesai` dan status meja dikembalikan menjadi `Tersedia`.

Untuk meja VIP, perhitungan diskon 10% tetap dipertahankan dari versi sebelumnya.

## 35.3 Riwayat Transaksi

Riwayat transaksi dibaca dari tabel `transaksi` melalui `getRiwayatTransaksi()`. Karena transaksi disimpan di MySQL, data transaksi yang sudah selesai tetap dapat ditampilkan meskipun program ditutup dan dibuka kembali.

---

# 36. Pengujian Minpro 4

Pengujian dilakukan untuk memastikan program dapat membaca dan mengubah data melalui database MySQL.

| No. | Pengujian | Hasil yang Diharapkan |
|---|---|---|
| 1 | Menghubungkan aplikasi dengan MySQL | Koneksi berhasil |
| 2 | Menampilkan data meja | Data dibaca dari tabel `meja_billiard` |
| 3 | Menambah meja | Data baru muncul di aplikasi dan database |
| 4 | Mengubah meja | Perubahan tersimpan di database |
| 5 | Menghapus meja tersedia | Data terhapus dari database |
| 6 | Membuka transaksi | Transaksi tersimpan dengan status `Aktif` |
| 7 | Menambah waktu | Durasi dan total pembayaran diperbarui |
| 8 | Menutup meja | Status transaksi menjadi `Selesai` dan meja menjadi `Tersedia` |
| 9 | Melihat riwayat transaksi | Data transaksi tampil dari database |
| 10 | Memeriksa data lewat phpMyAdmin | Perubahan aplikasi terlihat pada tabel |

## Screenshot Pengujian Read dari MySQL

<img width="167" height="311" alt="image" src="https://github.com/user-attachments/assets/1bc77b6d-b328-403f-ad05-1ab95fab7f02" />

**Penjelasan:** Screenshot memperlihatkan aplikasi berhasil membaca data meja yang tersimpan di MySQL.

## Screenshot Pengujian Riwayat Transaksi

<img width="179" height="359" alt="image" src="https://github.com/user-attachments/assets/0bcc1cd5-4015-453f-a9bf-950fdc90c6e0" />

**Penjelasan:** Screenshot memperlihatkan riwayat transaksi yang telah disimpan dan dibaca kembali dari database.

## Screenshot Verifikasi Transaksi di phpMyAdmin

<img width="731" height="289" alt="image" src="https://github.com/user-attachments/assets/94f91e14-ff4a-418f-9230-1e4d84e3a36d" />

**Penjelasan:** Data pada tabel menunjukkan bahwa transaksi yang dibuat melalui aplikasi tersimpan di database dengan durasi, total pembayaran, dan status transaksi.

---

# 37. Perbedaan Minpro 3 dan Minpro 4

| Aspek | Minpro 3 | Minpro 4 |
|---|---|---|
| Penyimpanan data | `ArrayList` selama program berjalan | Database MySQL |
| Koneksi database | Belum menggunakan JDBC | Menggunakan JDBC |
| Akses data | Dikelola dalam controller | Dipisahkan melalui DAO |
| Query SQL | Belum diterapkan untuk fitur utama | Menggunakan `PreparedStatement` |
| Data awal | Dibuat melalui kode Java | Disediakan melalui `billiard.sql` |
| Riwayat transaksi | Tersimpan selama aplikasi berjalan | Tersimpan permanen di tabel `transaksi` |
| Konsep PBO | Encapsulation, inheritance, polymorphism, abstraction, interface | Konsep PBO dipertahankan dan dilengkapi integrasi database |

Pengembangan Minpro 4 tidak menggantikan konsep PBO yang telah diterapkan sebelumnya. Pengembangan ini memperluas kemampuan program melalui penyimpanan data permanen dan pemisahan akses database agar struktur program lebih terorganisasi.

---

# 38. Struktur File Pendukung Minpro 4

File pendukung yang ditambahkan pada Minpro 4 adalah:

```text
pom.xml
billiard.sql

com.mycompany.miniproject4_pbo
├── dao
│   ├── MejaDAO.java
│   └── TransaksiDAO.java
└── database
    └── DatabaseConnection.java
```

File `pom.xml` memuat dependensi MySQL Connector/J agar aplikasi Java dapat menggunakan driver JDBC MySQL. File `billiard.sql` digunakan untuk menyiapkan database dan data awal. Sementara itu, package `database` dan `dao` bertanggung jawab terhadap koneksi serta operasi data.

---

# 39. Kesimpulan Minpro 4

Sistem Manajemen Tempat Billiard pada Minpro 4 berhasil dikembangkan dengan mempertahankan fitur utama dari versi sebelumnya, seperti CRUD meja, transaksi pelanggan, penambahan waktu, perhitungan biaya, diskon VIP, dan riwayat transaksi.

Pengembangan utama dilakukan dengan mengintegrasikan Java dan MySQL melalui JDBC. Class `DatabaseConnection` digunakan untuk mengatur koneksi, sedangkan `MejaDAO` dan `TransaksiDAO` mengelola operasi data meja dan transaksi. Query dijalankan menggunakan `PreparedStatement`, dan data awal disediakan melalui file `billiard.sql`, bukan dibuat langsung melalui kode Java.

Dengan adanya database, data meja dan riwayat transaksi dapat tetap tersimpan setelah aplikasi ditutup. Struktur DAO juga membantu memisahkan akses database dari controller, sehingga program menjadi lebih terorganisasi dan lebih mudah dikembangkan.

---
