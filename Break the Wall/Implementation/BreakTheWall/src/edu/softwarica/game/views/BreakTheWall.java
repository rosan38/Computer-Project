package edu.softwarica.game.views;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BreakTheWall implements ActionListener {

    static JButton login;
    static JButton register;
    static JFrame frame;
    static Container c;

    public BreakTheWall() {
        BufferedImage startImage = null;
        BufferedImage settingsImage = null;
        try {
            startImage = ImageIO.read(getClass().getResource("/items/login.png"));
            settingsImage = ImageIO.read(getClass().getResource("/items/register.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame = new JFrame("Break The Wall");
        frame.setResizable(false);
        frame.setSize(new Dimension(720, 360));
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(getClass().getResource("/items/icon.png")).getImage());
        c = frame.getContentPane();
        c.setLayout(new GridLayout(2, 1));
        login = new JButton();
        register = new JButton();
        login.setPreferredSize(new Dimension(720, 180));
        register.setPreferredSize(new Dimension(720, 180));
        login.addActionListener(this);
        register.addActionListener(this);
        login.setActionCommand("Login");
        register.setActionCommand("Register");
        login.setIcon(new ImageIcon(startImage));
        register.setIcon(new ImageIcon(settingsImage));
        c.add(login);
        c.add(register);
        frame.pack();
    }

    public static void main(String[] args) {
        BreakTheWall btw = new BreakTheWall();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Login") {
            frame.setVisible(false);
            Login login1 = new Login();
        }
        if (e.getActionCommand() == "Register") {
            frame.setVisible(false);
            Register register1 = new Register();
        }
    }
}
