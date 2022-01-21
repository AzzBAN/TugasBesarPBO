package Controller;

import Model.PembayaranModel;
import View.pembayaranView;
import Object.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pembayaranController implements ActionListener {

    user usr;
    pembayaranView p;
    PembayaranModel pem;
    String menu;

    public pembayaranController(){
        this.menu = "manager";
        p = new pembayaranView();
        p.getBtnCheckout().setVisible(false);
        p.addListener(this);
        p.getjLabel4().setText("Total Keuntungan :");
        p.getjLabel1().setText("LOG TRANSAKSI");
        p.getBtnBayar().setVisible(false);
        p.getBtnHapus().setVisible(false);
        p.setVisible(true);
        pem = new PembayaranModel(p);
    }

    public pembayaranController(user usr, String menu) {
        if (menu == "pembayaran"){
            this.menu = menu;
            this.usr = usr;
            p = new pembayaranView();
            p.getBtnCheckout().setVisible(false);
            p.setVisible(true);
            p.addListener(this);
            pem = new PembayaranModel(usr, p);
        } else if (menu == "ruangan"){
            this.menu = menu;
            this.usr = usr;
            p = new pembayaranView();
            p.getjLabel1().setText("Ruangan Saya");
            p.getLabelTotalHarga().setVisible(false);
            p.getjLabel4().setVisible(false);
            p.getBtnBayar().setVisible(false);
            p.getBtnHapus().setVisible(false);
            p.setVisible(true);
            p.addListener(this);
            pem = new PembayaranModel(usr, p);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (menu == "pembayaran"){
            Object ae = e.getSource();
            if (ae == p.getBtnBack()){
                new pelangganController(usr);
                p.dispose();
            } else if (ae == p.getBtnHapus()){
                pem.cancelOrder(p);
                JOptionPane.showMessageDialog(p, "Pemesanan berhasil dihapus");
                p.dispose();
                new pembayaranController(usr, "pembayaran");
            } else if (ae == p.getBtnBayar()){
                pem.pembayaranRuangan(p);
                p.dispose();
                new pembayaranController(usr, "pembayaran");
            }
        } else if (menu == "ruangan"){
            Object ae = e.getSource();
            if(ae == p.getBtnCheckout()){
                pem.checkout(p);
                JOptionPane.showMessageDialog(p, "Proses checkout berhasil");
                p.dispose();
                new pembayaranController(usr, "ruangan");
            } else if (ae == p.getBtnBack()){
                new pelangganController(usr);
                p.dispose();
            }
        } else if (menu == "manager"){
            Object ae = e.getSource();
            if (ae == p.getBtnBack()){
                new managerController();
                p.dispose();
            } else {
                JOptionPane.showMessageDialog(p, "Terjadi ERROR!!");
            }
        }
    }
}
