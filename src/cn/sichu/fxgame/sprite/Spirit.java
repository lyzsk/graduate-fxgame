package cn.sichu.fxgame.sprite;

import cn.sichu.fxgame.Director;
import cn.sichu.fxgame.scene.GameScene;
import cn.sichu.fxgame.utils.Direction;
import cn.sichu.fxgame.utils.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Spirit extends Role {

    Direction headDir;
    boolean keyup, keydown, keyleft, keyright;

    /**
     * 宽高写死
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param gameScene
     * @param group
     * @param direction
     */
    public Spirit(double x, double y, GameScene gameScene, Group group, Direction direction, Direction headDir) {
        super(x, y, 112, 112, gameScene, group, direction);

        this.headDir = headDir;
        speed = 5;

        if (group.equals(group.PLAYER)) {
            imageMap.put("up", new Image("images/spirit.gif"));
            imageMap.put("down", new Image("images/spirit.gif"));
            imageMap.put("left", new Image("images/spirit.gif"));
            imageMap.put("right", new Image("images/spirit.gif"));
        } else if (group.equals(group.ENEMY)) {
            imageMap.put("up", new Image("images/prpr_left.gif"));
            imageMap.put("down", new Image("images/prpr_right.gif"));
            imageMap.put("left", new Image("images/prpr_left.gif"));
            imageMap.put("right", new Image("images/prpr_right.gif"));
        }

    }

    public void pressed(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                keyup = true;
                break;
            case DOWN:
                keydown = true;
                break;
            case LEFT:
                keyleft = true;
                break;
            case RIGHT:
                keyright = true;
                break;
        }
        reDirect();
    }

    public void released(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                keyup = false;
                break;
            case DOWN:
                keydown = false;
                break;
            case LEFT:
                keyleft = false;
                break;
            case RIGHT:
                keyright = false;
                break;
        }
        reDirect();
    }

    public void reDirect() {
        if (keyup && !keydown && !keyleft && !keyright) {
            direction = Direction.UP;
        } else if (!keyup && keydown && !keyleft && !keyright) {
            direction = Direction.DOWN;
        } else if (!keyup && !keydown && keyleft && !keyright) {
            direction = Direction.LEFT;
        } else if (!keyup && !keydown && !keyleft && keyright) {
            direction = Direction.RIGHT;
        } else {
            direction = Direction.STOP;
        }
    }

    @Override
    public void move() {

        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }

        if (direction != Direction.STOP) {
            headDir = direction;
        }

        if (x < 0) {
            x = 0;
        }
        /**
         * 还有窗口栏的y值要-
         */
        if (y < 0) {
            y = 0;
        }
        if (x > Director.WIDTH - width) {
            x = Director.WIDTH - width;
        }
        if (y > Director.HEIGHT - height) {
            y = Director.HEIGHT - height;
        }
    }

    /**
     * 重写Sprite的paint()
     */
    @Override
    public void paint(GraphicsContext graphicsContext) {

        super.paint(graphicsContext);

        switch (headDir) {
            case UP:
                image = imageMap.get("up");
                break;
            case DOWN:
                image = imageMap.get("down");
                break;
            case LEFT:
                image = imageMap.get("left");
                break;
            case RIGHT:
                image = imageMap.get("right");
                break;
        }

        move();

    }

    @Override
    public boolean impactChecking(Sprite sprite) {
        // TODO Auto-generated method stub
        return false;
    }

}
