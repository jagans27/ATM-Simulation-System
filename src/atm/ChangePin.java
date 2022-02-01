package atm;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Window.*;
import java.lang.module.ModuleDescriptor;
import java.sql.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JTextField.*;

public class ChangePin extends JFrame implements ActionListener {

    JLabel title, oldpin, newpin;
    JPasswordField ioldpin, inewpin;
    JButton change, clear, goback;
    JFrame f;

    public ChangePin() {

        f = new JFrame("Change PIN Number");

        title = new JLabel("CHANGE PIN NUMBER");
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setBounds(210, 100, 600, 30);
        f.add(title);

        oldpin = new JLabel("Enter Old pin : ");
        oldpin.setFont(new Font("Arial", Font.PLAIN, 18));
        oldpin.setBounds(200, 250, 400, 30);
        f.add(oldpin);

        newpin = new JLabel("Enter new pin : ");
        newpin.setFont(new Font("Arial", Font.PLAIN, 18));
        newpin.setBounds(195, 330, 400, 30);
        f.add(newpin);

        ioldpin = new JPasswordField(4);
        ioldpin.setBounds(340, 250, 260, 30);
        f.add(ioldpin);

        inewpin = new JPasswordField(1);
        inewpin.setBounds(340, 330, 260, 30);
        f.add(inewpin);

        change = new JButton("CHANGE");
        change.setBackground(Color.BLACK);
        change.setForeground(Color.WHITE);
        change.setBounds(300, 435, 100, 35);
        f.add(change);

        clear = new JButton("CLEAR");
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.setBounds(430, 435, 100, 35);
        f.add(clear);

        goback = new JButton("BACK TO MENU");
        goback.setBackground(Color.WHITE);
        goback.setForeground(Color.BLACK);
        goback.setBounds(330, 505, 180, 40);
        f.add(goback);

        change.addActionListener(this);
        clear.addActionListener(this);
        goback.addActionListener(this);

        f.getContentPane().setBackground(Color.WHITE);
        f.setSize(840, 680);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocation(700, 100);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == change) {
            try {
                String cardNo = Login.scardNo;
                String pin = Login.spassword;
                String oldPin = ioldpin.getText();
                String newPin = inewpin.getText();
                if (!oldPin.equals("") || !newPin.equals("")) {
                    if (oldPin.equals(pin)) {
                        if (oldPin != newPin) {
                            if (newPin.length() == 4) {

                                int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                                if (response == JOptionPane.YES_OPTION) {
                                    Connect con = new Connect();
                                    String sql = "UPDATE AccHolder SET pin = " + newPin + " WHERE cardno = '" + cardNo + "';";
                                    con.stmt.executeUpdate(sql);
                                    Login.spassword = newPin;
                                    JOptionPane.showMessageDialog(f, "Your PIN changed successfully", "Alert", JOptionPane.WARNING_MESSAGE);
                                    f.setVisible(false);
                                    new Menu().setVisible(true);

                                } else {
                                }

                            } else {
                                JOptionPane.showMessageDialog(f, "Only 4-Digit allowed", "Alert", JOptionPane.WARNING_MESSAGE);

                            }
                        } else {
                            JOptionPane.showMessageDialog(f, "You Entered the same PIN", "Alert", JOptionPane.WARNING_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(f, "Your PIN Not matched", "Alert", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(f, "Please Fill the fields", "Alert", JOptionPane.WARNING_MESSAGE);
                }

            } catch (Exception exception) {
                exception.printStackTrace(); 
            }

        } else if (e.getSource() == clear) {
            ioldpin.setText("");
            inewpin.setText("");
        } else if (e.getSource() == goback) {
            new Menu().setVisible(true);
            f.setVisible(false);

        }

    }

}
