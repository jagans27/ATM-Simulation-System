package atm;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Window.*;
import java.lang.module.ModuleDescriptor;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Login extends JFrame implements ActionListener {

    JLabel title, cardNo, password;
    JTextField icardNo;
    JPasswordField ipassword;
    JButton signin, clear;
    JFrame f;
    static String scardNo, spassword;

    Login() {
        f = new JFrame("A T M");

        title = new JLabel("WELCOME  TO  ATM");
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setBounds(210, 100, 600, 30);
        f.add(title);

        cardNo = new JLabel("Card No. : ");
        cardNo.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        cardNo.setBounds(225, 250, 400, 30);
        f.add(cardNo);

        password = new JLabel("Password : ");
        password.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        password.setBounds(220, 330, 400, 30);
        f.add(password);

        icardNo = new JTextField();
        icardNo.setBounds(340, 250, 260, 30);
        f.add(icardNo);

        ipassword = new JPasswordField();
        ipassword.setBounds(340, 330, 260, 30);
        f.add(ipassword);

        signin = new JButton("SIGN IN");
        signin.setBackground(Color.BLACK);
        signin.setForeground(Color.WHITE);
        signin.setBounds(290, 475, 120, 40);
        f.add(signin);

        clear = new JButton("CLEAR");
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.setBounds(440, 475, 120, 40);
        f.add(clear);

        signin.addActionListener(this);
        clear.addActionListener(this);

        f.getContentPane().setBackground(Color.white);
        f.setSize(840, 680);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocation(700, 100);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            if (e.getSource() == signin) {

                Connect con = new Connect();
                scardNo = icardNo.getText();
                spassword = ipassword.getText();

                if (scardNo.equals("") || spassword.equals("")) {
                    JOptionPane.showMessageDialog(f, "Please Fill the Fields", "Alert", JOptionPane.WARNING_MESSAGE);
                }

                String sql = "SELECT * FROM AccHolder WHERE cardno = '" + scardNo + "' AND pin = " + spassword + ";";
                ResultSet result = con.stmt.executeQuery(sql);

                if (result.next()) {
                    new Menu().setVisible(true);
                    f.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(f, "INVALID PIN OR CARD NUMBER", "Alert", JOptionPane.WARNING_MESSAGE);
                }

            } else if (e.getSource() == clear) {
                icardNo.setText("");
                ipassword.setText("");
            }

        } catch (Exception exp) {
            exp.printStackTrace(); 
        }

    }

}
