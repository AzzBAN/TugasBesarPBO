package Controller;

import java.awt.event.ActionEvent;

import Model.userModel;
import View.ManagerView;
import View.PelangganView;
import View.adminView;
import View.signin;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signinControl implements ActionListener {
    signin s;
    private userModel usr;
    private String username, password;
    public signinControl() {
        s = new signin();
        s.setVisible(true);
        s.addListener(this);
        usr = new userModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (ae == s.getBtnMasuk()) {
            if (s.getTf_username().getText().isEmpty() || s.getTf_password().getText().isEmpty()){
                JOptionPane.showMessageDialog(s, "Data Username dan Password kosong");
                this.username = s.getTf_username().getText();
                this.password = s.getTf_password().getText();
            } else {
                boolean logstat = false;
                logstat = usr.cekUser(s.getTf_username().getText(),s.getTf_password().getText());
                if(logstat){
                    String posisi = usr.tst();
                    System.out.println(posisi);
                    System.out.println("Proses Pilih Menu");
                    JOptionPane.showMessageDialog(s, "Log in Berhasil Dilakukan");
                    if (posisi == "admin"){
                        adminView admin = new adminView();
                        admin.setVisible(true);
                    } else if (posisi == "manager"){
                        ManagerView manager = new ManagerView();
                        manager.setVisible(true);
                    } else if (posisi == "pelanggan"){
                        PelangganView pelanggan = new PelangganView();
                        pelanggan.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(s,"Username/Password salah");
                    }
                } else {
                    JOptionPane.showMessageDialog(s,"Login Gagal");
                }
            }
        }
        s.dispose();
    }
}
