package atm;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.module.ModuleDescriptor;
import java.sql.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Menu extends JFrame implements ActionListener {

    JLabel title;
    JButton withdraw, changePin, balanceEnq, perINfo, transfer, exit;
    JFrame f;

    public Menu() {

        f = new JFrame("MENU");

        title = new JLabel("SELECT YOU OPTION");
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setBounds(210, 100, 600, 30);
        f.add(title);

        withdraw = new JButton("WITHDRAW");
        withdraw.setFont(new Font("System", Font.BOLD, 18));
        withdraw.setBounds(110, 200, 200, 50);
        withdraw.setBackground(Color.BLACK);
        withdraw.setForeground(Color.WHITE);
        f.add(withdraw);

        balanceEnq = new JButton("BALANCE");
        balanceEnq.setFont(new Font("System", Font.BOLD, 18));
        balanceEnq.setBounds(510, 200, 200, 50);
        balanceEnq.setBackground(Color.BLACK);
        balanceEnq.setForeground(Color.WHITE);
        f.add(balanceEnq);

        changePin = new JButton("CHANGE PIN");
        changePin.setFont(new Font("System", Font.BOLD, 18));
        changePin.setBounds(110, 320, 200, 50);
        changePin.setBackground(Color.BLACK);
        changePin.setForeground(Color.WHITE);
        f.add(changePin);

        transfer = new JButton("TRANSFER");
        transfer.setFont(new Font("System", Font.BOLD, 18));
        transfer.setBounds(510, 320, 200, 50);
        transfer.setBackground(Color.BLACK);
        transfer.setForeground(Color.WHITE);
        f.add(transfer);

        exit = new JButton("LOGOUT");
        exit.setFont(new Font("System", Font.BOLD, 18));
        exit.setBounds(310, 450, 200, 50);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        f.add(exit);

        withdraw.addActionListener(this);
        balanceEnq.addActionListener(this);
        changePin.addActionListener(this);
        transfer.addActionListener(this);
        exit.addActionListener(this);

        f.getContentPane().setBackground(Color.white);
        f.setSize(840, 680);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocation(700, 100);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            f.setVisible(false);
            new Login();
        } else if (e.getSource() == changePin) {
            new ChangePin().setVisible(true);
            f.setVisible(false);
        } else if (e.getSource() == balanceEnq) {
            new Balance().display();
            f.setVisible(false);
        }
        else if (e.getSource() == withdraw) {
            new Withdraw();
            f.setVisible(false);
        }
        else if (e.getSource() == transfer) {
            new Transfer();
            f.setVisible(false);
        }
    }
}
