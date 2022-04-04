package cn.sichu.fxgame.sprite;

import javafx.scene.image.Image;

/**
 * 
 * @author sichu
 * @date 2022/04/04
 */
public class Obstacle extends Sprite {

    public Obstacle(double x, double y) {
        super(new Image("images/cheers.gif"), x, y, 56, 56);
    }

}
