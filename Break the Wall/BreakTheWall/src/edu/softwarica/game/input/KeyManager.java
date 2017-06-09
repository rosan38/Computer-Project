package edu.softwarica.game.input;

import edu.softwarica.game.items.Player;
import edu.softwarica.game.game.Game;
import edu.softwarica.game.utils.Assets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    boolean[] keys;
    Game game;
    public boolean left;
    public boolean right;

    public KeyManager(Game game) {
        this.keys = new boolean[65];
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        this.keys[e.getKeyCode()] = true;
        if (!this.game.getPlayer().inPipe) {
            if ((e.getKeyCode() == 38) && (!Player.jumping)) {
                this.game.getPlayer().setVelY(-30);
                this.game.getPlayer();
                if (Player.plyr) {
                    new Assets().playSound("/items/jump_super.wav");
                } else {
                    this.game.getPlayer();
                    if (!Player.plyr) {
                        new Assets().playSound("/items/jump_small.wav");
                    }
                }
                Player.jumping = true;
            }
            if (e.getKeyCode() == 37) {
                this.game.getPlayer().setVelX(-6);
                this.game.getPlayer().heading = 1;
            }
            if (e.getKeyCode() == 39) {
                this.game.getPlayer().setVelX(6);
                this.game.getPlayer().heading = 0;
            }
            if ((e.getKeyCode() == 40) && (Player.canGoDownPipe)) {
                this.game.getPlayer().inPipe = true;

                Player.canGoDownPipe = false;
                this.game.handler.getPlayer().setVelY(10);
                new Assets().playSound("/items/pipe_travel_power_down.wav");
            }
            if ((e.getKeyCode() == 38) && (Player.canGoUpPipe)) {
                this.game.getPlayer().inPipe = true;
                Player.canGoUpPipe = false;
                new Assets().playSound("/items/pipe_travel_power_down.wav");
            }
        }
    }

    public void tick() {
        if (this.game.getPlayer().inPipe) {
            this.game.getPlayer().setVelX(0);
        }
        this.right = this.keys[39];
        this.left = this.keys[37];
    }

    public void keyReleased(KeyEvent e) {
        this.keys[e.getKeyCode()] = false;
        if ((e.getKeyCode() == 37) || (e.getKeyCode() == 39)) {
            this.game.getPlayer().setVelX(0);
        }
    }

    public void keyTyped(KeyEvent e) {
    }
}
