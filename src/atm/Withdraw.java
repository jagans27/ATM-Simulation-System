package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Withdraw extends JFrame implements ActionListener {

    JFrame f;
    JTextField iamount, ipin;
    JLabel title, lable1, lable2;
    JButton withdraw, clear, goback;

    public Withdraw() {
        f = new JFrame("A T M");

        title = new JLabel("WITHDRAW YOUR AMOUNT");
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setBounds(170, 100, 600, 30);
        f.add(title);

        lable1 = new JLabel("Amount : ");
        lable1.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 22));
        lable1.setBounds(225, 260, 400, 30);
        f.add(lable1);

        lable2 = new JLabel("Pin : ");
        lable2.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 22));
        lable2.setBounds(230, 330, 400, 30);
        f.add(lable2);

        iamount = new JTextField();
        iamount.setBounds(340, 250, 260, 50);
        iamount.setFont(new Font("Arial", Font.BOLD, 25));
        f.add(iamount);

        ipin = new JPasswordField();
        ipin.setBounds(340, 330, 260, 30);
        f.add(ipin);

        withdraw = new JButton("WITHDRAW");
        withdraw.setBackground(Color.BLACK);
        withdraw.setForeground(Color.WHITE);
        withdraw.setBounds(290, 445, 120, 40);
        f.add(withdraw);

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

        withdraw.addActionListener(this);
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
        } else if (e.getSource() == goback) {
            new Menu().setVisible(true);
            f.setVisible(false);
        } else if (e.getSource() == withdraw) {

//            
            try {

                String cardNo = Login.scardNo;
                String pin = Login.spassword;
                String pinNo = ipin.getText();
                String wamount = iamount.getText();
                float amount = Float.parseFloat(wamount);

                Connect con = new Connect();
                String sql = "SELECT * FROM AccHolder WHERE cardno='" + cardNo + "';";
                ResultSet result = con.stmt.executeQuery(sql);

                while (result.next()) {
                    float bal = result.getFloat("balance");

                    if (pinNo.equals(pin)) {
                        if (amount != bal || amount != bal-200) {
                            if (amount < bal) {

                                int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (response == JOptionPane.YES_OPTION) {

                                    bal = bal - amount;

                                    String sql1 = "UPDATE AccHolder SET balance = " + bal + " WHERE cardno = '" + cardNo + "';";
                                    con.stmt.executeUpdate(sql1);
                                    String str = "Your Balance : " + bal;
                                    JOptionPane.showMessageDialog(f, str, "Alert", JOptionPane.WARNING_MESSAGE);
                                    f.setVisible(false);
                                    new Menu().setVisible(true);
                                }else{}

                            }
                            else {
                                
                                JOptionPane.showMessageDialog(f, "Withdraw Amount is Higher than Balanace", "Alert", JOptionPane.WARNING_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(f, "There should be Minimum Balance Should be maintained", "Alert", JOptionPane.WARNING_MESSAGE);

                        }
                    } else {
                        JOptionPane.showMessageDialog(f,"INVALID PIN", "Alert", JOptionPane.WARNING_MESSAGE);
                        
                    }

                }
            } catch (Exception exception) {
                exception.printStackTrace(); 
            }
        }

    }

}
