package cn.sichu.fxgame.sprite;

import java.util.List;

import cn.sichu.fxgame.Director;
import cn.sichu.fxgame.scene.GameScene;
import cn.sichu.fxgame.utils.Direction;
import cn.sichu.fxgame.utils.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 * @author sichu
 * @date 2022/04/04
 */
public class Bullet extends Role {

    public Bullet(double x, double y, GameScene gameScene, Group group, Direction direction) {
        super(x, y, 0, 0, gameScene, group, direction);
        speed = 10;

        if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
            width = 10;
            height = 22;
        } else if (direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)) {
            width = 22;
            height = 10;
        }

        if (group.equals(Group.ENEMY)) {
            switch (direction) {
                case UP:
                    image = new Image("images/bullet-green-up.png");
                    break;
                case DOWN:
                    image = new Image("images/bullet-green-down.png");
                    break;
                case LEFT:
                    image = new Image("images/bullet-green-left.png");
                    break;
                case RIGHT:
                    image = new Image("images/bullet-green-right.png");
                    break;
            }
        } else {
            switch (direction) {
                case UP:
                    image = new Image("images/bullet-red-up.png");
                    break;
                case DOWN:
                    image = new Image("images/bullet-red-down.png");
                    break;
                case LEFT:
                    image = new Image("images/bullet-red-left.png");
                    break;
                case RIGHT:
                    image = new Image("images/bullet-red-right.png");
                    break;
            }
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

        if (x < 0 || y < 0 || x > Director.WIDTH || y > Director.HEIGHT) {
            gameScene.bullets.remove(this);
        }
        // if (x < 0) {
        // x = 0;
        // }
        //
        // if (y < 0) {
        // y = 0;
        // }
        // if (x > Director.WIDTH - width) {
        // x = Director.WIDTH - width;
        // }
        // if (y > Director.HEIGHT - height) {
        // y = Director.HEIGHT - height;
        // }

    }

    @Override
    public void paint(GraphicsContext graphicsContext) {

        super.paint(graphicsContext);

        if (!alive) {
            gameScene.bullets.remove(this);
            gameScene.explodes.add(new Explode(x, y, gameScene));
            // SoundEffect.play("/sounds/yada.mp3");
            return;
        }
        move();
    }

    public boolean impactSpiritChecking(Spirit spirit) {
        if (spirit != null && !spirit.group.equals(this.group) && getContour().intersects(spirit.getContour())) {
            spirit.setAlive(false);
            alive = false;
            return true;
        }

        return false;
    }

    public void impactSpiritChecking(List<Spirit> spirits) {

        for (Spirit spirit : spirits) {
            impactSpiritChecking(spirit);
        }
    }

    public boolean impactObstacleChecking(Obstacle obstacle) {
        if (obstacle != null && getContour().intersects(obstacle.getContour())) {
            alive = false;
            gameScene.obstacles.remove(obstacle);
            return true;
        }
        return false;
    }

    /**
     * 使用forEach的容器，只能查询，不允许删除，否则报错concurrent
     * 
     * @param obstacles
     */
    public void impactObstacleChecking(List<Obstacle> obstacles) {
        // for (Obstacle obstacle : obstacles) {
        // impactObstacleChecking(obstacle);
        // }
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);
            impactObstacleChecking(obstacle);
        }
    }

    public boolean impactRockChecking(Rock rock) {
        if (rock != null && getContour().intersects(rock.getContour())) {
            alive = false;
            return true;
        }
        return false;
    }

    public void impactRockChecking(List<Rock> rocks) {

        for (int i = 0; i < rocks.size(); i++) {
            Rock rock = rocks.get(i);
            impactRockChecking(rock);
        }
    }
}
