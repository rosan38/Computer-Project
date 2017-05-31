package edu.softwarica.game.walls;

import edu.softwarica.game.game.Game;
import edu.softwarica.game.game.Ids;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Flag extends Tile {

    public int level;
    public int flagY;
    BufferedImage[] me = new BufferedImage[3];
    public boolean animate = false;

    public Flag(int x, int y, int width, int height, Ids id, Game game, int level) {
        super(x, y, width, height, id, game, level);
        this.flagY = y;
    }

    public void render(Graphics g) {
        if ((this.x > this.game.handler.getPlayer().getX() + this.game.handler.getPlayer().getWidth() + 1240) || (this.y > this.game.handler.getPlayer().getY() + this.game.handler.getPlayer().getHeight() + 580)) {
            return;
        }
        g.drawImage(this.me[1], this.x, this.y + this.height, this.width, this.height, null);
        g.drawImage(this.me[1], this.x, this.y + this.height * 2, this.width, this.height, null);
        g.drawImage(this.me[1], this.x, this.y + this.height * 3, this.width, this.height, null);
        g.drawImage(this.me[2], this.x, this.y + this.height * 4, this.width, this.height, null);
        g.drawImage(this.me[0], this.x, this.flagY, this.width, this.height, null);
        if (this.animate) {
            animate();
        }
    }

    public void animate() {
        this.flagY += 3;
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
