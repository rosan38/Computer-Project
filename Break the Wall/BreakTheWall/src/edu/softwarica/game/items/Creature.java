package edu.softwarica.game.items;

import edu.softwarica.game.game.Game;
import edu.softwarica.game.game.Ids;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Creature {

    int x;
    public int y;
    int width;
    Ids id;
    int height;
    Game game;

    public abstract void render(Graphics paramGraphics);

    public abstract void tick();

    public abstract void init();

    public Ids getId() {
        return this.id;
    }

    public Creature(int x, int y, int width, int height, Ids id, Game game) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle(this.x + 7, this.y - 3, this.width - 14, 10);
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle(this.x + 3, this.y + this.height - 3, this.width - 6, 10);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle(this.x + this.width - 5, this.y + 5, 5, this.height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle(this.x, this.y + 3, 5, this.height - 10);
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getHeight() {
        return this.height;
    }
}
