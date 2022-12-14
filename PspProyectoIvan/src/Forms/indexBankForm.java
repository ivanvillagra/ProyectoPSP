package Forms;

import Bdd.BdKutxaBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionListener; // seems to be missing.


public class indexBankForm extends JFrame {
    private JPasswordField jpassword;
    private JLabel jlabelNif;
    private JLabel jlabelTitle;
    private JPanel jPanelContainer;
    private JPanel jPanelContainer2;
    private JPanel jPanelContainer3;
    private JPanel jPanelContainer4;
    private JTextField textFieldNIF;
    private JLabel CLAVE;
    private JButton buttonDel;
    private JButton btnIniciar;
    private JButton buttonRegistrarse;

    public indexBankForm() {
        this.setContentPane(jPanelContainer);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        jPanelContainer4.setLayout(new FlowLayout(FlowLayout.CENTER,12,12));
        this.generateButtons();

        buttonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = jpassword.getText();
                if ((code != null) && (code.length() > 0)) {
                    jpassword.setText(code.substring(0, code.length() - 1));
                }
            }
        });
        buttonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm r = new RegisterForm();
                r.setVisible(true);
                dispose();

            }
        });
    }


    public static void main(String[] args) {

        indexBankForm f = new indexBankForm();
        f.setVisible(true);
        Connection bdConnection =   BdKutxaBank.Conection();

        try {
            System.out.println(bdConnection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void generateButtons() {

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i <= 9; i++) {
            list.add(i);
        }

        Collections.shuffle(list);

         list.forEach(integer -> {
            JButton jb = new JButton(integer + "btn");
            jb.setMargin(new Insets(1, 1, 1, 1));
            jb.setBackground(Color.WHITE);
            jb.setText(integer + "");
            jb.setPreferredSize(new Dimension(40, 40));
            jb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (jpassword.getText().length() <6) {

                        jpassword.setText(jpassword.getText()+integer + "");

}
                }
            });

            jPanelContainer4.add(jb);



         });
    }
}







