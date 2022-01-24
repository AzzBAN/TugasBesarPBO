-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 24 Jan 2022 pada 15.37
-- Versi server: 10.4.21-MariaDB
-- Versi PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tubespbo`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `posisi` varchar(10) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `employee`
--

INSERT INTO `employee` (`id`, `nama`, `posisi`, `tgl_lahir`, `username`, `password`) VALUES
(1, 'admin', 'admin', '2021-12-01', 'admin', 'admin'),
(2, 'azhar', 'manager', '2011-01-04', 'azhar', 'azhar03.,'),
(3, 'coba1', 'pelanggan', '2022-01-20', 'p', 'p'),
(10, 'aldy Kecil', 'pelanggan', '2000-09-14', 'r', 'r'),
(15, 'fani', 'manager', '1996-06-16', 'f', 'f');

-- --------------------------------------------------------

--
-- Struktur dari tabel `history`
--

CREATE TABLE `history` (
  `idPemesanan` int(11) NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `ruangan` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `waktuCheckin` varchar(11) NOT NULL,
  `durasi` int(11) NOT NULL,
  `totalHarga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `history`
--

INSERT INTO `history` (`idPemesanan`, `customerName`, `ruangan`, `tanggal`, `waktuCheckin`, `durasi`, `totalHarga`) VALUES
(5, 'coba1', 'jendi', '2022-01-02', '13.00', 5, 5000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `logtransaksi`
--

CREATE TABLE `logtransaksi` (
  `idPemesanan` int(11) NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `ruangan` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `waktuCheckin` varchar(11) NOT NULL,
  `durasi` int(11) NOT NULL,
  `totalHarga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `logtransaksi`
--

INSERT INTO `logtransaksi` (`idPemesanan`, `customerName`, `ruangan`, `tanggal`, `waktuCheckin`, `durasi`, `totalHarga`) VALUES
(6, 'coba1', 'jendi', '2022-01-01', '12.00', 1, 1000),
(8, 'coba1', 'Kamar jendi', '2022-01-01', '12.00', 1, 100000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemesanan`
--

CREATE TABLE `pemesanan` (
  `id` int(11) NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `ruangan` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `waktuCheckin` varchar(11) NOT NULL,
  `durasi` int(11) NOT NULL,
  `totalHarga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pemesanan`
--

INSERT INTO `pemesanan` (`id`, `customerName`, `ruangan`, `tanggal`, `waktuCheckin`, `durasi`, `totalHarga`) VALUES
(2, 'aldy kecil', 'jendi', '2022-01-02', '12:00', 1, 10000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `ruangan`
--

CREATE TABLE `ruangan` (
  `id` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `lokasi` varchar(150) NOT NULL,
  `rating` int(11) NOT NULL,
  `luasRuangan` varchar(10) NOT NULL,
  `fasilitas` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `ruangan`
--

INSERT INTO `ruangan` (`id`, `nama`, `lokasi`, `rating`, `luasRuangan`, `fasilitas`, `harga`) VALUES
(1, 'Student Center', 'Telkom University, Bandung', 5, '10x30', 'AC, Projector, TV', 0),
(3, 'Kamar jendi', 'BSD', 45, '4', 'AC, Papan Tulis, Projector', 100000),
(4, 'kamar dista', 'trenggalek', 20, '10', 'AC, Meja Rapat, TV', 0),
(8, 'jendi', 'kediri', 30, '10', 'AC, Papan Tulis, Projector, Wifi', 190000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`idPemesanan`);

--
-- Indeks untuk tabel `logtransaksi`
--
ALTER TABLE `logtransaksi`
  ADD PRIMARY KEY (`idPemesanan`);

--
-- Indeks untuk tabel `pemesanan`
--
ALTER TABLE `pemesanan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `ruangan`
--
ALTER TABLE `ruangan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `history`
--
ALTER TABLE `history`
  MODIFY `idPemesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `logtransaksi`
--
ALTER TABLE `logtransaksi`
  MODIFY `idPemesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `pemesanan`
--
ALTER TABLE `pemesanan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `ruangan`
--
ALTER TABLE `ruangan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
