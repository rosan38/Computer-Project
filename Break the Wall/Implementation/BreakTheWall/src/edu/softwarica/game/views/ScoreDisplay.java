package edu.softwarica.game.views;

import edu.softwarica.game.controller.ScoreController;
import edu.softwarica.game.game.Game;
import edu.softwarica.game.model.Score;
import edu.softwarica.game.utils.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ScoreDisplay extends JFrame implements MouseListener {

    boolean resultDisplayed = false;
    Font font;
    Game game;
    int win, sc1;

    public ScoreDisplay(int win, Graphics g, Game game, int scor) {
        this.win = win;
        this.sc1 = scor;
        setSize(720, 360);
        setVisible(true);
        setTitle("Break The Wall");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setResizable(false);
        this.font = new Font("Serif", 0, 50);
        setIconImage(new ImageIcon(getClass().getResource("/items/icon.png")).getImage());
        this.game = game;
        inscore();
        addMouseListener(this);

    }

    public void paint(Graphics g) {
        if (this.resultDisplayed) {
            return;
        }
        switch (this.win) {

            case 0:
                g.setColor(Color.black);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.white);
                g.setFont(new Font("Serif", 0, 50));
                g.drawString("You win", 220, 100);
                g.drawString("Your score :" + sc1, 200, 200);
                setLocationRelativeTo(null);
                Assets a = new Assets();
                a.playSound("/items/stage_complete.wav");
                this.resultDisplayed = true;
                break;
            case 1:
                g.setColor(Color.black);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setFont(new Font("Serif", 0, 50));
                g.setColor(Color.white);
                g.drawString("You lose", 240, 100);
                g.drawString("Your score :" + sc1, 200, 200);
                setLocationRelativeTo(null);
                Assets a2 = new Assets();
                a2.playSound("/items/game_over.wav");
                this.resultDisplayed = true;
                break;
            case 2:
                g.clearRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.black);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setFont(new Font("Serif", 0, 50));
                g.setColor(Color.white);
                g.drawString("You ran out of time!", 180, 100);
                g.drawString("Your score :" + sc1, 200, 200);
                setLocationRelativeTo(null);
                Assets a3 = new Assets();
                a3.playSound("/items/game_over.wav");
                this.resultDisplayed = true;
        }
    }

    public void inscore() {
        ScoreController sc = new ScoreController(0);
        ScoreController sc1 = new ScoreController(this.game.id);
        List<Integer> sco = null;
        sco = sc1.getScore(null);
        int size = sco.size();
        if (size == 0) {
            Score scr = new Score();
            scr.setScore(this.game.score);
            scr.setPlayerId(this.game.id);
            sc.addScore(scr);
        } else {
            int a = sco.get(sco.size() - 1);
            Score scr = new Score();
            if (a < this.game.score) {
                scr.setScore(this.game.score);
                scr.setPlayerId(this.game.id);
                sc.addScore(scr);
            } else {
                JOptionPane.showMessageDialog(this, "Your Score is low then previous");
            }
        }

    }

    public void mouseClicked(MouseEvent e) {
        this.setVisible(false);
        MainGame.frame.setVisible(true);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
