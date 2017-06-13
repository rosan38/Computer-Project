package edu.softwarica.game.items;

import edu.softwarica.game.game.Game;
import edu.softwarica.game.game.Ids;
import edu.softwarica.game.walls.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Creature {

    int velX = 0;
    int velY = 0;
    int gravity = 2;
    Random r = new Random();
    int dir = this.r.nextInt(2);
    int heading;
    BufferedImage meR;
    BufferedImage meL;

    public Enemy(int x, int y, int width, int height, Game game, Ids id) {
        super(x, y, width, height, id, game);
        switch (this.dir) {
            case 0:
                this.velX = -4;
                break;
            case 1:
                this.velX = 4;
        }
    }

    public void render(Graphics g) {
        if ((this.x > this.game.handler.getPlayer().getX() + this.game.handler.getPlayer().getWidth() + 1240) || (this.y > this.game.handler.getPlayer().getY() + this.game.handler.getPlayer().getHeight() + 580)) {
            return;
        }
        if (this.heading == 0) {
            g.drawImage(this.meR, this.x, this.y, this.width, this.height, null);
        } else {
            g.drawImage(this.meL, this.x, this.y, this.width, this.height, null);
        }
    }

    public void tick() {
        if (this.velX > 0) {
            this.heading = 0;
        } else if (this.velX < 0) {
            this.heading = 1;
        }
        this.x += this.velX;
        this.y += this.velY;
        this.velY += this.gravity;
        for (int i = 0; i < this.game.handler.wall.size(); i++) {
            Tile t = (Tile) this.game.handler.wall.get(i);
            if (getBoundsBottom().intersects(t.getBoundsTop())) {
                this.velY = 0;
            }
            if (getBoundsRight().intersects(t.getBoundsLeft())) {
                this.velX = -4;
            }
            if (getBoundsLeft().intersects(t.getBoundsRight())) {
                this.velX = 4;
            }
        }
    }

    public void init() {
        this.meR = Game.enemyRight.getBufferedImage();
        this.meL = Game.enemyLeft.getBufferedImage();
    }
}
