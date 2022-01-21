package Controller;

import Model.ruanganModel;
import View.lihatRuangan;
import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class lihatRuanganController implements ActionListener {
    lihatRuangan l;
    ruanganModel r;
    public lihatRuanganController(){
        l = new lihatRuangan();
        l.getBtnEdit().setVisible(false);
        l.getBtnHapus().setVisible(false);
        l.setVisible(true);
        l.addListener(this);
        r = new ruanganModel();
        DefaultListModel model;
        model = r.showRuangan();
        l.getjList1().setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if(ae == l.getBtnBack()){
            new managerController();
            l.dispose();
        }
    }
}
