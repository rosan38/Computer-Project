package edu.softwarica.game.graphics;

import java.awt.image.BufferedImage;

public class Sprite {

    private SpriteSheet sheet;
    int x;
    int y;

    public Sprite(SpriteSheet sheet, int x, int y) {
        this.sheet = sheet;
        this.x = x;
        this.y = y;
    }

    public BufferedImage getBufferedImage() {
        return this.sheet.getSprite(this.x, this.y);
    }
}
