package Model;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class koneksi {
    static final String DB_URL = "jdbc:mysql://localhost/tubespbo";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection conn;

    public void bikinKoneksi(){
        try {
            //load jdbc driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
//        buat objek koneksi
        try{
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Koneksi Gagal");
            System.err.println("Koneksi gagal !!!!");
            System.err.println(e);
        }
    }

    public void tutupKoneksi(){
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println("tidak ada koneksi yang terbuka");
        }
    }

    public Connection getConn() {
        return conn;
    }

}
