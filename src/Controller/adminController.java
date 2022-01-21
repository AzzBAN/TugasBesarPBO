package Controller;

import View.adminView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminController implements ActionListener {

    adminView a;

    public adminController() {
        System.out.println("Proses Pilih Menu");
        a = new adminView();
        a.setVisible(true);
        a.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (ae == a.getBtnLogout()){
            System.out.println("LOGOUT");
            new signinControl();
        } else if (ae == a.getBtnTambahAkun()) {
            System.out.println("-> Tambah Akun");
            new daftarController("admin", "daftar");
        } else if (ae == a.getBtnManageAkun()) {
            new manageAkunController();
        } else if (ae == a.getBtnManageRuangan()) {
            new editController("admin");
        } else if (ae == a.getBtnLogTransaksi()) {
            new pembayaranController();
        }
        a.dispose();
    }
}
