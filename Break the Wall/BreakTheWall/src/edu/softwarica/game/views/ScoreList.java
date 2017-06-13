/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.softwarica.game.views;

import edu.softwarica.game.controller.ScoreController;
import edu.softwarica.game.model.Score;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author rosan
 */
public class ScoreList extends JFrame implements ActionListener {

    static JButton back;
    static JLabel lblscore, lblscore1, lblscore2, lblscore3, lblscore4, lblscore5;
    static JFrame frame;
    static Container c;
    ScoreController sc;
    int id, s1, s2, s3, s4, s5;
    String st1, st2, st3, st4, st5;

    private void slist() {

    }

    public ScoreList(int id) {
        this.id = id;
        Score scr = new Score();
        scr.setPlayerId(id);
        ScoreController sc = new ScoreController(id);
        List<Integer> sco = null;
        sco = sc.getScore(null);
        int size = sco.size();
        if (size == 5) {
            s1 = sco.get(sco.size() - 5);
            st1 = Integer.toString(s1);
            s2 = sco.get(sco.size() - 4);
            st2 = Integer.toString(s2);
            s3 = sco.get(sco.size() - 3);
            st3 = Integer.toString(s3);
            s4 = sco.get(sco.size() - 2);
            st4 = Integer.toString(s4);
            s5 = sco.get(sco.size() - 1);
            st5 = Integer.toString(s5);
        } else if (size == 4) {
            s2 = sco.get(sco.size() - 4);
            st2 = Integer.toString(s2);
            s3 = sco.get(sco.size() - 3);
            st3 = Integer.toString(s3);
            s4 = sco.get(sco.size() - 2);
            st4 = Integer.toString(s4);
            s5 = sco.get(sco.size() - 1);
            st5 = Integer.toString(s5);
        } else if (size == 3) {
            s3 = sco.get(sco.size() - 3);
            st3 = Integer.toString(s3);
            s4 = sco.get(sco.size() - 2);
            st4 = Integer.toString(s4);
            s5 = sco.get(sco.size() - 1);
            st5 = Integer.toString(s5);
        } else if (size == 2) {
            s4 = sco.get(sco.size() - 2);
            st4 = Integer.toString(s4);
            s5 = sco.get(sco.size() - 1);
            st5 = Integer.toString(s5);
        } else if (size == 1) {
            s5 = sco.get(sco.size() - 1);
            st5 = Integer.toString(s5);
        }
        
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

        lblscore = new JLabel("Score");
        lblscore.setBounds(250, 0, 300, 50);
        lblscore.setForeground(Color.white);
        lblscore.setFont(new Font("", Font.PLAIN, 40));

        lblscore1 = new JLabel("0");
        lblscore1.setText(st1);
        lblscore1.setBounds(300, 70, 130, 40);
        lblscore1.setForeground(Color.white);
        lblscore1.setFont(new Font("", Font.PLAIN, 20));

        lblscore2 = new JLabel("0");
        lblscore2.setText(st2);
        lblscore2.setBounds(300, 120, 130, 40);
        lblscore2.setForeground(Color.white);
        lblscore2.setFont(new Font("", Font.PLAIN, 20));

        lblscore3 = new JLabel("0");
        lblscore3.setText(st3);
        lblscore3.setBounds(300, 170, 130, 50);
        lblscore3.setForeground(Color.white);
        lblscore3.setFont(new Font("", Font.PLAIN, 20));

        lblscore4 = new JLabel("0");
        lblscore4.setText(st4);
        lblscore4.setBounds(300, 220, 130, 50);
        lblscore4.setForeground(Color.white);
        lblscore4.setFont(new Font("", Font.PLAIN, 20));

        lblscore5 = new JLabel("0");
        lblscore5.setText(st5);
        lblscore5.setBounds(300, 270, 130, 50);
        lblscore5.setForeground(Color.white);
        lblscore5.setFont(new Font("", Font.PLAIN, 20));

        back = new JButton("Back");
        back.setBounds(250, 350, 120, 50);
        back.setFont(new Font("", Font.PLAIN, 20));
        back.setBackground(Color.black);
        back.setForeground(Color.white);

        back.addActionListener(this);
        back.setActionCommand("Back");
        c.add(lblscore);
        c.add(lblscore1);
        c.add(lblscore2);
        c.add(lblscore3);
        c.add(lblscore4);
        c.add(lblscore5);
        c.add(back);
        slist();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Back") {
            this.frame.setVisible(false);
            MainGame.frame.setVisible(true);
        }
    }

}
