package edu.softwarica.game.game;

import edu.softwarica.game.controller.ScoreController;
import edu.softwarica.game.graphics.Sprite;
import edu.softwarica.game.graphics.SpriteSheet;
import edu.softwarica.game.input.KeyManager;
import edu.softwarica.game.items.Player;
import edu.softwarica.game.views.ScoreDisplay;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game implements Runnable {

    Thread thread = new Thread(this);

    public JFrame frame;
    int level = 1;
    public BufferedImage level1;
    public BufferedImage level2;
    public BufferedImage level3;
    public BufferedImage level4;
    public BufferedImage level5;
    public BufferedImage background;
    public Handler handler = new Handler(this);
    public static Sprite coin;
    public static Sprite block;
    public static Sprite pipeDown;
    public static Sprite pipeUp;
    public static Sprite brick;
    public static Sprite playerRightNormal;
    public static Sprite playerLeftNormal;
    public static Sprite grass;
    public static Sprite mud;
    public static Sprite enemyRight;
    public static Sprite enemyLeft;
    public static Sprite finalwall;
    static SpriteSheet sheet;
    int pipes = 0;
    Canvas canvas;
    KeyManager km = new KeyManager(this);
    public Graphics g;
    public Sprite[] flag = new Sprite[3];
    public Sprite[] playerNormal = new Sprite[4];
    public Sprite[] inAirPlayer = new Sprite[4];
    BufferStrategy bs;
    volatile boolean running = false;
    public int seconds = 0;
    public int completionTime = 100;
    public int score = 0;
    public int id = 0;
    public boolean translate = false;
    boolean resultDisplayed = false;

    public void switchLevel(int number) {
        this.handler.clearWorld();
        this.handler.pipesUp = 0;
        this.handler.pipesDown = 0;
        switch (number) {
            case 1:
                this.handler.createLevel(this.level1);
                break;
            case 2:
                this.handler.createLevel(this.level2);
                this.seconds = 0;
                break;
            case 3:
                this.handler.createLevel(this.level3);
                this.seconds = 0;
                break;
            case 4:
                this.handler.createLevel(this.level4);
                this.seconds = 0;
                break;
            case 5:
                this.handler.createLevel(this.level5);
                this.seconds = 0;
        }
    }

    public void init(int level) {
        sheet = new SpriteSheet("/items/spritesheet.png");
        grass = new Sprite(sheet, 3, 0);
        mud = new Sprite(sheet, 2, 0);
        playerRightNormal = new Sprite(sheet, 0, 0);
        playerLeftNormal = new Sprite(sheet, 1, 0);
        enemyRight = new Sprite(sheet, 0, 1);
        enemyLeft = new Sprite(sheet, 1, 1);
        finalwall = new Sprite(sheet, 4, 0);
        brick = new Sprite(sheet, 2, 1);
        pipeUp = new Sprite(sheet, 3, 1);
        pipeDown = new Sprite(sheet, 4, 1);
        block = new Sprite(sheet, 5, 1);
        coin = new Sprite(sheet, 5, 2);
        try {
            this.level1 = ImageIO.read(getClass().getResource("/items/level1.png"));
            this.level2 = ImageIO.read(getClass().getResource("/items/level2.png"));
            this.level3 = ImageIO.read(getClass().getResource("/items/level3.png"));
            this.level4 = ImageIO.read(getClass().getResource("/items/level4.png"));
            this.level5 = ImageIO.read(getClass().getResource("/items/level5.png"));
            this.background = ImageIO.read(getClass().getResource("/items/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        for (int i = 0; i < this.flag.length; i++) {
            this.flag[i] = new Sprite(sheet, i, 2);
        }
        for (int i = 0; i < this.playerNormal.length; i++) {
            this.playerNormal[i] = new Sprite(sheet, i, 3);
        }
        for (int i = 0; i < this.inAirPlayer.length; i++) {
            if (i < 2) {
                this.inAirPlayer[i] = new Sprite(sheet, 3, i + 1);
            } else {
                this.inAirPlayer[i] = new Sprite(sheet, 4, i - 1);
            }
        }
        switch (level) {
            case 1:
                switchLevel(1);
                break;
            case 2:
                switchLevel(2);
                break;
            case 3:
                switchLevel(3);
                break;
            case 4:
                switchLevel(4);
                break;
            case 5:
                switchLevel(5);
        }
        this.handler.initCreature();
        this.handler.initTile();
    }

    public KeyManager getKeyManager() {
        return this.km;
    }

    public Game(int width, int height, int id) {
        this.id = id;
        this.frame = new JFrame("Break The Wall");
        this.frame.setSize(width, height);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(3);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setIconImage(new ImageIcon(getClass().getResource("/items/icon.png")).getImage());
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(1300, 600));
        this.canvas.setFocusable(false);
        this.frame.addKeyListener(this.km);
        this.frame.add(this.canvas);
        this.frame.pack();
    }

    public synchronized void start() {
        if (this.running) {
            return;
        }
        this.running = true;
        this.thread.start();
    }

    public void run() {
        init(1);
        long lastTime = System.nanoTime();
        double ns = 1.6666666666666666E7D;
        double delta = 0.0D;
        long timer = System.currentTimeMillis();

        while (this.running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0D) {
                tick();
                delta -= 1.0D;
            }
            render();
            if (System.currentTimeMillis() - timer > 1000L) {
                timer += 1000L;
                if (!this.handler.f.animate) {
                    this.seconds += 1;
                }
            }
        }
    }

    public synchronized void stop() {
        if (!this.running) {
            return;
        }
        this.running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        this.handler.tickCreature();
        this.km.tick();
        if ((this.completionTime - this.seconds <= 0) && (!this.resultDisplayed)) {
            ScoreDisplay d = new ScoreDisplay(0, this.g, this, this.score);
            this.resultDisplayed = true;
            this.frame.setVisible(false);
            d = null;
            stop();
        }
    }

    public Player getPlayer() {
        return this.handler.getPlayer();
    }

    public void render() {
        if (this.bs == null) {
            this.canvas.createBufferStrategy(3);
        }
        this.bs = this.canvas.getBufferStrategy();
        this.g = this.bs.getDrawGraphics();
        this.g.clearRect(0, 0, 1300, 600);
        this.g.drawImage(background, 0, 0, null);
        if ((!this.handler.getPlayer().inPipe) && (this.handler.getPlayer().getY() <= 140)) {
            this.g.translate(0, -this.handler.getPlayer().getY() + 140);
            this.translate = true;
        } else if ((!this.handler.getPlayer().inPipe) && (this.handler.getPlayer().getY() > 704)) {
            this.g.translate(0, -this.handler.getPlayer().getY() + 140);
            this.translate = true;
        } else {
            this.translate = false;
        }
        this.g.translate(-this.handler.getPlayer().getX() + 200, 0);
        this.handler.renderCreature(this.g);
        this.handler.renderTile(this.g);
        this.g.setColor(Color.WHITE);
        this.g.setFont(new Font("Serif", 1, 12));
        if (!this.translate) {
            this.g.drawString("TIME : " + (this.completionTime - this.seconds), this.handler.getPlayer().getX() + 100, 50);
            this.g.drawString("SCORE : " + this.score, getPlayer().getX() + 200, 50);
        } else {
            this.g.drawString("TIME : " + (this.completionTime - this.seconds), this.handler.getPlayer().getX() + 100, this.handler.getPlayer().getY() - 90);
            this.g.drawString("SCORE : " + this.score, getPlayer().getX() + 200, getPlayer().getY() - 90);
        }
        ScoreController sc1 = new ScoreController(id);
        List<Integer> sco = null;
        sco = sc1.getScore(null);
        int size = sco.size();
        if (size == 0) {
            this.g.setFont(new Font("Serif", 1, 20));
            this.g.setColor(Color.red);
            this.g.drawString("HIGHEST SCORE: " + this.score, getPlayer().getX() + 300, 50);
        } else if (size == 1) {
            int a = sco.get(sco.size() - 1);
            if (a < this.score) {
                this.g.setFont(new Font("Serif", 1, 20));
                this.g.setColor(Color.red);
                this.g.drawString("HIGHEST SCORE: " + this.score, getPlayer().getX() + 300, 50);
            }
        } else if (size == 2) {
            int a = sco.get(sco.size() - 2);
            if (a < this.score) {
                this.g.setFont(new Font("Serif", 1, 20));
                this.g.setColor(Color.red);
                this.g.drawString("HIGHEST SCORE: " + this.score, getPlayer().getX() + 300, 50);
            }
        } else if (size == 3) {
            int a = sco.get(sco.size() - 3);
            if (a < this.score) {
                this.g.setFont(new Font("Serif", 1, 20));
                this.g.setColor(Color.red);
                this.g.drawString("HIGHEST SCORE: " + this.score, getPlayer().getX() + 300, 50);
            }
        } else if (size == 4) {
            int a = sco.get(sco.size() - 4);
            if (a < this.score) {
                this.g.setFont(new Font("Serif", 1, 20));
                this.g.setColor(Color.red);
                this.g.drawString("HIGHEST SCORE: " + this.score, getPlayer().getX() + 300, 50);
            }
        } else if (size == 5) {
            int a = sco.get(sco.size() - 5);
            if (a < this.score) {
                this.g.setFont(new Font("Serif", 1, 20));
                this.g.setColor(Color.red);
                this.g.drawString("HIGHEST SCORE: " + this.score, getPlayer().getX() + 300, 50);
            }
        }
        this.bs.show();
        this.g.dispose();
    }
}
