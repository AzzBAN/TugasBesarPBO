package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Object.pemesanan;

import Object.user;
import View.pembayaranView;

public class PembayaranModel {
    private koneksi conn;
    private Statement stmt;

    public PembayaranModel(user usr, pembayaranView p) {
        this.conn = new koneksi();
        conn.bikinKoneksi();
        fillData(usr, p);
    }

    public PembayaranModel(pembayaranView pem){
        this.conn = new koneksi();
        conn.bikinKoneksi();
        viewLogTransaksi(pem);
    }

    public void viewLogTransaksi(pembayaranView pem){
        DefaultTableModel model = (DefaultTableModel) pem.getTabelPembayaran().getModel();
        ArrayList<pemesanan> list = new ArrayList<pemesanan>();
        int total = 0;
        pemesanan p;
        try {
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from logtransaksi";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                p = new pemesanan(rs.getString("customerName"),
                        rs.getString("ruangan"),
                        rs.getString("tanggal"), rs.getString("waktuCheckin"),
                        rs.getString("durasi"),
                        rs.getInt("idPemesanan"), rs.getInt("totalHarga"));

                list.add(p);
            }
            Object rowData[] = new Object[7];
            for (int i = 0; i < list.size(); i++){
                rowData[0] = list.get(i).getId();
                rowData[1] = list.get(i).getRuangan();
                rowData[2] = list.get(i).getTanggal();
                rowData[3] = list.get(i).getWaktuCheckin();
                rowData[4] = list.get(i).getDurasi();
                rowData[5] = list.get(i).getTotalHarga();
                total = total + list.get(i).getTotalHarga();
                model.addRow(rowData);
            }
            pem.getLabelNama().setText("MANAGER");
            pem.getLabelTotalHarga().setText(String.valueOf(total));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillData(user usr, pembayaranView pem){
        DefaultTableModel model = (DefaultTableModel) pem.getTabelPembayaran().getModel();
        ArrayList<pemesanan> list = new ArrayList<pemesanan>();
        int total = 0;
        pemesanan p;
        try {
            stmt = conn.getConn().createStatement();
            String query;
            if (pem.getBtnBayar().isVisible()){
                query = "SELECT * from pemesanan WHERE customerName = '"+usr.getNama()+"'";
            } else {
                System.out.println("MASUK");
                query = "SELECT * from logtransaksi WHERE customerName = '"+usr.getNama()+"'";
            }
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                if (pem.getBtnBayar().isVisible()){
                    p = new pemesanan(rs.getString("customerName"),
                            rs.getString("ruangan"),
                            rs.getString("tanggal"), rs.getString("waktuCheckin"),
                            rs.getString("durasi"),
                            rs.getInt("id"), rs.getInt("totalHarga"));
                } else {
                    p = new pemesanan(rs.getString("customerName"),
                            rs.getString("ruangan"),
                            rs.getString("tanggal"), rs.getString("waktuCheckin"),
                            rs.getString("durasi"),
                            rs.getInt("idPemesanan"), rs.getInt("totalHarga"));
                }
                list.add(p);
            }
            Object rowData[] = new Object[7];
            for (int i = 0; i < list.size(); i++){
                rowData[0] = list.get(i).getId();
                rowData[1] = list.get(i).getRuangan();
                rowData[2] = list.get(i).getTanggal();
                rowData[3] = list.get(i).getWaktuCheckin();
                rowData[4] = list.get(i).getDurasi();
                rowData[5] = list.get(i).getTotalHarga();
                total = total + list.get(i).getTotalHarga();
                model.addRow(rowData);
            }
            pem.getLabelNama().setText(usr.getNama());
            pem.getLabelTotalHarga().setText(String.valueOf(total));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkAvailable(String tempDate, String tempCheckin, int tempDurasi, pembayaranView pem){
        JTable tabel = pem.getTabelPembayaran();
        int idx = pem.getTabelPembayaran().getSelectedRow();
        String date = (String) tabel.getValueAt(idx, 2);
        String strdurasi = (String) tabel.getValueAt(idx, 4);
        int durasi = Integer.parseInt(strdurasi);
        String strcheckin = (String) tabel.getValueAt(idx, 3);
        int checkin = Integer.parseInt(strcheckin.substring(0,2));
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

    public boolean validasiPembayaran(pembayaranView pem){
        int idx = pem.getTabelPembayaran().getSelectedRow();
        String nama = (String) pem.getTabelPembayaran().getValueAt(idx, 1);
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
                    available = checkAvailable(tempDate, tempCheckin, tempDurasi, pem);
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

    public void pembayaranRuangan(pembayaranView pem){
        JTable tabel = pem.getTabelPembayaran();
        int idx = pem.getTabelPembayaran().getSelectedRow();
        int idPemesanan = (int) tabel.getValueAt(idx, 0);
        try {
            if (validasiPembayaran(pem)){
                stmt = conn.getConn().createStatement();
                String query = "INSERT INTO logtransaksi SELECT * from pemesanan WHERE id = '"+idPemesanan+"'";
                stmt.executeUpdate(query);
                query = "DELETE FROM pemesanan WHERE id = '"+idPemesanan+"'";
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(pem, "Pembayaran berhasil");
            } else {
                String query = "DELETE FROM pemesanan WHERE id = '"+idPemesanan+"'";
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(pem, "Ruangan tersebut sudah ada yang book");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelOrder(pembayaranView pem){
        JTable tabel = pem.getTabelPembayaran();
        int idx = pem.getTabelPembayaran().getSelectedRow();
        int idPemesanan = (int) tabel.getValueAt(idx, 0);
        try {
            stmt = conn.getConn().createStatement();
            String query = "DELETE FROM pemesanan WHERE id = '"+idPemesanan+"'";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkout(pembayaranView pem){
        JTable tabel = pem.getTabelPembayaran();
        int idx = pem.getTabelPembayaran().getSelectedRow();
        int idPemesanan = (int) tabel.getValueAt(idx, 0);
        try {
            stmt = conn.getConn().createStatement();
            String query = "INSERT INTO history SELECT * from logtransaksi WHERE idPemesanan = '"+idPemesanan+"'";
            stmt.executeUpdate(query);
            query = "DELETE FROM logtransaksi WHERE idPemesanan = '"+idPemesanan+"'";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
