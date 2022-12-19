package Forms;

import Clases.Acount;
import Clases.Seguridad;
import Clases.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MovimientosForm  extends JFrame{
    private static User userLogin ;
    private JPanel JPanepPrincipal;
    private JLabel jlabelDni;
    private JLabel jlableName;
    private JComboBox comboBoxCuentas;
    private JTextField textFieldSaldoTotal;
    private JButton buttonIngresar;
    private JFormattedTextField formattedTextFieldSaldoIngreso;
    private JTextField textFieldIbanDestino;
    private JButton buttonUpdate;

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
        comboBoxCuentas.removeAllItems();
        userLogin.acountList().forEach(acount -> {
            comboBoxCuentas.addItem(acount.iban());
        });
        PintarDatosPrimeraVez();
    }

    public void PintarDatosPrimeraVez(){
        textFieldSaldoTotal.setText(String.valueOf(userLogin.acountList().get(0).balance()));
    }

    public void LimpiarCampos(){
        formattedTextFieldSaldoIngreso.setText("");
        textFieldIbanDestino.setText("");
    };

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

                    os.writeObject("ingreso");
                    Seguridad sgr =  (Seguridad) is.readObject();

                    Pattern pattern1 = Pattern.compile("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);
                    Matcher matcher1 = pattern1.matcher(textFieldIbanDestino.getText());
                    boolean matchFound = matcher1.find();

                    Pattern pattern2 = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
                    Matcher matcher2 = pattern2.matcher(formattedTextFieldSaldoIngreso.getText());
                    boolean matchFound2 = matcher2.find();

                    if (matchFound && matchFound2){

                        os.writeObject(sgr.encriptar(comboBoxCuentas.getSelectedItem().toString()));
                        os.writeObject(sgr.encriptar(textFieldIbanDestino.getText()));
                        os.writeObject(sgr.encriptar(formattedTextFieldSaldoIngreso.getText()));
                        boolean response = (boolean) is.readObject();

                        if (response){
                            socket.close();
                            getAcounts();
                            llenarCombox();
                        }else {

                            JOptionPane.showMessageDialog(null,"Error  al  ingresar");
                        }
                    }else {

                        JOptionPane.showMessageDialog(null,"Error al ingresar, datos erroneos");
                    }

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                LimpiarCampos();

            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAcounts();
                llenarCombox();
            }
        });
    }
}
