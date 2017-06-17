package edu.softwarica.game.game;

import edu.softwarica.game.items.Coin;
import edu.softwarica.game.items.Creature;
import edu.softwarica.game.items.Enemy;
import edu.softwarica.game.items.Player;
import edu.softwarica.game.walls.Block;
import edu.softwarica.game.walls.Brick;
import edu.softwarica.game.walls.FinalWall;
import edu.softwarica.game.walls.Flag;
import edu.softwarica.game.walls.Grass;
import edu.softwarica.game.walls.InvisibleWall;
import edu.softwarica.game.walls.Mud;
import edu.softwarica.game.walls.PipeDown;
import edu.softwarica.game.walls.PipeUp;
import edu.softwarica.game.walls.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler {

    Game game;
    int level = 1;
    public Flag f;
    public int pipesUp = 0;
    public int pipesDown = 0;
    public PipeUp[] piU;
    public PipeDown[] piD;

    public Player getPlayer() {
        return this.p;
    }

    public Handler(Game game) {
        this.game = game;
    }
    public LinkedList<Creature> creature = new LinkedList();
    public LinkedList<Tile> wall = new LinkedList();
    public Player p;

    public void addCreature(Creature c) {
        this.creature.add(c);
    }

    public void clearWorld() {
        this.creature.clear();
        this.wall.clear();
    }

    public void createLevel(BufferedImage level) {
        int width = level.getWidth();
        int height = level.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color c = new Color(level.getRGB(x, y));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                if ((red == 76) && (green == 255) && (blue == 0)) {
                    Grass g = new Grass(x * 64, y * 64, 64, 64, Ids.grass, this.game, this.level);
                    addTile(g);
                }
                if ((red == 127) && (green == 0) && (blue == 0)) {
                    addTile(new Mud(x * 64, y * 64, 64, 64, Ids.mud, this.game, this.level));
                }
                if ((red == 0) && (green == 148) && (blue == 255)) {
                    this.p = new Player(x * 64, y * 64, 64, 64, this.game, Ids.player);
                    addCreature(this.p);
                }
                if ((red == 128) && (green == 128) && (blue == 128)) {
                    addCreature(new Enemy(x * 64, y * 64, 64, 64, this.game, Ids.enemy));
                }
                if ((red == 255) && (green == 0) && (blue == 110)) {
                    addTile(new InvisibleWall(x * 64, y * 64, 64, 64, Ids.invisibleWall, this.game, this.level));
                }
                if ((red == 234) && (green == 21) && (blue == 123)) {
                    this.f = new Flag(x * 64, y * 64, 90, 90, Ids.flag, this.game, this.level);
                    addTile(this.f);
                    this.level += 1;
                }
                if ((red == 0) && (green == 255) && (blue == 255)) {
                    addTile(new PipeUp(x * 64, y * 64, 128, 128, Ids.pipeUp, this.game, this.level));
                    this.pipesUp += 1;
                }
                if ((red == 42) && (green == 213) && (blue == 42)) {
                    addTile(new PipeDown(x * 64, y * 64, 128, 128, Ids.pipeDown, this.game, this.level));
                    this.pipesDown += 1;
                }
                if ((red == 50) && (green == 250) && (blue == 150)) {
                    addTile(new FinalWall(x * 64, y * 64, 256, 256, Ids.castle, this.game, this.level));
                }
                if ((red == 31) && (green == 34) && (blue == 42)) {
                    addTile(new Brick(x * 64, y * 64, 64, 64, Ids.brick, this.game, this.level));
                }
                if ((red == 32) && (green == 134) && (blue == 95)) {
                    addTile(new Block(x * 64, y * 64, 128, 128, Ids.block, this.game, this.level));
                }
                if ((red == 21) && (green == 10) && (blue == 53)) {
                    addCreature(new Coin(x * 64, y * 64, 64, 64, Ids.coin, this.game));
                }
            }
        }
        this.piU = new PipeUp[this.pipesUp];
        this.piD = new PipeDown[this.pipesDown];
        int i = 0;
        int m = 0;
        for (int a = 0; a < this.wall.size(); a++) {
            Tile t = (Tile) this.wall.get(a);
            if (t.getId() == Ids.pipeUp) {
                this.piU[m] = ((PipeUp) t);
                m++;
            } else if (t.getId() == Ids.pipeDown) {
                this.piD[i] = ((PipeDown) t);
                i++;
            }
        }
    }

    public void die(Creature c) {
        this.creature.remove(c);
    }

    public void initCreature() {
        for (int i = 0; i < this.creature.size(); i++) {
            Creature c = (Creature) this.creature.get(i);
            c.init();
        }
    }

    public void initTile() {
        for (int i = 0; i < this.wall.size(); i++) {
            Tile t = (Tile) this.wall.get(i);
            t.init();
        }
    }

    public void tickCreature() {
        for (int i = 0; i < this.creature.size(); i++) {
            Creature c = (Creature) this.creature.get(i);
            c.tick();
        }
    }

    public void renderCreature(Graphics g) {
        for (int i = 0; i < this.creature.size(); i++) {
            Creature c = (Creature) this.creature.get(i);
            c.render(g);
        }
    }

    public void addTile(Tile t) {
        this.wall.add(t);
    }

    public void removeTile(Tile t) {
        this.wall.remove(t);
    }

    public void renderTile(Graphics g) {
        for (int i = 0; i < this.wall.size(); i++) {
            Tile t = (Tile) this.wall.get(i);
            t.render(g);
        }
    }
}
