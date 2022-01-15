package Controller;

import Model.transaksiModel;
import View.bookRuangan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bookController implements ActionListener {

    bookRuangan b;
    transaksiModel t;

    public bookController() {
        t = new transaksiModel();
        b = new bookRuangan();
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
            b.getTfDurasi().getText() == ""){
                JOptionPane.showMessageDialog(b, "Tidak ada data yang kosong");
            } else if (b.getLabelNama().getText() == ""){
                JOptionPane.showMessageDialog(b, "Anda belum memilih ruangan");
            } else {
                boolean validasi = t.validasiRuangan(b);
                System.out.println(validasi);
                if (validasi){
                    b.getLabelTersedia().setText("Ruangan tersedia");
                    b.getLabelTersedia().setForeground(Color.green);
                    b.getLabelTersedia().setVisible(true);
                } else {
                    System.out.println("Tidak valid");
                    b.getLabelTersedia().setText("Ruangan tidak tersedia");
                    b.getLabelTersedia().setForeground(Color.red);
                    b.getLabelTersedia().setVisible(true);
                }
            }
        } else if (ae == b.getBtnBook()) {
            if (b.getLabelTersedia().isVisible() == false){
                JOptionPane.showMessageDialog(b, "Ruangan Tidak Tersedia");
            }
            new bookController();
            b.dispose();
        } else if (ae == b.getBtnBack()){
            new pelangganController();
            b.dispose();
        }
    }
}
