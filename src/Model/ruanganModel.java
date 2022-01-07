package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Object.ruangan;
import View.lihatRuangan;

import javax.swing.*;

public class ruanganModel {
    private koneksi conn;
    private Statement stmt;
    lihatRuangan r;

    public ruanganModel(){
        this.conn = new koneksi();
        conn.bikinKoneksi();
    }

    public void tambahRuangan(ruangan r){
        try {
            stmt = conn.getConn().createStatement();
            String query = "INSERT INTO `ruangan` (`id`, `nama`, `lokasi`, `rating`, `luasRuangan`, `fasilitas`) VALUES (NULL, '"+r.getNama()+"', '"+r.getLokasi()+"', '"+r.getRating()+"', '"+r.getBesarRuanganm()+"', '"+r.getFasilitas()+"')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DefaultListModel showRuangan() {
        DefaultListModel model = null;
        r = new lihatRuangan();
        try {
            model = new DefaultListModel();
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from ruangan";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("masuk");
                String nama = rs.getString("nama");
                model.addElement(nama);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public void itemShow(String namaRuangan, JLabel labelnama, JLabel labelLokasi, JLabel labelRating, JLabel labelLuas, JLabel labelFasilitas){
        try {
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from ruangan WHERE nama = '"+namaRuangan+"'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("masuk");
                String nama = rs.getString("nama");
                String lokasi = rs.getString("lokasi");
                int rating = rs.getInt("rating");
                String luasLokasi = rs.getString("luasRuangan");
                String fasilitas = rs.getString("fasilitas");
                System.out.println("Nama : "+nama);
                System.out.println("Lokasi : "+lokasi);
                labelnama.setText(nama);
                labelLokasi.setText(lokasi);
                labelRating.setText(String.valueOf(rating));
                labelLuas.setText(luasLokasi);
                labelFasilitas.setText("<html>"+fasilitas+"</html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
