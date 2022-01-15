package Model;

import View.bookRuangan;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class transaksiModel {
    private koneksi conn;
    private Statement stmt;

    public transaksiModel(){
        this.conn = new koneksi();
        conn.bikinKoneksi();
    }

    public void isiList(JList list){
        DefaultListModel model;
        try {
            model = new DefaultListModel();
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from ruangan";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                model.addElement(rs.getString("nama"));
            }
            list.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showInformation(String namaRuangan, bookRuangan b){
        try {
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from ruangan WHERE nama = '"+namaRuangan+"'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String nama = rs.getString("nama");
                String lokasi = rs.getString("lokasi");
                String rating = rs.getString("rating");
                String LuasRuangan = rs.getString("luasRuangan");
                String fasilitas = rs.getString("fasilitas");
                String harga = rs.getString("harga");

                b.getLabelNama().setText(nama);
                b.getLabelLokasi().setText(lokasi);
                b.getLabelRating().setText(rating);
                b.getLabelLuas().setText(LuasRuangan);
                b.getLabelFasilitas().setText("<html>"+fasilitas+"</html>");
                b.getLabelHarga().setText(harga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkAvailable(String tempDate, String tempCheckin, int tempDurasi, bookRuangan b){
        System.out.println("tst");
        String date = b.getCbTahun().getSelectedItem()+"-"+b.getCbBulan().getSelectedItem()+"-"+b.getCbTanggal().getSelectedItem();
        int checkin = Integer.parseInt(String.valueOf(b.getCbWaktuCheckin().getSelectedItem()).substring(0,2));
        int intCheckin = Integer.parseInt(tempCheckin.substring(0, 2));
        if(tempDate.equals(date)){
            System.out.println("Validasi tanggal");
            if(checkin == intCheckin){
                System.out.println("Validasi waktu checkin");
                return false;
            } else {
                for (int i = 0; i < tempDurasi;i++){
                    if (checkin == intCheckin){
                        System.out.println("validasi durasi count");
                        return false;
                    }
                    intCheckin++;
                }
            }
            return true;
        }
        return true;
    }

    public boolean validasiRuangan(bookRuangan b){
        String nama = b.getListRuangan().getSelectedValue();
        boolean available = true;
        try {
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from logTransaksi";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String tempNama = rs.getString("ruangan");
                String tempDate = rs.getString("tanggal");
                String tempCheckin = rs.getString("waktuCheckin");
                int tempDurasi = rs.getInt("durasi");
                if(nama.equals(tempNama)){
                    available = checkAvailable(tempDate, tempCheckin, tempDurasi, b);
                    if (available == false){
                        return available;
                    }
                }
            }
            return available;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return available;
    }

}
