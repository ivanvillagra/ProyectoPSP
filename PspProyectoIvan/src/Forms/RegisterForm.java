package Forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class RegisterForm extends JFrame {
    private JButton buttonRegitro;
    private JButton buttonBackLogin;
    private JPanel jpanelPrincipal;
    private JLabel LABELDNI;
    private JLabel LABELAPELLIDO;
    private JLabel LABELNOMBRE;
    private JLabel LABELREGIST;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldDni;

    public RegisterForm(){
        this.setContentPane(jpanelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        buttonBackLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexBankForm in = new indexBankForm();
                in.setVisible(true);
                dispose();
            }


        });
        buttonRegitro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost",6868);
                    DataInputStream is = new DataInputStream(socket.getInputStream());
                    DataOutputStream os = new DataOutputStream(socket.getOutputStream());
                    os.writeUTF("register");
                    String mensaje = is.readUTF();
                    System.out.println(mensaje);
                    socket.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
