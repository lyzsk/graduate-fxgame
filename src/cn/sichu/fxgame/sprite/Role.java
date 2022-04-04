package cn.sichu.fxgame.sprite;

import java.util.HashMap;
import java.util.Map;

import cn.sichu.fxgame.scene.GameScene;
import cn.sichu.fxgame.utils.Direction;
import cn.sichu.fxgame.utils.Group;
import javafx.scene.image.Image;

/**
 * move(), impactChecking(Sprite sprite) 由子类实现，所以abstract
 * 
 * @author sichu
 * @date 2022/04/04
 */
public abstract class Role extends Sprite {

    boolean alive = true;
    Group group;
    Direction direction;
    double speed;
    Map<String, Image> imageMap = new HashMap<String, Image>();

    /**
     * speed在子类写死不需要构造
     * <p>
     * 父类的image根据方向改变初始化为null，并且不构造
     * 
     * @param image
     * @param x
     * @param y
     * @param width
     * @param height
     * @param gameScene
     * @param group
     * @param direction
     */
    public Role(double x, double y, double width, double height, GameScene gameScene, Group group,
        Direction direction) {
        super(null, x, y, width, height, gameScene);
        this.group = group;
        this.direction = direction;
    }

    public abstract void move();

    public abstract boolean impactChecking(Sprite sprite);
}
