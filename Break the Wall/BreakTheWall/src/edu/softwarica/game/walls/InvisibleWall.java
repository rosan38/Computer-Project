package edu.softwarica.game.walls;

import edu.softwarica.game.game.Game;
import edu.softwarica.game.game.Ids;
import java.awt.Graphics;

public class InvisibleWall extends Tile {

    public InvisibleWall(int x, int y, int width, int height, Ids id, Game game, int level) {
        super(x, y, width, height, id, game, level);
    }

    public void render(Graphics g) {
    }

    public void init() {
    }
}
