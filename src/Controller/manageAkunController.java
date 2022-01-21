package Controller;

import Model.userModel;
import View.daftarView;
import View.lihatRuangan;
import Object.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class manageAkunController implements ActionListener, MouseListener {

    lihatRuangan l;
    userModel u;
    private String nama, username, password, posisi, tglLahir;

    public manageAkunController() {
        l = new lihatRuangan();
        l.getjLabelharga().setVisible(false);
        l.getjLabel1().setText("Manage Akun");
        l.getjLabelnama().setText("Nama               :");
        l.getjLabellokasi().setText("Tanggal lahir   :");
        l.getjLabelRating().setText("Username        :");
        l.getjLabelLuas().setText("Password         :");
        l.getjLabelfasilitas().setText("posisi               :");
        l.setVisible(true);
        l.addListener(this);
        l.addMouseListener(this);
        u = new userModel("admin", l);
    }

    public user editUser(){
        user usr = new user(this.nama, username, password, posisi, tglLahir, 0);
        return usr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if (ae == l.getBtnBack()){
            new adminController();
            l.dispose();
        } else if (ae == l.getBtnHapus()) {
            if (l.getjList1().isSelectionEmpty() || l.getLabelNama().getText().isEmpty()){
                JOptionPane.showMessageDialog(l, "Anda belum memilih user");
            } else {
                u.deleteAccount(l.getLabelNama().getText());
                JOptionPane.showMessageDialog(l, "Proses Hapus Akun Berhasil");
                l.dispose();
                new manageAkunController();
            }
        } else if (ae == l.getBtnEdit()) {
            if (l.getjList1().isSelectionEmpty() || l.getLabelNama().getText().isEmpty()){
                JOptionPane.showMessageDialog(l, "Anda belum memilih user");
            } else {
                this.nama = l.getLabelNama().getText();
                this.tglLahir = l.getLabelLokasi().getText();
                this.username = l.getLabelRating().getText();
                this.password = l.getLabelLuas().getText();
                this.posisi = l.getLabelFasilitas().getText();
                user usr = editUser();
                new editController(usr);
                l.dispose();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            u.displayUserData(l);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
