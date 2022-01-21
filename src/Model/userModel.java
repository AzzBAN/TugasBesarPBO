package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Object.user;
import View.lihatRuangan;

import javax.swing.*;

public class userModel {
    private koneksi conn;
    private Statement stmt;
    String menu;

    public userModel() {
        this.conn = new koneksi();
        conn.bikinKoneksi();
    }

    public userModel(String menu, lihatRuangan l) {
        this.conn = new koneksi();
        conn.bikinKoneksi();
        this.menu = menu;
        fillUser(l);
    }

    public void fillUser(lihatRuangan l){
        DefaultListModel model = new DefaultListModel();
        try {
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from employee";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String nama = rs.getString("nama");
                model.addElement(nama);
            }
            l.getjList1().setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayUserData(lihatRuangan l){
        String selected = l.getjList1().getSelectedValue();
        try {
            stmt = conn.getConn().createStatement();
            String query = "SELECT * from employee WHERE nama = '"+selected+"' ";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                l.getLabelNama().setText(rs.getString("nama"));
                l.getLabelLokasi().setText(rs.getString("tgl_lahir"));
                l.getLabelRating().setText(rs.getString("username"));
                l.getLabelLuas().setText(rs.getString("password"));
                l.getLabelFasilitas().setText(rs.getString("posisi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean cekUser(String username, String password) {
        try {
            stmt = conn.getConn().createStatement();
            String query = "select * from employee where username='"+username+"' and password = '"+password+"'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs != null && rs.next()) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public String cekPosisi(String username, String password) {
        try {
            stmt = conn.getConn().createStatement();
            String query = "select * from employee where (username='"+username+"' and password = '"+password+"')";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString("posisi");
            }else{
                return rs.getString("posisi");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Kosong";
    }

    public void daftarCustomer(user usr){
        try {
            stmt = conn.getConn().createStatement();
            String query = "INSERT INTO `employee` (`id`, `nama`, `posisi`, `tgl_lahir`, `username`, `password`) VALUES (NULL, '"+usr.getNama()+"', '"+usr.getPosisi()+"', '"+usr.getTglLahir()+"', '"+usr.getUsername()+"', '"+usr.getPassword()+"')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public user getuser(String username, String password){
        try{
            stmt = conn.getConn().createStatement();
            String query = "select * from employee where (username='"+username+"' and password = '"+password+"')";
            ResultSet rs = stmt.executeQuery(query);
            if (rs != null && rs.next()){
                user usr = new user(rs.getString("nama"), rs.getString("username"), rs.getString("password"), rs.getString("posisi"), rs.getString("tgl_lahir"), rs.getInt("id"));
                return usr;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccount(user usr){
        try {
            stmt = conn.getConn().createStatement();
            String query = "UPDATE `employee` SET nama = '"+usr.getNama()+"', posisi = '"+usr.getPosisi()+"', tgl_lahir = '"+usr.getTglLahir()+"', username = '"+usr.getUsername()+"', password = '"+usr.getPassword()+"' WHERE employee.nama = '"+usr.getNama()+"'";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void deleteAccount(String nama){
        try {
            stmt = conn.getConn().createStatement();
            String query = "DELETE FROM employee WHERE nama = '"+nama+"'";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
