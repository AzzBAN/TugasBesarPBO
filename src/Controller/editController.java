package Controller;

import Model.ruanganModel;
import Model.userModel;
import View.daftarView;
import View.lihatRuangan;
import Object.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class editController implements ActionListener {
    lihatRuangan edit;
    daftarView d;
    userModel usrm;
    ruanganModel r;
    user usr;
    String menu;
    String status, nama, username, password, posisi, tglLahir;

    public editController(String posisi){
        this.menu = posisi;
        edit = new lihatRuangan();
        if (this.menu =="admin"){
            edit.getjLabel1().setText("Manage Ruangan");
        } else {
            edit.getjLabel1().setText("Edit Ruangan");
        }
        edit.setVisible(true);
        edit.addListener(this);
        r = new ruanganModel();
        r.showRuangan();
        DefaultListModel model;
        model = r.showRuangan();
        edit.getjList1().setModel(model);
        this.status = "ruangan";
    }

    public editController(user usr) {
        this.usr = usr;
        d = new daftarView();
        usrm = new userModel();
        d.addListener(this);
        d.getTf_nama().setText(usr.getNama());
        d.getCB_Bulan().setSelectedItem(usr.getTglLahir().substring(5, 7));
        d.getCB_tanggal().setSelectedItem(usr.getTglLahir().substring(8, 10));
        d.getCB_tahun().setSelectedItem(usr.getTglLahir().substring(0,4));
        d.getTf_username().setText(usr.getUsername());
        d.getTf_password().setText(usr.getPassword());
        d.getCbPosisi().setSelectedItem(usr.getPosisi().substring(0, 1).toUpperCase() + usr.getPosisi().substring(1));
        d.getBtnDaftar().setText("Update");
        d.setVisible(true);
        this.status = "akun";
    }

    public user getUsr(){
        user usr = new user(this.nama, username, password, posisi, tglLahir, 0);
        return usr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (status == "ruangan"){
            if (ae == edit.getBtnBack()){
                if (menu == "manager"){
                    new managerController();
                    edit.dispose();
                } else {
                    new adminController();
                    edit.dispose();
                }
            } else if (ae == edit.getBtnEdit()){
                if (menu == "manager"){
                    new daftarController("manager", edit, "edit");
                    edit.dispose();
                } else {
                    new daftarController("admin", edit, "edit");
                    edit.dispose();
                }
            } else if (ae == edit.getBtnHapus()){
                r.hapusRuangan(edit.getLabelNama().getText());
                if (menu == "manager"){
                    new editController("manager");
                    edit.dispose();
                } else {
                    new editController("admin");
                    edit.dispose();
                }
            }
        } else if (status == "akun"){
            if (ae == d.getBtnDaftar()) {
                if (d.getTf_nama().getText().isEmpty() ||
                        d.getTf_username().getText().isEmpty() ||
                        d.getTf_password().getText().isEmpty() ||
                        d.getCB_Bulan().getSelectedItem() == "Pilih" ||
                        d.getCB_tanggal().getSelectedItem() == "Pilih" ||
                        d.getCB_tahun().getSelectedItem() == "Pilih" ||
                        d.getCbPosisi().getSelectedItem() == "Pilih") {
                    JOptionPane.showMessageDialog(d, "Tidak boleh ada data yang kosong");
                } else {
                    this.nama = d.getTf_nama().getText();
                    this.posisi = d.getCbPosisi().getSelectedItem().toString().toLowerCase(Locale.ROOT);
                    this.username = d.getTf_username().getText();
                    this.password = d.getTf_password().getText();
                    this.tglLahir = d.getCB_tahun().getSelectedItem() + "-" + d.getCB_Bulan().getSelectedItem() + "-" + d.getCB_tanggal().getSelectedItem();
                    usrm.updateAccount(getUsr());
                    System.out.println("+Nama     : " + nama);
                    System.out.println("+Posisi   : " + posisi);
                    System.out.println("Tgl Lahir : " + tglLahir);
                    System.out.println("+Username : " + username);
                    System.out.println("+Password : " + password);
                    JOptionPane.showMessageDialog(d, "Proses Update Akun Berhasil");
                    new manageAkunController();
                    d.dispose();
                }
            } else if (ae == d.getBtnBack()) {
                System.out.println("Back");
                new manageAkunController();
                d.dispose();
            }
        }
    }
}
