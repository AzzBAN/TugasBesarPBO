package Controller;

import Model.ruanganModel;
import View.lihatRuangan;
import View.tambahRuangan;
import Object.ruangan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editController implements ActionListener {
    lihatRuangan edit;
    ruanganModel r;
    String nama, lokasi, luasRuangan, fasilitas;
    int rating, harga;

    public editController(){
        edit = new lihatRuangan();
        edit.getjLabel1().setText("Edit Ruangan");
        edit.setVisible(true);
        edit.addListener(this);
        r = new ruanganModel();
        r.showRuangan();
        DefaultListModel model;
        model = r.showRuangan();
        edit.getjList1().setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (ae == edit.getBtnBack()){
            new managerController();
            edit.dispose();
        } else if (ae == edit.getBtnEdit()){
            new daftarController("manager", edit, "edit");
            edit.dispose();
        } else if (ae == edit.getBtnHapus()){

        }
    }
}
