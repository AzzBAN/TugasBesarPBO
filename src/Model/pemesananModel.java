package Model;

import View.bookRuanganView;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Object.user;

public class pemesananModel {
    private koneksi conn;
    private Statement stmt;

    public pemesananModel(){
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

    public void showInformation(String namaRuangan, bookRuanganView b){
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

    public boolean checkAvailable(String tempDate, String tempCheckin, int tempDurasi, bookRuanganView b){
//        System.out.println("tst");
        String date = b.getCbTahun().getSelectedItem()+"-"+b.getCbBulan().getSelectedItem()+"-"+b.getCbTanggal().getSelectedItem();
        int durasi = Integer.parseInt(b.getTfDurasi().getText());
        int checkin = Integer.parseInt(String.valueOf(b.getCbWaktuCheckin().getSelectedItem()).substring(0,2));
        int intCheckin = Integer.parseInt(tempCheckin.substring(0, 2));
        if(tempDate.equals(date)){
//            System.out.println("Validasi tanggal");
            if(checkin == intCheckin){
//                System.out.println("Validasi waktu checkin");
                return false;
            } else if (checkin > intCheckin) {
                for (int i = 0; i < tempDurasi;i++){
                    if (checkin == intCheckin){
//                        System.out.println("validasi durasi count");
                        return false;
                    }
                    intCheckin++;
                }
            } else if (checkin < intCheckin){
                for (int i = 0; i < durasi;i++){
                    if (checkin == intCheckin){
//                        System.out.println("validasi durasi count");
                        return false;
                    }
                    checkin++;
                }
            }
            return true;
        }
        return true;
    }

    public boolean checkDuplicate(String tempDate, String tempCheckin, int tempDurasi, bookRuanganView b){
        String date = b.getCbTahun().getSelectedItem()+"-"+b.getCbBulan().getSelectedItem()+"-"+b.getCbTanggal().getSelectedItem();
        int durasi = Integer.parseInt(b.getTfDurasi().getText());
        int checkin = Integer.parseInt(String.valueOf(b.getCbWaktuCheckin().getSelectedItem()).substring(0,2));
        int intCheckin = Integer.parseInt(tempCheckin.substring(0, 2));
        if(tempDate.equals(date)){
//            System.out.println("Validasi tanggal");
            if(checkin == intCheckin) {
//                System.out.println("Validasi waktu checkin");
                if (durasi == tempDurasi){
                    return false;
                }
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean isBookDuplicate(bookRuanganView b, user usr){
        String nama = b.getListRuangan().getSelectedValue();
        boolean available = true;
        try {
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from pemesanan where customerName = '"+usr.getNama()+"' ";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String tempNama = rs.getString("ruangan");
                String tempDate = rs.getString("tanggal");
                String tempCheckin = rs.getString("waktuCheckin");
                int tempDurasi = rs.getInt("durasi");
                if(nama.equals(tempNama)){
                    available = checkDuplicate(tempDate, tempCheckin, tempDurasi, b);
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

    public boolean validasiRuangan(bookRuanganView b){
        String nama = b.getListRuangan().getSelectedValue();
        boolean available = true;
        try {
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from logtransaksi";
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

    public void pesanRuangan(bookRuanganView b, user usr){
        String date = b.getCbTahun().getSelectedItem()+"-"+b.getCbBulan().getSelectedItem()+"-"+b.getCbTanggal().getSelectedItem();
        int harga = Integer.parseInt(b.getLabelHarga().getText()) * Integer.parseInt(b.getTfDurasi().getText());
        try {
            stmt = conn.getConn().createStatement();
//            String query = "INSERT INTO `logTransaksi` (`id`, `customerName`, `ruangan`, `tanggal`, `waktuCheckin`, `durasi`, `totalHarga`) VALUES (NULL, '"+usr.getNama()+"', '"+b.getLabelNama().getText()+"', '"+date+"', '"+b.getCbWaktuCheckin().getSelectedItem()+"', '"+b.getTfDurasi().getText()+"', '"+harga+"')";
            String query = String.format("INSERT INTO `pemesanan` (`id`, `customerName`, `ruangan`, `tanggal`, `waktuCheckin`, `durasi`, `totalHarga`) VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s')", usr.getNama(), b.getLabelNama().getText(), date, b.getCbWaktuCheckin().getSelectedItem(), b.getTfDurasi().getText(), harga);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
