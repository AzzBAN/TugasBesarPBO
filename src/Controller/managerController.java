package Controller;

import View.ManagerView;

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
            m.dispose();
        } else if (ae == m.getBtnTambahRuangan()){

        } else if (ae == m.getBtnEditRuangan()){

        } else if (ae == m.getBtnLihatRuangan()){

        }
    }
}
