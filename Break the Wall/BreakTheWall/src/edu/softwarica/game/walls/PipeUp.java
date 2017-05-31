package edu.softwarica.game.walls;

import edu.softwarica.game.game.Game;
import edu.softwarica.game.game.Ids;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PipeUp extends Tile {

    BufferedImage me;

    public PipeUp(int x, int y, int width, int height, Ids id, Game game, int level) {
        super(x, y, width, height, id, game, level);
    }

    public void render(Graphics g) {
        if ((this.x > this.game.handler.getPlayer().getX() + this.game.handler.getPlayer().getWidth() + 1240) || (this.y > this.game.handler.getPlayer().getY() + this.game.handler.getPlayer().getHeight() + 580)) {
            return;
        }
        g.drawImage(this.me, this.x, this.y, this.width, this.height, null);
    }

    public void init() {
        this.me = Game.pipeUp.getBufferedImage();
    }
}
