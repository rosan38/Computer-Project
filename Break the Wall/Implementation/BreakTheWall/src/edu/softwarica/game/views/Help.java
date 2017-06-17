/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.softwarica.game.views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author rosan
 */
public class Help extends JFrame implements ActionListener {

    static JFrame frame;
    static JLabel help;
    static Container c;
    JButton back = new JButton("Back");

    public Help() {

        frame = new JFrame("Break The Wall");
        frame.setResizable(false);
        frame.setSize(new Dimension(720, 360));
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(getClass().getResource("/items/icon.png")).getImage());
        c = frame.getContentPane();
        c.setLayout(new GridLayout(2, 1));
        String h = "→ = Front Movement || "
                + "← = Backward Movement || "
                + "↑ = Jump";
        help = new JLabel(h);
        help.setFont(new Font("", Font.PLAIN, 25));
        help.setBackground(Color.black);
        help.setForeground(Color.white);
        help.setOpaque(true);

        this.help.setPreferredSize(new Dimension(720, 180));
        this.back.setPreferredSize(new Dimension(720, 180));
        back.setFont(new Font("", Font.PLAIN, 80));
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setOpaque(true);
        this.back.setActionCommand("goBack");
        this.back.addActionListener(this);
        this.c.add(this.help);
        this.c.add(this.back);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "goBack") {
            this.frame.setVisible(false);
            MainGame.frame.setVisible(true);
        }

    }
}
