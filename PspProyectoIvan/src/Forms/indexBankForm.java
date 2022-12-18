package Forms;

import Bdd.BdKutxaBank;
import Clases.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionListener; // seems to be missing.
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket socket = null;
                try {
                    socket = new Socket("localhost",6868);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    assert socket != null;
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

                    Pattern pattern1 = Pattern.compile("[0-9]{8}[A-Za-z]{1}", Pattern.CASE_INSENSITIVE);
                    Matcher matcher1 = pattern1.matcher(textFieldNIF.getText());
                    boolean matchFound = matcher1.find();

                    Pattern pattern2 = Pattern.compile("[0-9]{6}", Pattern.CASE_INSENSITIVE);
                    Matcher matcher2 = pattern2.matcher(jpassword.getText());
                    boolean matchFound2 = matcher2.find();


                    os.writeObject("login");


                    if(matchFound && matchFound2) {
                        User newUser = new User( textFieldNIF.getText() , jpassword.getText());

                        os.writeObject(newUser);
                        User loginUser = (User)is.readObject();
                        if(loginUser!=null){

                            socket.close();
                            MovimientosForm mv = new MovimientosForm(loginUser);
                            mv.setVisible(true);
                            dispose();


                        }else {
                            JOptionPane.showMessageDialog(null,"Error al iniciar sesion");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Error al iniciar sesion");
                    }



                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {

        indexBankForm f = new indexBankForm();
        f.setVisible(true);

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







