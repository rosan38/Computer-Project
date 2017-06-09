package edu.softwarica.game.items;

import edu.softwarica.game.game.Game;
import edu.softwarica.game.game.Ids;
import edu.softwarica.game.utils.Assets;
import edu.softwarica.game.walls.Flag;
import edu.softwarica.game.walls.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    public int heading = 0;
    Game game;
    int velY = 0;
    int velX = 0;
    float gravity = 2.0F;
    BufferedImage meRnormal;
    BufferedImage meLnormal;
    BufferedImage[] meNormal = new BufferedImage[4];
    BufferedImage[] inAir = new BufferedImage[4];
    public static boolean canGoDownPipe = false;
    public static boolean canGoUpPipe = false;
    public static boolean jumping = false;
    public static boolean plyr = false;
    public static boolean state = false;
    boolean resultPrinted = false;
    boolean py = false;
    public boolean inPipe = false;
    boolean falling = false;
    private int frame = 0;
    private int frameDelay = 0;
    int totalFrames = 2;
    Flag fl;
    private boolean soundPlayed = false;

    public Player(int x, int y, int width, int height, Game game, Ids id) {
        super(x, y, width, height, id, game);
        this.game = game;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public void render(Graphics g) {
        if (jumping) {
            if (state) {
                if (this.heading == 0) {
                    g.drawImage(this.inAir[0], this.x, this.y, this.width, this.height, null);
                } else {
                    g.drawImage(this.inAir[2], this.x, this.y, this.width, this.height, null);
                }
            } else if (this.heading == 0) {
                g.drawImage(this.inAir[1], this.x, this.y, this.width, this.height, null);
            } else {
                g.drawImage(this.inAir[3], this.x, this.y, this.width, this.height, null);
            }
        } else if (!state) {
            if ((this.velX == 0) && (!jumping)) {
                if (this.heading == 0) {
                    g.drawImage(this.meRnormal, this.x, this.y, this.width, this.height, null);
                } else {
                    g.drawImage(this.meLnormal, this.x, this.y, this.width, this.height, null);
                }
            } else if (this.velX > 0) {
                g.drawImage(this.meNormal[this.frame], this.x, this.y, this.width, this.height, null);
            } else if (this.velX < 0) {
                g.drawImage(this.meNormal[(this.frame + this.totalFrames)], this.x, this.y, this.width, this.height, null);
            }

        }
    }

    public void setVelY(int v) {
        this.velY = v;
    }

    public void setVelX(int v) {
        this.velX = v;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void tick() {
        if ((this.fl != null) && (this.fl.animate) && (this.fl.flagY >= this.fl.y + this.fl.getHeight() * 3)) {
            this.fl.animate = false;
            Tile t = this.fl;
            }
        }
        if ((this.velY != 0) && (!this.inPipe)) {
            jumping = true;
        }
        if ((plyr) && (!this.py)) {
            this.height = 128;
            this.y -= 64;
            this.py = true;
        }
        this.y += this.velY;
        this.x += this.velX;
        if (!this.inPipe) {
            if (this.velY > 15) {
                this.velY = 15;
            }
            this.velY = ((int) (this.velY + this.gravity));
        }
        this.frameDelay += 1;
        if (this.frameDelay >= 7) {
            this.frame += 1;
            if (this.frame >= this.game.playerNormal.length - this.totalFrames) {
                this.frame = 0;
            }
            this.frameDelay = 0;
        }
        for (int i = 0; i < this.game.handler.wall.size(); i++) {
            Tile t = (Tile) this.game.handler.wall.get(i);
            if (!this.inPipe) {
                if ((t.getId() == Ids.flag) && (getBounds().intersects(t.getBounds()))) {
                    this.fl = ((Flag) t);
                    this.fl.animate = true;
                    if (!this.soundPlayed) {
                        new Assets().playSound("/items/flagpole.wav");
                        this.soundPlayed = true;
                    }
                }
                if ((getBoundsTop().intersects(t.getBoundsBottom())) || ((getBoundsTop().intersects(t.getBoundsInterior())) && (this.velY < 0))) {
                    this.y = (t.getY() + t.getHeight());
                    this.falling = true;
                    this.velY = 12;
                } else if ((getBoundsBottom().intersects(t.getBoundsTop())) && (!getBoundsTop().intersects(t.getBoundsInterior())) && (this.velY > 0) && (t.getId() != Ids.pipeDown)) {
                    this.y = (t.getY() - getHeight());
                    this.velY = 0;
                    this.falling = false;
                    jumping = false;
                } else if (((getBoundsRight().intersects(t.getBoundsLeft())) || (getBoundsRight().intersects(t.getBoundsInterior()))) && (!this.game.getKeyManager().left) && (this.velX > 0)) {
                    this.x = (t.getX() - getWidth());
                } else if (((getBoundsLeft().intersects(t.getBoundsRight())) || (getBoundsLeft().intersects(t.getBoundsInterior()))) && (!this.game.getKeyManager().right) && (this.velX < 0)) {
                    this.x = (t.getX() + t.getWidth());
                }
            }
        }
        for (int k = 0; k < this.game.handler.creature.size(); k++) {
            Creature c = (Creature) this.game.handler.creature.get(k);
            if (c.getId() == Ids.enemy) {
                if (getBoundsBottom().intersects(c.getBoundsTop())) {
                    this.game.handler.die(c);
                    this.velY = -30;
                    new Assets().playSound("/items/stomp.wav");
                    this.game.score += 100;
                } else if ((getBoundsLeft().intersects(c.getBoundsRight())) || (getBoundsRight().intersects(c.getBoundsLeft()))) {
                    if ((getHeight() == 128) && (state)) {
                        state = false;
                        this.velY = -30;
                        new Assets().playSound("/items/pipe_travel_power_down.wav");
                    } else if ((getHeight() == 128) && (!state)) {
                        setHeight(64);
                        plyr = false;
                        this.velY = -30;
                        new Assets().playSound("/items/pipe_travel_power_down.wav");
                    } else {
                        this.game.frame.setVisible(false);
                        if ((!this.resultPrinted) && (this.d == null)) {
                            this.d = new ScoreDisplay(1, this.game.g, this.game);
                            this.resultPrinted = true;
                            this.d = null;
                            this.game.stop();
                            this.game.handler.die(this);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < this.game.handler.pipesUp; i++) {
            if ((this.y <= this.game.handler.piU[i].getY() - getHeight()) && (this.inPipe)) {
                this.inPipe = false;
            }
            if ((this.velY > 0) && (getBoundsBottom().intersects(this.game.handler.piD[i].getBoundsTop()))) {
                this.inPipe = false;
            }
            if ((this.x > this.game.handler.piU[i].getX()) && (this.x + this.width < this.game.handler.piU[i].getX() + this.game.handler.piU[i].getWidth()) && (this.y < this.game.handler.piU[i].getY())) {
                canGoDownPipe = true;
            } else {
                canGoDownPipe = false;
            }
            if ((this.x > this.game.handler.piD[i].getX()) && (this.x + this.width < this.game.handler.piD[i].getX() + this.game.handler.piD[i].getWidth()) && (this.y >= this.game.handler.piD[i].getY() + this.game.handler.piD[i].getHeight())) {
                canGoUpPipe = true;
            } else {
                canGoUpPipe = false;
            }
            if ((this.velY < 0) && (getBoundsTop().intersects(this.game.handler.piD[i].getBounds())) && (canGoUpPipe)) {
                this.velY = -10;
                this.inPipe = true;
                jumping = false;
                this.falling = false;
                canGoDownPipe = false;
                canGoUpPipe = false;
            }
            if ((this.velY < 0) && (getBoundsBottom().intersects(this.game.handler.piU[i].getBoundsTop())) && (!jumping)) {
                this.inPipe = false;
                canGoDownPipe = false;
                canGoUpPipe = false;
                this.velY = 0;
            }
        }
    }

    public void init() {
        this.meRnormal = Game.playerRightNormal.getBufferedImage();
        this.meLnormal = Game.playerLeftNormal.getBufferedImage();
        for (int i = 0; i < this.game.playerNormal.length; i++) {
            this.meNormal[i] = this.game.playerNormal[i].getBufferedImage();
        }
        for (int i = 0; i < this.game.inAirPlayer.length; i++) {
            this.inAir[i] = this.game.inAirPlayer[i].getBufferedImage();
        }
    }

    public int getVelY() {
        return this.velY;
    }

    public void setY(int i) {
        this.y = i;
    }

    public int getWidth() {
        return this.width;
    }
}
