package Forms;

import Clases.Acount;
import Clases.User;

import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Random;


public class MovimientosForm  extends JFrame{
    private static User userLogin ;
    private JPanel JPanepPrincipal;
    private JLabel jlabelDni;
    private JLabel jlableName;
    private JComboBox comboBoxCuentas;
    private JTextField textFieldSaldoTotal;
    private JButton buttonIngresar;
    private JFormattedTextField formattedTextFieldSaldoIngreso;
    private JFormattedTextField formattedTextField1;
    private JButton btnRetirar;

    public static void  getAcounts() {
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

            os.writeObject("getAcounts");


            User newUser = userLogin;

            os.writeObject(newUser);
            List <Acount> list = (List<Acount>) is.readObject();
            userLogin.setAcountList(list);
            System.out.println(userLogin.acountList().size());


        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public  void llenarCombox(){
        userLogin.acountList().forEach(acount -> {
            comboBoxCuentas.addItem(acount.iban());
        });
        PintarDatosPrimeraVez();
    }

    public void PintarDatosPrimeraVez(){
        textFieldSaldoTotal.setText(String.valueOf(userLogin.acountList().get(0).balance()));
    }

    public MovimientosForm(User userLogin) {
        this.userLogin = userLogin;
        getAcounts();
        llenarCombox();
        this.setContentPane(JPanepPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        jlabelDni.setText(userLogin.dni());
        jlableName.setText(userLogin.name() + " " + userLogin.surName());
        //textFieldSaldoTotal.setText();
        textFieldSaldoTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
