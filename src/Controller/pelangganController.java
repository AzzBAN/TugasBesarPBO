package Controller;

import View.PelangganView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pelangganController implements ActionListener {
    PelangganView p;

    public pelangganController() {
        System.out.println("Proses Pilih Menu");
        p = new PelangganView();
        p.setVisible(true);
        p.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (ae == p.getBtnBookRuangan()){
            System.out.println("-> Book Ruangan");
            new bookController();
        } else {
            new signinControl();
        }
        p.dispose();
    }
}
