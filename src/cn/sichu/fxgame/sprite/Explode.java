package cn.sichu.fxgame.sprite;

import cn.sichu.fxgame.scene.GameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explode extends Sprite {

    private static Image[] images = new Image[] {new Image("images/explode1.png"), new Image("images/explode2.png"),
        new Image("images/explode3.png"), new Image("images/explode4.png"), new Image("images/explode5.png"),
        new Image("images/explode6.png"), new Image("images/explode7.png"), new Image("images/explode8.png"),
        new Image("images/explode9.png")};
    private int count = 0;

    public Explode(double x, double y, GameScene gameScene) {
        super(null, x, y, 0, 0, gameScene);

    }

    @Override
    public void paint(GraphicsContext graphicsContext) {

        super.paint(graphicsContext);

        if (count >= images.length) {
            gameScene.explodes.remove(this);
            return;
        }

        image = images[count];
        double explode_x = x - image.getWidth() / 2;
        double explode_y = y - image.getHeight() / 2;
        graphicsContext.drawImage(image, explode_x, explode_y);
        ++count;
    }

}
