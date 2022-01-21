package Controller;

import Model.pemesananModel;
import View.bookRuanganView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Object.user;

public class bookController implements ActionListener {

    user usr;
    bookRuanganView b;
    pemesananModel t;
    private boolean validasi = false, duplicate = false;

    public bookController(user usr) {
        this.usr = usr;
        t = new pemesananModel();
        b = new bookRuanganView();
        b.setVisible(true);
        b.addListener(this);
        b.getLabelTersedia().setVisible(false);
        t.isiList(b.getListRuangan());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if(ae == b.getBtnCekRuangan()){
            if (b.getCbTanggal().getSelectedItem() == "Pilih" ||
            b.getCbBulan().getSelectedItem() == "Pilih" ||
            b.getCbTahun().getSelectedItem() == "Pilih" ||
            b.getCbWaktuCheckin().getSelectedItem() == "Pilih" ||
            b.getTfDurasi().getText().isEmpty()){
                JOptionPane.showMessageDialog(b, "Tidak boleh ada data yang kosong");
            } else if (b.getLabelNama().getText() == ""){
                JOptionPane.showMessageDialog(b, "Anda belum memilih ruangan");
            } else {
                validasi = t.validasiRuangan(b);
                duplicate = t.isBookDuplicate(b, usr);
                System.out.println(validasi);
                if (duplicate){
                    if (validasi && duplicate){
                        b.getLabelTersedia().setText("Ruangan tersedia");
                        b.getLabelTersedia().setForeground(Color.green);
                        b.getLabelTersedia().setVisible(true);
                    } else {
//                    System.out.println("Tidak valid");
                        b.getLabelTersedia().setText("Ruangan tidak tersedia");
                        b.getLabelTersedia().setForeground(Color.red);
                        b.getLabelTersedia().setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(b, "Data pemesanan terdeteksi duplikat");
                }
            }
        } else if (ae == b.getBtnBook()) {
            if (b.getCbTanggal().getSelectedItem() == "Pilih" ||
                    b.getCbBulan().getSelectedItem() == "Pilih" ||
                    b.getCbTahun().getSelectedItem() == "Pilih" ||
                    b.getCbWaktuCheckin().getSelectedItem() == "Pilih" ||
                    b.getTfDurasi().getText() == ""){
                JOptionPane.showMessageDialog(b, "Tidak ada data yang kosong");
            } else if (b.getLabelTersedia().getText() == "Ruangan tersedia"){
                t.pesanRuangan(b, this.usr);
                JOptionPane.showMessageDialog(b, "Ruangan berhasil di pesan");
            } else {
                if (b.getLabelTersedia().getText().isEmpty()){
                    JOptionPane.showMessageDialog(b, "Harap cek ketersediaan ruangan terlebih dahulu");
                } else {
                    JOptionPane.showMessageDialog(b, "Ruangan Tidak Tersedia");
                }
            }
        } else if (ae == b.getBtnBack()){
            new pelangganController(this.usr);
            b.dispose();
        }
    }
}
