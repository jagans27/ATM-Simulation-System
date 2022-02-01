package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Transfer extends JFrame implements ActionListener {

    JFrame f;
    JTextField iacc, iamount, ipin;
    JLabel title, lable1, lable2, lable3;
    JButton transfer, clear, goback;

    public Transfer() {
        f = new JFrame("Transfer Amount");

        title = new JLabel("TRANSFER YOUR AMOUNT");
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setBounds(170, 100, 600, 30);
        f.add(title);

        lable3 = new JLabel("Enter Acc No : ");
        lable3.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 22));
        lable3.setBounds(215, 200, 400, 30);
        f.add(lable3);

        lable1 = new JLabel("Amount : ");
        lable1.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 22));
        lable1.setBounds(215, 260, 400, 30);
        f.add(lable1);

        lable2 = new JLabel("Pin : ");
        lable2.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 22));
        lable2.setBounds(215, 330, 400, 30);
        f.add(lable2);

        iacc = new JTextField();
        iacc.setBounds(380, 200, 260, 30);
        f.add(iacc);

        iamount = new JTextField();
        iamount.setBounds(380, 250, 260, 50);
        iamount.setFont(new Font("Arial", Font.BOLD, 25));
        f.add(iamount);

        ipin = new JPasswordField();
        ipin.setBounds(380, 330, 260, 30);
        f.add(ipin);

        transfer = new JButton("TRANSFER");
        transfer.setBackground(Color.BLACK);
        transfer.setForeground(Color.WHITE);
        transfer.setBounds(290, 445, 120, 40);
        f.add(transfer);

        clear = new JButton("CLEAR");
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.setBounds(440, 445, 120, 40);
        f.add(clear);

        goback = new JButton("<- GO BACK");
        goback.setBackground(Color.BLACK);
        goback.setForeground(Color.WHITE);
        goback.setBounds(365, 515, 120, 40);
        f.add(goback);

        transfer.addActionListener(this);
        clear.addActionListener(this);
        goback.addActionListener(this);

        f.getContentPane().setBackground(Color.white);
        f.setSize(840, 680);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocation(700, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == clear) {
            iamount.setText("");
            ipin.setText("");
            iacc.setText("");
        } else if (e.getSource() == goback) {
            new Menu().setVisible(true);
            f.setVisible(false);
        } else if (e.getSource() == transfer) {
            try {
                String cardNo = Login.scardNo;
                String pin = Login.spassword;
                String pinNo = ipin.getText();
                String tacc = iacc.getText();
                String wamount = iamount.getText();
                float amount = Float.parseFloat(wamount);

                String sql_Balance_Retrive = "SELECT * FROM AccHolder WHERE cardno = '" + cardNo + "';";
                String sql_for_Check_AccNo = "SELECT * FROM AccHolder WHERE accno = '" + tacc + "';";

                Connect con = new Connect();
                ResultSet result = con.stmt.executeQuery(sql_Balance_Retrive);

                while (result.next()) {
                    float bal = result.getFloat("balance");

                    if (pinNo.equals(pin)) {
                        System.out.println("The Pin was matched ");
                        if (amount != bal || amount != bal - 200) {
                            if (amount < bal) {
                                ResultSet result1 = con.stmt.executeQuery(sql_for_Check_AccNo);
                                if (result1.next()) {

                                    float recbal = result1.getFloat("balance");
                                    String raccno = result1.getString("accno");
                                    String n = result1.getString("name");

                                    if (tacc.equals(raccno)) {
                                        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue Transfer ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                        if (response == JOptionPane.YES_OPTION) {

                                            bal = bal - amount;
                                            recbal = recbal + amount;
                                            String sql1 = "UPDATE AccHolder SET balance = " + bal + " WHERE cardno = '" + cardNo + "';";
                                            String sql2 = "UPDATE AccHolder SET balance = " + recbal + " WHERE accno = '" + raccno + "';";
                                            con.stmt.executeUpdate(sql1);
                                            con.stmt.executeUpdate(sql2);
                                            String str = "Successfully Transfered to " + n + " ! \n Your Balance : " + bal;
                                            JOptionPane.showMessageDialog(f, str, "Alert", JOptionPane.WARNING_MESSAGE);
                                            new Menu().setVisible(true);
                                            f.setVisible(false);
                                        } else {
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(f, "ACCOUNT NOT FOUND", "Alert", JOptionPane.WARNING_MESSAGE);
                                    }
                                } else {
                                }
                            } else {
                                JOptionPane.showMessageDialog(f, "INSUFFICIENT BALANCE", "Alert", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(f, "MIN BALANCE SHOULD BE MAINTAINED", "Alert", JOptionPane.WARNING_MESSAGE);

                        }

                    } else {
                        JOptionPane.showMessageDialog(f, "INVALID PIN", "Alert", JOptionPane.WARNING_MESSAGE);

                    }
                }

            } catch (Exception exp) {
                exp.printStackTrace(); 
            }

        }
    }


}
