package Controller;

import View.PelangganView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Object.user;

public class pelangganController implements ActionListener {
    PelangganView p;
    user pelanggan;

    public pelangganController(user usr) {
        System.out.println("Proses Pilih Menu");
        pelanggan = usr;
        p = new PelangganView();
        p.setVisible(true);
        p.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (ae == p.getBtnBookRuangan()){
            System.out.println("-> Book Ruangan");
            new bookController(pelanggan);
        } else if (ae == p.getBtnPembayaran()) {
            new pembayaranController(pelanggan, "pembayaran");
        } else if (ae == p.getBtnLogout()) {
            new signinControl();
        } else if (ae == p.getBtnRuangan()) {
            new pembayaranController(pelanggan, "ruangan");
        }
        p.dispose();
    }
}
