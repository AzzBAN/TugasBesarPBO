package Controller;

import Model.userModel;
import View.daftarView;
import Object.user;
import Object.customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class daftarController implements ActionListener {
    daftarView d;
    userModel usr;
    String nama, posisi, username, password, tgl_lahir;
    public daftarController() {
        d = new daftarView();
        d.setVisible(true);
        d.addListener(this);
        usr = new userModel();
    }

    public user addCustomer(){
        user usr = new customer(nama, username, password, posisi, tgl_lahir, 0);
        return usr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (ae == d.getBtnDaftar()){
            if(d.getTf_nama().getText().isEmpty() ||
            d.getTf_username().getText().isEmpty() ||
            d.getTf_password().getText().isEmpty() ||
            d.getCB_Bulan().getSelectedItem() == "Pilih" ||
            d.getCB_tanggal().getSelectedItem() == "Pilih" ||
            d.getCB_tahun().getSelectedItem() == "Pilih"){
                JOptionPane.showMessageDialog(d, "Tidak boleh ada data yang kosong");
            } else {
                this.nama = d.getTf_nama().getText();
                this.posisi = "pelanggan";
                this.username = d.getTf_username().getText();
                this.password = d.getTf_password().getText();
                this.tgl_lahir = d.getCB_tahun().getSelectedItem() + "-" + d.getCB_Bulan().getSelectedItem() + "-" + d.getCB_tanggal().getSelectedItem();
                usr.daftarCustomer(addCustomer());
                JOptionPane.showMessageDialog(d, "Proses Pendaftaran Berhasil");
                new signinControl();
                d.dispose();
            }
        } else if (ae == d.getBtnBack()) {
            new signinControl();
            d.dispose();
        }
    }
}

