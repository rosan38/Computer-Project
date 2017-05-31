package edu.softwarica.game.game;

import edu.softwarica.game.graphics.Sprite;
import edu.softwarica.game.graphics.SpriteSheet;
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
    public Graphics g;
    public Sprite[] flag = new Sprite[3];
    public Sprite[] playerNormal = new Sprite[4];
    public Sprite[] inAirPlayer = new Sprite[4];
    BufferStrategy bs;
    volatile boolean running = false;
    public int seconds = 0;
    public int completionTime = 100;
    public int id = 0;
    public boolean translate = false;
    boolean resultDisplayed = false;

    public void switchLevel(int number) {
        this.handler.clearWorld();
        this.handler.pipesUp = 0;
        this.handler.pipesDown = 0;
                this.handler.createLevel(this.level1);
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
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        for (int i = 0; i < this.flag.length; i++) {
            this.flag[i] = new Sprite(sheet, i, 2);
        }
        }
        switch (level) {
            case 1:
                switchLevel(1);
                break;
        }
        this.handler.initTile();
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
        this.canvas.setMaximumSize(new Dimension(width, height));
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

    public void render() {
        if (this.bs == null) {
            this.canvas.createBufferStrategy(3);
        }
        this.bs = this.canvas.getBufferStrategy();
        this.g = this.bs.getDrawGraphics();
        this.g.clearRect(0, 0, 3000, 3000);
        this.g.drawImage(background, 0, 0, null);
       this.bs.show();
        this.g.dispose();
    }
}
