package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Object.ruangan;
import View.lihatRuangan;
import View.tambahRuangan;

import javax.swing.*;

public class ruanganModel {
    private koneksi conn;
    private Statement stmt;
    lihatRuangan r;
    tambahRuangan t;

    public ruanganModel(){
        this.conn = new koneksi();
        conn.bikinKoneksi();
    }

    public void tambahRuangan(ruangan r){
        try {
            stmt = conn.getConn().createStatement();
            String query = "INSERT INTO `ruangan` (`id`, `nama`, `lokasi`, `rating`, `luasRuangan`, `fasilitas`, `harga`) VALUES (NULL, '"+r.getNama()+"', '"+r.getLokasi()+"', '"+r.getRating()+"', '"+r.getBesarRuanganm()+"', '"+r.getFasilitas()+"', '"+r.getHarga()+"')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DefaultListModel showRuangan() {
        r = new lihatRuangan();
        DefaultListModel model = null;
        try {
            model = new DefaultListModel();
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from ruangan";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nama = rs.getString("nama");
                model.addElement(nama);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public void itemShow(String namaRuangan, JLabel labelnama, JLabel labelLokasi, JLabel labelRating, JLabel labelLuas, JLabel labelFasilitas, JLabel labelHarga, JButton edit, JButton hapus){
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
                String harga = rs.getString("harga");
                System.out.println("Nama : "+nama);
                System.out.println("Lokasi : "+lokasi);
                labelnama.setText(nama);
                labelLokasi.setText(lokasi);
                labelRating.setText(String.valueOf(rating));
                labelLuas.setText(luasLokasi);
                labelFasilitas.setText("<html>"+fasilitas+"</html>");
                labelHarga.setText(harga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public tambahRuangan editShow(tambahRuangan t, String nama){
        String[] cb;
        try{
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from ruangan WHERE nama = '"+nama+"'";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                t.getTf_namaRuangan().setText(rs.getString("nama"));
                t.getTa_lokasi().setText(rs.getString("lokasi"));
                t.getSl_rating().setValue(rs.getInt("rating"));
                t.getjLabel7().setText(rs.getString("rating"));
                t.getTf_luasRuangan().setText(rs.getString("luasRuangan"));
                cb = rs.getString("fasilitas").split("\\s*,\\s*");
                for(int i=0;i < cb.length;i++){
                    if(cb[i].equals("AC")){
                        System.out.println("Combo Box AC selected");
                        t.getCb_ac().setSelected(true);
                    } else if (cb[i].equals("TV")){
                        System.out.println("Combo Box TV selected");
                        t.getCb_TV().setSelected(true);
                    } else if (cb[i].equals("Meja Rapat")){
                        System.out.println("Combo Box Meja Rapat selected");
                        t.getCb_mejaRapat().setSelected(true);
                    } else if (cb[i].equals("Papan Tulis")){
                        System.out.println("Combo Box Papan Tulis selected");
                        t.getCb_papanTulis().setSelected(true);
                    } else if (cb[i].equals("Projector")){
                        System.out.println("Combo Box Projector selected");
                        t.getCb_projector().setSelected(true);
                    } else if (cb[i].equals("Wifi")){
                        System.out.println("Combo Box wifi selected");
                        t.getCb_wifi().setSelected(true);
                    }
                }
                t.getTf_harga().setText(rs.getString("harga"));
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void editRuangan(ruangan r, String defaultName){
        try {
            stmt = conn.getConn().createStatement();
            String query = "UPDATE ruangan SET nama = '"+r.getNama()+"', lokasi = '"+r.getLokasi()+"', rating = '"+r.getRating()+"', luasRuangan = '"+r.getBesarRuanganm()+"', fasilitas = '"+r.getFasilitas()+"', harga = '"+r.getHarga()+"' WHERE ruangan.nama = '"+defaultName+"' ";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
