package Controller;

import View.ManagerView;
import View.tambahRuangan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class managerController implements ActionListener {
    ManagerView m;

    public managerController() {
        m = new ManagerView();
        m.setVisible(true);
        m.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if(ae == m.getBtnLogout()){
            new signinControl();
        } else if (ae == m.getBtnTambahRuangan()){
            System.out.println("-> Tambah Ruangan");
            new daftarController("manager");
            m.dispose();
        } else if (ae == m.getBtnEditRuangan()){

        } else if (ae == m.getBtnLihatRuangan()){
            System.out.println("-> Lihat Ruangan");
            new lihatRuanganController();
            m.dispose();
        } else if (ae == m.getBtnLogTransaksi()){

        }
        m.dispose();
    }
}
