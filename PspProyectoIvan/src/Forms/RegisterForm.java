package Forms;

import Clases.FClient;
import Clases.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                    Pattern pattern1 = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);
                    Matcher matcher1 = pattern1.matcher(textFieldNombre.getText());
                    boolean matchFound = matcher1.find();

                    Pattern pattern2 = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);
                    Matcher matcher2 = pattern2.matcher(textFieldApellido.getText());
                    boolean matchFound2 = matcher2.find();

                    Pattern pattern3 = Pattern.compile("[0-9]{8}[A-Za-z]{1}", Pattern.CASE_INSENSITIVE);
                    Matcher matcher3 = pattern3.matcher(textFieldDni.getText());
                    boolean matchFound3 = matcher3.find();

                    os.writeObject("register");

                    boolean firmado = FClient.fClient(is);

                    if(firmado){
                        JOptionPane.showMessageDialog(null,"Kutxabank firma digital correcta se puede registrar");
                        if(matchFound && matchFound2 && matchFound3) {
                            Random rnd = new Random();
                            String pass = String.format("%06d", rnd.nextInt(999999));
                            User newUser = new User(textFieldNombre.getText(),textFieldDni.getText(), textFieldApellido.getText(), pass);

                            os.writeObject(newUser);
                            if((boolean)is.readObject()){

                                socket.close();
                                indexBankForm inx = new indexBankForm();
                                inx.setVisible(true);
                                dispose();

                            }else {
                                JOptionPane.showMessageDialog(null,"Error al registrar");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,"Error al registrar");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"No se pudo Registrar error en la Firma digital de kutxabank intentelo de nuevo");
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

}
