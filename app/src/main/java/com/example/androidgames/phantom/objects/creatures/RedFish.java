package com.example.androidgames.phantom.objects.creatures;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.example.androidgames.phantom.core.MarioResourceManager;
import com.example.androidgames.phantom.core.MarioSoundManager;
import com.example.androidgames.phantom.core.animation.Animation;
import com.example.androidgames.phantom.core.tile.TileMap;
import com.example.androidgames.phantom.objects.base.Creature;
import com.example.androidgames.phantom.particles.BubbleParticle;


public class RedFish extends Creature {

    private final Animation leftSwim;
    private final Animation rightSwim;
    private final Animation dead;
    private final Animation flip;
    private static Bitmap[] v;
    private static boolean initialized = false;
    private final int initY;

    public RedFish(int x, int y, MarioSoundManager soundManager) {
        super(x, y, soundManager);
        inWater = true;
        initY = y;
        if (!initialized) {
            v = MarioResourceManager.RedFish;
        }
        leftSwim = new Animation(200).addFrame(v[0]).addFrame(v[1]);
        rightSwim = new Animation(200).addFrame(v[2]).addFrame(v[3]);
        flip = new Animation().addFrame(v[4]).addFrame(v[4]);
        final class DeadAfterAnimation extends Animation {
            public void endOfAnimationAction() {
                kill();
            }
        }
        dead = new DeadAfterAnimation().setDAL(100).addFrame(v[4]).setDAL(20).addFrame(v[4]);
        setAnimation(leftSwim);
        dx = (float) (-0.4f - Math.random() * 0.3f);
    }

    @Override
    public void xCollide(Point p) {

    }

    @Override
    public void creatureXCollide() {
        if (dx > 0) {
            x = x - 2;
            setAnimation(leftSwim);
        } else {
            setAnimation(rightSwim);
            x = x + 2;
        }
        dx = -dx;
    }

    @Override
    public void wakeUp(boolean isLeft) {
        super.wakeUp();
        if (isLeft) {
            dx = -0.03f;
            setAnimation(leftSwim);
        } else {
            dx = 0.03f;
            setAnimation(rightSwim);
        }
    }

    @Override
    public void updateCreature(TileMap map, int time) {
        if (isFlipped() || !inWater) {
            super.updateCreature(map, time);
        } else {
            x = x + dx * time;
            if (x < -16 || x > map.getWidth() * 16) kill();
            super.update(time);
            if (inWater) {
                if (Math.random() > 0.97) {
                    map.creaturesToAdd().add(new BubbleParticle((int) getX(), (int) getY()));
                }
                y = (float) (initY + 6 * Math.sin(x / 7));
            }
        }
    }

    @Override
    public void jumpedOn() {
        setAnimation(dead);
        setIsCollidable(false);
        dx = 0;
        dy = 0;
    }

    @Override
    public void flip() {
        setAnimation(flip);
        setIsFlipped(true);
        setIsCollidable(false);
        dy = -.1f;
        dx = 0;
    }

}
