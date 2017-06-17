package edu.softwarica.game.walls;

import edu.softwarica.game.game.Game;
import edu.softwarica.game.game.Ids;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Flag extends Tile {

    public int level;
    public int flagX, flagY;
    public int wallY;
    BufferedImage[] me = new BufferedImage[3];
    public boolean animate = false;

    public Flag(int x, int y, int width, int height, Ids id, Game game, int level) {
        super(x, y, width, height, id, game, level);
        this.flagX = x;
        this.flagY = y;
        this.wallY = y;
    }

    public void render(Graphics g) {
        g.drawImage(this.me[1], this.x, this.wallY + this.height, this.width, this.height, null);
        g.drawImage(this.me[2], this.x, this.wallY + this.height * 2, this.width, this.height, null);
        g.drawImage(this.me[2], this.x, this.wallY + this.height * 3, this.width, this.height, null);
        g.drawImage(this.me[2], this.x, this.wallY + this.height * 4, this.width, this.height, null);
        g.drawImage(this.me[0], this.flagX, this.flagY, this.width, this.height, null);
        if (this.animate) {
            animate();
        }
    }

    public void animate() {
        this.flagX += 2;
        this.flagY += 2;
        this.wallY += 4;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height * 5);
    }

    public void init() {
        for (int i = 0; i < 3; i++) {
            this.me[i] = this.game.flag[i].getBufferedImage();
        }
    }
}
