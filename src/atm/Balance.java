package atm;

import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Balance extends JFrame implements ActionListener {

    JFrame f;
    JLabel title, ph, balance, accNO, cardNo, name;
    JPanel bPanel;
    JButton goback;

    public Balance() {
        f = new JFrame("Balance Enquiry");

        goback = new JButton("BACK TO MENU");
        goback.setBackground(Color.BLACK);
        goback.setForeground(Color.WHITE);
        goback.setBounds(330, 505, 180, 40);
        f.add(goback);

        goback.addActionListener(this);

        f.getContentPane().setBackground(Color.WHITE);
        f.setSize(840, 680);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocation(700, 100);
    }

    void display() {

        try {
            String cn = Login.scardNo;
            Connect con = new Connect();
            String sql = "SELECT * FROM AccHolder WHERE cardno='" + cn + "';";
            ResultSet result = con.stmt.executeQuery(sql);

            while (result.next()) {
                String n = result.getString("name");
                String acc = result.getString("accno");
                String card = result.getString("cardno");
                String phno = result.getString("phno");
                float bal = result.getFloat("balance");

                title = new JLabel("YOUR BALANCE DETAILS");
                title.setFont(new Font("System", Font.LAYOUT_LEFT_TO_RIGHT, 38));
                title.setForeground(Color.red);
                title.setBounds(175, 60, 500, 100);
                f.add(title);

                name = new JLabel("Name : " + n);
                name.setFont(new Font("System", Font.LAYOUT_LEFT_TO_RIGHT, 25));
                name.setBounds(200, 150, 500, 100);
                f.add(name);

                String cno = "1234567890112345";
                accNO = new JLabel("Account No. : xxxx-xxxx-" + (acc.substring(8)));
                accNO.setFont(new Font("System", Font.LAYOUT_LEFT_TO_RIGHT, 25));
                accNO.setBounds(200, 200, 500, 100);
                f.add(accNO);

                cardNo = new JLabel("Card No. : xxxx-xxxx-xxxx-" + (card.substring(12)));
                cardNo.setFont(new Font("System", Font.LAYOUT_LEFT_TO_RIGHT, 25));
                cardNo.setBounds(200, 250, 500, 100);
                f.add(cardNo);

                ph = new JLabel("Ph No. : xxxxx " + (phno.substring(5)));
                ph.setFont(new Font("System", Font.LAYOUT_LEFT_TO_RIGHT, 22));
                ph.setBounds(200, 300, 500, 100);
                f.add(ph);

                balance = new JLabel("Balance : " + bal);
                balance.setFont(new Font("System", Font.LAYOUT_LEFT_TO_RIGHT, 25));
                balance.setBounds(200, 350, 500, 100);
                f.add(balance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goback) {
            new Menu().setVisible(true);
            f.setVisible(false);

        }
    }

}
