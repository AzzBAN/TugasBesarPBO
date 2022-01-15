package Controller;

import Model.ruanganModel;
import Model.userModel;
import View.daftarView;
import Object.user;
import Object.customer;
import Object.ruangan;
import View.lihatRuangan;
import View.tambahRuangan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class daftarController implements ActionListener {
    daftarView d;
    tambahRuangan r;
    userModel usr;
    ruanganModel ruangan;
    String posisiLogin, status;
    String nama, posisi, username, password, tgl_lahir, lokasi, luasRuangan, fasilitas, lnama;
    int rating, harga;
    private List<String> checkBoxes = new ArrayList<String>();
    public daftarController(String posisi, String status){
        if (posisi == "manager"){
            this.posisiLogin = posisi;
            r = new tambahRuangan();
            r.setVisible(true);
            r.addListener(this);
            ruangan = new ruanganModel();
            this.status = status;
        } else if (posisi == "pelanggan"){
            d = new daftarView();
            d.setVisible(true);
            d.addListener(this);
            usr = new userModel();
            this.posisiLogin = posisi;
            this.status = status;
        }
     }
     public daftarController(String posisi, lihatRuangan l, String status){
         this.posisiLogin = posisi;
         r = new tambahRuangan();
         r.getBtnDaftar().setText("Updates");
         this.lnama = l.getjList1().getSelectedValue();
         ruangan = new ruanganModel();
         r = ruangan.editShow(r, lnama);
         r.setVisible(true);
         r.addListener(this);
         this.status = status;
         System.out.println(this.status);
     }

    public user addCustomer(){
        user usr = new customer(nama, username, password, posisi, tgl_lahir, 0);
        return usr;
    }

    public ruangan addRuangan(){
        ruangan ru = new ruangan(nama, lokasi, rating, luasRuangan, fasilitas, harga);
        return ru;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (posisiLogin == "pelanggan" && status == "daftar") {
            if (ae == d.getBtnDaftar()) {
                if (d.getTf_nama().getText().isEmpty() ||
                        d.getTf_username().getText().isEmpty() ||
                        d.getTf_password().getText().isEmpty() ||
                        d.getCB_Bulan().getSelectedItem() == "Pilih" ||
                        d.getCB_tanggal().getSelectedItem() == "Pilih" ||
                        d.getCB_tahun().getSelectedItem() == "Pilih") {
                    JOptionPane.showMessageDialog(d, "Tidak boleh ada data yang kosong");
                } else {
                    this.nama = d.getTf_nama().getText();
                    this.posisi = "pelanggan";
                    this.username = d.getTf_username().getText();
                    this.password = d.getTf_password().getText();
                    this.tgl_lahir = d.getCB_tahun().getSelectedItem() + "-" + d.getCB_Bulan().getSelectedItem() + "-" + d.getCB_tanggal().getSelectedItem();
                    usr.daftarCustomer(addCustomer());
                    System.out.println("+Nama     : " + nama);
                    System.out.println("+Posisi   : " + posisi);
                    System.out.println("Tgl Lahir : " + tgl_lahir);
                    System.out.println("+Username : " + username);
                    System.out.println("+Password : " + password);
                    JOptionPane.showMessageDialog(d, "Proses Pendaftaran Berhasil");
                    new signinControl();
                    d.dispose();
                }
            } else if (ae == d.getBtnBack()) {
                System.out.println("Back");
                new signinControl();
                d.dispose();
            }
        } else if (posisiLogin == "manager"){
            if(status == "tambah"){
                System.out.println("masuk");
                if(ae == r.getBtnDaftar()){
                    if(r.getTf_namaRuangan().getText().isEmpty() ||
                            r.getTf_luasRuangan().getText().isEmpty() ||
                            r.getTa_lokasi().getText().isEmpty() ||
                            r.getSl_rating().getValue() == 0) {
                        JOptionPane.showMessageDialog(r, "Tidak boleh ada data yang kosong");
                    } else {
                        this.nama = r.getTf_namaRuangan().getText();
                        this.lokasi = r.getTa_lokasi().getText();
                        this.rating = r.getSl_rating().getValue();
                        this.luasRuangan = r.getTf_luasRuangan().getText();
                        if (r.getCb_ac().isSelected()){
                            checkBoxes.add(r.getCb_ac().getText());
                        }
                        if (r.getCb_papanTulis().isSelected()){
                            checkBoxes.add(r.getCb_papanTulis().getText());
                        }
                        if (r.getCb_mejaRapat().isSelected()){
                            checkBoxes.add(r.getCb_mejaRapat().getText());
                        }
                        if (r.getCb_projector().isSelected()){
                            checkBoxes.add(r.getCb_projector().getText());
                        }
                        if (r.getCb_TV().isSelected()){
                            checkBoxes.add(r.getCb_TV().getText());
                        }
                        if (r.getCb_wifi().isSelected()){
                            checkBoxes.add(r.getCb_wifi().getText());
                        }
                        fasilitas = String.join(", ", checkBoxes);
                        harga = Integer.parseInt(r.getTf_harga().getText());
                        ruangan.tambahRuangan(addRuangan());
                        System.out.println("+Nama Ruangan : "+ nama);
                        System.out.println("+Lokasi       : "+ lokasi);
                        System.out.println("+Rating       : "+rating);
                        System.out.println("+Luas Ruangan : "+luasRuangan);
                        System.out.println("+Fasilitas    : "+fasilitas);
                        JOptionPane.showMessageDialog(r, "Proses tambah ruangan berhasil");
                        new editController();
                        r.dispose();
                    }
                } else {
                    new managerController();
                    r.dispose();
                }
            } else {
                if (ae == r.getBtnDaftar()){
                    if(r.getTf_namaRuangan().getText().isEmpty() ||
                            r.getTf_luasRuangan().getText().isEmpty() ||
                            r.getTa_lokasi().getText().isEmpty() ||
                            r.getSl_rating().getValue() == 0) {
                        JOptionPane.showMessageDialog(r, "Tidak boleh ada data yang kosong");
                    } else {
                        this.nama = r.getTf_namaRuangan().getText();
                        this.lokasi = r.getTa_lokasi().getText();
                        this.rating = r.getSl_rating().getValue();
                        this.luasRuangan = r.getTf_luasRuangan().getText();
                        if (r.getCb_ac().isSelected()){
                            checkBoxes.add(r.getCb_ac().getText());
                        }
                        if (r.getCb_papanTulis().isSelected()){
                            checkBoxes.add(r.getCb_papanTulis().getText());
                        }
                        if (r.getCb_mejaRapat().isSelected()){
                            checkBoxes.add(r.getCb_mejaRapat().getText());
                        }
                        if (r.getCb_projector().isSelected()){
                            checkBoxes.add(r.getCb_projector().getText());
                        }
                        if (r.getCb_TV().isSelected()){
                            checkBoxes.add(r.getCb_TV().getText());
                        }
                        if (r.getCb_wifi().isSelected()){
                            checkBoxes.add(r.getCb_wifi().getText());
                        }
                        fasilitas = String.join(", ", checkBoxes);
                        harga = Integer.parseInt(r.getTf_harga().getText());
                        ruangan.editRuangan(addRuangan(), this.lnama);
                        System.out.println("+Nama Ruangan : "+ nama);
                        System.out.println("+Lokasi       : "+ lokasi);
                        System.out.println("+Rating       : "+rating);
                        System.out.println("+Luas Ruangan : "+luasRuangan);
                        System.out.println("+Fasilitas    : "+fasilitas);
                        JOptionPane.showMessageDialog(d, "Proses edit ruangan berhasil");
                        new managerController();
                        r.dispose();
                    }
                } else {
                    new editController();
                    r.dispose();
                }
            }
        }

    }
}

