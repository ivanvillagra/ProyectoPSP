package Forms;

import Clases.User;

import javax.swing.*;

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

    public MovimientosForm(User userLogin) {
        this.userLogin = userLogin;
        this.setContentPane(JPanepPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        jlabelDni.setText(userLogin.dni());
        jlableName.setText(userLogin.name() + " " + userLogin.surName());
    }
}
