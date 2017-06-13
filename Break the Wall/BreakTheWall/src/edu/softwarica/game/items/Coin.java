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

    public void tick() {
        for (int i = 0; i < this.game.handler.creature.size(); i++) {
            if (((Creature) this.game.handler.creature.get(i)).getId() == Ids.player) {
                Player p = (Player) this.game.handler.creature.get(i);
                if (p.getBounds().intersects(getBounds())) {
                    this.game.score += 100;
                    new Assets().playSound("/items/coin.wav");
                    this.game.handler.die(this);
                }
            }
        }
    }

    public void init() {
        this.me = Game.coin.getBufferedImage();
    }
}
