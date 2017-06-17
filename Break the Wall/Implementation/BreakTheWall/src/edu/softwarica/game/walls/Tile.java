package edu.softwarica.game.walls;

import edu.softwarica.game.game.Game;
import edu.softwarica.game.game.Ids;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Tile {

    int x;
    public int y;
    int width;
    public int height;
    public int level;
    Ids id;
    Game game;

    public abstract void render(Graphics paramGraphics);

    public abstract void init();

    public int getWidth() {
        return this.width;
    }

    public Tile(int x, int y, int width, int height, Ids id, Game game, int level) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level = level;
        this.game = game;
        this.id = id;
    }

    public Ids getId() {
        return this.id;
    }

    public int getX() {
        return this.x;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle(this.x + 3, this.y - 3, this.width - 6, 10);
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle(this.x + 3, this.y + this.height - 3, this.width - 6, 5);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle(this.x + this.width - 5, this.y + 3, 5, this.height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle(this.x, this.y + 3, 5, this.height - 10);
    }

    public Rectangle getBoundsInterior() {
        return new Rectangle(this.x + 10, this.y + 10, this.width - 20, this.height - 20);
    }

    public int getY() {
        return this.y;
    }

    public int getHeight() {
        return this.height;
    }
}
