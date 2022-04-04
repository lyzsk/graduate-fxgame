package cn.sichu.fxgame.sprite;

import javafx.scene.image.Image;

/**
 * 
 * @author sichu
 * @date 2022/04/04
 */
public class Rock extends Sprite {

    public Rock(double x, double y) {
        super(new Image("/images/male.jpg"), x, y, 112, 112);
    }

}
