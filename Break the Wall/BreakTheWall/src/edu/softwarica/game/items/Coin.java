package edu.softwarica.game.items;

import edu.softwarica.game.game.Game;
import edu.softwarica.game.game.Ids;
import edu.softwarica.game.utils.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Coin extends Creature {

    BufferedImage me;

    public Coin(int x, int y, int width, int height, Ids id, Game game) {
        super(x, y, width, height, id, game);
    }

    public void render(Graphics g) {
        g.drawImage(this.me, this.x, this.y, this.width, this.height, null);
    }

    public void init() {
        this.me = Game.coin.getBufferedImage();
    }
}
