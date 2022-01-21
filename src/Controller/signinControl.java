package Controller;

import java.awt.event.ActionEvent;

import Model.userModel;
import View.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Object.user;

public class signinControl implements ActionListener, KeyListener {
    signin s;
    private userModel usr;
    private String username, password;
    public signinControl() {
        System.out.println("==== Menu Login ====");
        s = new signin();
        s.setVisible(true);
        s.addListener(this);
        s.addKeyListener(this);
        usr = new userModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (ae == s.getBtnMasuk()) {
            if (s.getTf_username().getText().isEmpty() || s.getTf_password().getText().isEmpty()){
                JOptionPane.showMessageDialog(s, "Data Username dan Password kosong");
                new signinControl();
            } else {
                boolean logstat = false;
                logstat = usr.cekUser(s.getTf_username().getText(),s.getTf_password().getText());
                if(logstat){
                    // update
                    this.username = s.getTf_username().getText();
                    this.password = s.getTf_password().getText();
                    String posisi = usr.cekPosisi(this.username, this.password);
                    System.out.println("Login sebagai -> "+posisi);
                    JOptionPane.showMessageDialog(s, "Log in Berhasil Dilakukan");
                    if ("admin".equals(posisi)){
                        new adminController();
                    } else if ("manager".equals(posisi)){
                        new managerController();
                    } else if ("pelanggan".equals(posisi)){
                        user user = usr.getuser(username, password);
                        new pelangganController(user);
                    } else {
                        JOptionPane.showMessageDialog(s,"Username/Password salah");
                    }
                } else {
                    System.out.println("LOGIN GAGAL");
                    JOptionPane.showMessageDialog(s,"Login Gagal");
                    new signinControl();
                }
            }
            s.dispose();
        } else if (ae == s.getBtnDaftar()){
            System.out.println("-> Menu Daftar");
            new daftarController("pelanggan", "daftar");
            s.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if (s.getTf_username().getText().isEmpty() || s.getTf_password().getText().isEmpty()){
                JOptionPane.showMessageDialog(s, "Data Username dan Password kosong");
                new signinControl();
            } else {
                boolean logstat = false;
                logstat = usr.cekUser(s.getTf_username().getText(),s.getTf_password().getText());
                if(logstat){
                    // update
                    this.username = s.getTf_username().getText();
                    this.password = s.getTf_password().getText();
                    String posisi = usr.cekPosisi(this.username, this.password);
                    System.out.println("Login sebagai -> "+posisi);
                    JOptionPane.showMessageDialog(s, "Log in Berhasil Dilakukan");
                    if ("admin".equals(posisi)){
                        new adminController();
                    } else if ("manager".equals(posisi)){
                        new managerController();
                    } else if ("pelanggan".equals(posisi)){
                        user user = usr.getuser(username, password);
                        new pelangganController(user);
                    } else {
                        JOptionPane.showMessageDialog(s,"Username/Password salah");
                    }
                } else {
                    System.out.println("LOGIN GAGAL");
                    JOptionPane.showMessageDialog(s,"Login Gagal");
                    new signinControl();
                }
            }
            s.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if(e.getKeyCode() == KeyEvent.VK_ENTER){
//            System.out.println("MASUK");
//            if (s.getTf_username().getText().isEmpty() || s.getTf_password().getText().isEmpty()){
//                JOptionPane.showMessageDialog(s, "Data Username dan Password kosong");
//                new signinControl();
//            } else {
//                boolean logstat = false;
//                logstat = usr.cekUser(s.getTf_username().getText(),s.getTf_password().getText());
//                if(logstat){
//                    // update
//                    this.username = s.getTf_username().getText();
//                    this.password = s.getTf_password().getText();
//                    String posisi = usr.cekPosisi(this.username, this.password);
//                    System.out.println("Login sebagai -> "+posisi);
//                    System.out.println("Proses Pilih Menu");
//                    JOptionPane.showMessageDialog(s, "Log in Berhasil Dilakukan");
//                    if ("admin".equals(posisi)){
//                        adminView admin = new adminView();
//                        admin.setVisible(true);
//                    } else if ("manager".equals(posisi)){
//                        new managerController();
//                    } else if ("pelanggan".equals(posisi)){
//                        PelangganView pelanggan = new PelangganView();
//                        pelanggan.setVisible(true);
//                    } else {
//                        JOptionPane.showMessageDialog(s,"Username/Password salah");
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(s,"Login Gagal");
//                    new signinControl();
//                }
//            }
//            s.dispose();
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
}
