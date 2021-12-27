package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Object.*;

public class userModel {
    private koneksi conn;
    private Statement stmt;

    public userModel() {
        this.conn = new koneksi();
        conn.bikinKoneksi();
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
            String query = "select * from employee where username='" + username + "' and password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs != null && rs.next()) {
                return rs.getString("posisi");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "gagal";
    }

    public String tst(){
        try {
            stmt = conn.getConn().createStatement();
            String query = "select * from employee where nama='azhar'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString("posisi");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "gagal";
    }
}
