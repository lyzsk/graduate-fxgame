package cn.sichu.fxgame.sprite;

import cn.sichu.fxgame.scene.GameScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 不能被实例化，只用来继承
 * 
 * @author sichu
 * @date 2022/04/04
 */
public abstract class Sprite {

    Image image;
    double x, y, width, height;
    GameScene gameScene;

    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x, y, width, height);
    }

    /**
     * 用于碰撞检测
     * 
     * @return new Rectangle2D
     */
    public Rectangle2D getContour() {
        return new Rectangle2D(x, y, width, height);
    }

    /**
     * 有些不需要销毁，比如背景，所以不abstract
     */
    public void destroy() {

    }

    public Sprite(Image image, double x, double y, double width, double height, GameScene gameScene) {
        super();
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gameScene = gameScene;
    }

    public Sprite(Image image, double x, double y, double width, double height) {
        super();
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

}
