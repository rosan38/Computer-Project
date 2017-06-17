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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Register extends JFrame implements ActionListener {

    static JButton register, back;
    static JLabel lblemail, lblpass, lblregister, lblfname, lbllname, lbladdress;
    static JTextField txtemail, txtfname, txtlname, txtaddress;
    static JPasswordField txtpass;
    static JFrame frame;
    static Container c;

    public Register() {

        frame = new JFrame("Break The Wall");
        frame.setResizable(false);
        frame.setSize(new Dimension(660, 460));
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(getClass().getResource("/items/icon.png")).getImage());
        c = frame.getContentPane();
        frame.getContentPane().setBackground(Color.BLACK);
        c.setLayout(null);

        lblregister = new JLabel("Registration");
        lblregister.setBounds(250, 0, 300, 50);
        lblregister.setForeground(Color.white);
        lblregister.setFont(new Font("", Font.PLAIN, 40));

        lblfname = new JLabel("First Name:");
        lblfname.setBounds(100, 70, 130, 40);
        lblfname.setForeground(Color.white);
        lblfname.setFont(new Font("", Font.PLAIN, 20));

        lbllname = new JLabel("Last Name:");
        lbllname.setBounds(100, 120, 130, 40);
        lbllname.setForeground(Color.white);
        lbllname.setFont(new Font("", Font.PLAIN, 20));

        lblemail = new JLabel("Email:");
        lblemail.setBounds(100, 170, 130, 50);
        lblemail.setForeground(Color.white);
        lblemail.setFont(new Font("", Font.PLAIN, 20));

        lblpass = new JLabel("Password:");
        lblpass.setBounds(100, 220, 130, 50);
        lblpass.setForeground(Color.white);
        lblpass.setFont(new Font("", Font.PLAIN, 20));

        lbladdress = new JLabel("Address:");
        lbladdress.setBounds(100, 270, 130, 50);
        lbladdress.setForeground(Color.white);
        lbladdress.setFont(new Font("", Font.PLAIN, 20));

        txtfname = new JTextField();
        txtfname.setBounds(300, 70, 200, 30);
        txtfname.setFont(new Font("", Font.PLAIN, 20));

        txtlname = new JTextField();
        txtlname.setBounds(300, 120, 200, 30);
        txtlname.setFont(new Font("", Font.PLAIN, 20));

        txtemail = new JTextField();
        txtemail.setBounds(300, 170, 200, 30);
        txtemail.setFont(new Font("", Font.PLAIN, 20));

        txtpass = new JPasswordField();
        txtpass.setBounds(300, 220, 200, 30);
        txtpass.setFont(new Font("", Font.PLAIN, 20));

        txtaddress = new JTextField();
        txtaddress.setBounds(300, 270, 200, 30);
        txtaddress.setFont(new Font("", Font.PLAIN, 20));

        register = new JButton("Register");
        register.setBounds(110, 350, 120, 50);
        register.setFont(new Font("", Font.PLAIN, 20));
        register.setBackground(Color.black);
        register.setForeground(Color.white);

        back = new JButton("Back");
        back.setBounds(350, 350, 120, 50);
        back.setFont(new Font("", Font.PLAIN, 20));
        back.setBackground(Color.black);
        back.setForeground(Color.white);

        register.addActionListener(this);
        back.addActionListener(this);
        register.setActionCommand("Register");
        back.setActionCommand("Back");
        c.add(lblregister);
        c.add(lblfname);
        c.add(lbllname);
        c.add(lblemail);
        c.add(lblpass);
        c.add(lbladdress);
        c.add(txtfname);
        c.add(txtlname);
        c.add(txtemail);
        c.add(txtpass);
        c.add(txtaddress);
        c.add(register);
        c.add(back);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Register") {
            char[] pass = txtpass.getPassword();
            String passwrd = String.valueOf(pass);
            String fname = txtfname.getText();
            String lname = txtlname.getText();
            String address = txtaddress.getText();
            String email = txtemail.getText();
            Profile profile = new Profile();
            profile.setFirstName(fname);
            profile.setLastName(lname);
            profile.setEmail(email);
            profile.setPassword(passwrd);
            profile.setAddress(address);
            if (fname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "First Name can't be empty");
            } else if (lname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Last Name can't be empty");
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email can't be empty");
            } else if (passwrd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Password can't be empty");
            } else if (address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Address can't be empty");
            } else {
                ProfileController pc = new ProfileController();
                try {
                    pc.addProfile(profile);
                } catch (SQLException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                }
                reset();
            }
        }
        if (e.getActionCommand() == "Back") {
            frame.setVisible(false);
            BreakTheWall btw = new BreakTheWall();
        }
    }

    private void reset() {
        txtfname.setText("");
        txtlname.setText("");
        txtemail.setText("");
        txtpass.setText("");
        txtaddress.setText("");
    }
}
