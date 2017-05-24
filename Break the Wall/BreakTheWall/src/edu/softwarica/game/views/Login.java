/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.softwarica.game.views;

import edu.softwarica.game.controller.ProfileController;
import edu.softwarica.game.model.Profile;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author rosan
 */
public class Login extends JFrame implements ActionListener {

    static JButton login, back;
    static JLabel lblemail, lblpass, lbllogin;
    static JTextField txtemail;
    static JPasswordField txtpass;
    static JFrame frame;
    static Container c;

    public Login() {
        frame = new JFrame("Break The Wall");
        frame.setResizable(false);
        frame.setSize(new Dimension(660, 360));
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(getClass().getResource("/items/icon.png")).getImage());
        c = frame.getContentPane();
        frame.getContentPane().setBackground(Color.BLACK);
        c.setLayout(null);

        lbllogin = new JLabel("Login");
        lbllogin.setBounds(290, 0, 100, 50);
        lbllogin.setForeground(Color.white);
        lbllogin.setFont(new Font("", Font.PLAIN, 40));

        lblemail = new JLabel("Email:");
        lblemail.setBounds(100, 80, 100, 50);
        lblemail.setForeground(Color.white);
        lblemail.setFont(new Font("", Font.PLAIN, 20));

        lblpass = new JLabel("Password:");
        lblpass.setBounds(100, 150, 100, 50);
        lblpass.setForeground(Color.white);
        lblpass.setFont(new Font("", Font.PLAIN, 20));

        txtemail = new JTextField();
        txtemail.setBounds(300, 80, 200, 50);
        txtemail.setFont(new Font("", Font.PLAIN, 20));

        txtpass = new JPasswordField();
        txtpass.setBounds(300, 150, 200, 50);
        txtpass.setFont(new Font("", Font.PLAIN, 20));

        login = new JButton("Login");
        login.setBounds(110, 250, 100, 50);
        login.setFont(new Font("", Font.PLAIN, 20));
        login.setBackground(Color.black);
        login.setForeground(Color.white);

        back = new JButton("Back");
        back.setBounds(350, 250, 100, 50);
        back.setFont(new Font("", Font.PLAIN, 20));
        back.setBackground(Color.black);
        back.setForeground(Color.white);

        login.addActionListener(this);
        back.addActionListener(this);
        login.setActionCommand("Login");
        back.setActionCommand("Back");
        c.add(lbllogin);
        c.add(lblemail);
        c.add(lblpass);
        c.add(txtemail);
        c.add(txtpass);
        c.add(login);
        c.add(back);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Login") {
            String email = txtemail.getText();
            String password = String.valueOf(txtpass.getPassword());
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email can't be empty");
            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Password can't be empty");
            } else {
                Profile pro = new Profile();
                pro.setEmail(email);
                pro.setPassword(password);
                ProfileController proController = new ProfileController();
                boolean isAuthenticate = proController.authenticate(pro);
                if (isAuthenticate) {
                    frame.setVisible(false);
                    int id = proController.getUserId(pro);
                    MainGame maingame = new MainGame(id);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Email/Password.");
                }
            }
        }
        if (e.getActionCommand() == "Back") {
            frame.setVisible(false);
            BreakTheWall btw = new BreakTheWall();
        }
    }
}
