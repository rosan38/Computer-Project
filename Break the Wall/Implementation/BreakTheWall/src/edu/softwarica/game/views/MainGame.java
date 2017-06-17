/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.softwarica.game.views;

import edu.softwarica.game.game.Game;
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

/**
 *
 * @author rosan
 */
public class MainGame implements ActionListener {

    static Game game;
    static JButton start;
    static JButton settings;
    static JButton score;
    static JButton help;
    static JButton logout;
    static JFrame frame;
    static Container c;
    int id;

    public MainGame(int id) {
        this.id = id;
        BufferedImage startImage = null;
        BufferedImage settingsImage = null;
        BufferedImage scoreImage = null;
        BufferedImage helpImage = null;
        BufferedImage LogoutImage = null;
        try {
            startImage = ImageIO.read(getClass().getResource("/items/start.png"));
            scoreImage = ImageIO.read(getClass().getResource("/items/score.png"));
            settingsImage = ImageIO.read(getClass().getResource("/items/settings.png"));
            helpImage = ImageIO.read(getClass().getResource("/items/help.png"));
            LogoutImage = ImageIO.read(getClass().getResource("/items/logout.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame = new JFrame("Break The Wall");
        frame.setResizable(false);
        frame.setSize(new Dimension(680, 600));
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(getClass().getResource("/items/icon.png")).getImage());
        c = frame.getContentPane();
        c.setLayout(new GridLayout(5, 1));
        start = new JButton();
        score = new JButton();
        settings = new JButton();
        help = new JButton();
        logout = new JButton();
        start.setPreferredSize(new Dimension(680, 120));
        settings.setPreferredSize(new Dimension(680, 120));
        score.setPreferredSize(new Dimension(680, 120));
        help.setPreferredSize(new Dimension(680, 120));
        logout.setPreferredSize(new Dimension(680, 120));
        start.addActionListener(this);
        score.addActionListener(this);
        settings.addActionListener(this);
        help.addActionListener(this);
        logout.addActionListener(this);
        start.setActionCommand("Start");
        score.setActionCommand("Score");
        settings.setActionCommand("Settings");
        help.setActionCommand("Help");
        logout.setActionCommand("Logout");
        start.setIcon(new ImageIcon(startImage));
        score.setIcon(new ImageIcon(scoreImage));
        settings.setIcon(new ImageIcon(settingsImage));
        help.setIcon(new ImageIcon(helpImage));
        logout.setIcon(new ImageIcon(LogoutImage));
        c.add(start);
        c.add(score);
        c.add(settings);
        c.add(help);
        c.add(logout);
        frame.pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Start") {
            frame.setVisible(false);
            game = new Game(1440, 720, id);
            game.start();
        }
        if (e.getActionCommand() == "Score") {
            frame.setVisible(false);
            ScoreList scorelist = new ScoreList(id);
        }
        if (e.getActionCommand() == "Settings") {
            frame.setVisible(false);
            Settings settings = new Settings();
        }
        if (e.getActionCommand() == "Help") {
            frame.setVisible(false);
            Help help = new Help();
        }
        if (e.getActionCommand() == "Logout") {
            frame.setVisible(false);
            BreakTheWall btw = new BreakTheWall();
        }
    }
}
