package edu.softwarica.game.views;

import edu.softwarica.game.utils.Assets;
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

public class Settings implements ActionListener {

    JFrame frame;
    JButton back = new JButton("Back");
    JButton musicToggle = new JButton("Music Enabled");
    Container c;

    public Settings() {
        this.frame = new JFrame("Break The Wall " + " - Settings");
        this.c = this.frame.getContentPane();
        this.c.setLayout(new GridLayout(2, 1));
        this.frame.setSize(new Dimension(720, 360));
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(3);
        this.frame.setLocationRelativeTo(null);
        this.musicToggle.setPreferredSize(new Dimension(720, 180));
        this.back.setPreferredSize(new Dimension(720, 180));
        frame.setIconImage(new ImageIcon(getClass().getResource("/items/icon.png")).getImage());
        back.setFont(new Font("", Font.PLAIN, 80));
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setOpaque(true);
        musicToggle.setFont(new Font("", Font.PLAIN, 80));
        musicToggle.setBackground(Color.black);
        musicToggle.setForeground(Color.white);
        musicToggle.setOpaque(true);

        this.back.setActionCommand("goBack");
        this.musicToggle.setActionCommand("toggleMusic");
        this.c.add(this.musicToggle);
        this.c.add(this.back);
        this.back.addActionListener(this);
        this.musicToggle.addActionListener(this);
        this.frame.pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "goBack") {
            this.frame.setVisible(false);
            MainGame.frame.setVisible(true);
        }
        if (e.getActionCommand() == "toggleMusic") {
            Assets.toggleMusic();
            if (!Assets.canPlay) {
                this.musicToggle.setText("Music not enabled");
            } else {
                this.musicToggle.setText("Music enabled");
            }
        }
    }
}
