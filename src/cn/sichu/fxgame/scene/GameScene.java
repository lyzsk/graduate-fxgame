package cn.sichu.fxgame.scene;

import java.util.ArrayList;
import java.util.List;

import cn.sichu.fxgame.Director;
import cn.sichu.fxgame.sprite.Background;
import cn.sichu.fxgame.sprite.Bullet;
import cn.sichu.fxgame.sprite.Explode;
import cn.sichu.fxgame.sprite.Obstacle;
import cn.sichu.fxgame.sprite.Rock;
import cn.sichu.fxgame.sprite.Spirit;
import cn.sichu.fxgame.sprite.Tree;
import cn.sichu.fxgame.utils.Direction;
import cn.sichu.fxgame.utils.Group;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * @author sichu
 * @date 2022/04/04
 */
public class GameScene {

    private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    private KeyProcess keyProcess = new KeyProcess();
    private Refresh refresh = new Refresh();
    private boolean running = false;

    private Background background = new Background();
    private Spirit self = null;
    /**
     * 容器
     */
    public List<Bullet> bullets = new ArrayList<Bullet>();
    public List<Spirit> spirits = new ArrayList<Spirit>();
    public List<Explode> explodes = new ArrayList<Explode>();
    public List<Obstacle> obstacles = new ArrayList<Obstacle>();
    public List<Rock> rocks = new ArrayList<Rock>();
    public List<Tree> trees = new ArrayList<Tree>();

    /**
     * 绘制所有内容
     * <p>
     * 不能用forEach，否则concurrent报错
     */
    private void paint() {
        background.paint(graphicsContext);
        self.paint(graphicsContext);
        self.impactChecking(spirits);
        self.impactChecking(obstacles);
        self.impactChecking(rocks);

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.paint(graphicsContext);
            bullet.impactObstacleChecking(obstacles);
            bullet.impactSpiritChecking(spirits);
            bullet.impactRockChecking(rocks);
            bullet.impactSpiritChecking(self);
        }

        for (int i = 0; i < spirits.size(); i++) {
            Spirit spirit = spirits.get(i);
            spirit.paint(graphicsContext);
            spirit.impactChecking(obstacles);
            spirit.impactChecking(self);
            spirit.impactChecking(rocks);
            spirit.impactChecking(spirits);
        }

        for (int i = 0; i < explodes.size(); i++) {
            Explode explode = explodes.get(i);
            explode.paint(graphicsContext);
        }

        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);
            obstacle.paint(graphicsContext);
        }

        for (int i = 0; i < rocks.size(); i++) {
            Rock rock = rocks.get(i);
            rock.paint(graphicsContext);
        }

        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            tree.paint(graphicsContext);
        }

        graphicsContext.setFill(Color.RED);
        graphicsContext.setFont(new Font(15));
        graphicsContext.fillText("Number Of Enemies: " + spirits.size(), 800, 30);
        graphicsContext.fillText("Number Of Bullets: " + bullets.size(), 800, 60);

        if (!self.isAlive()) {
            Director.getInstance().gameOver(false);
        } else if (spirits.isEmpty()) {
            Director.getInstance().gameOver(true);
        }
    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane(canvas);
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyReleased(keyProcess);
        stage.getScene().setOnKeyPressed(keyProcess);
        running = true;
        self = new Spirit((960 - 112) >> 1, 540 - 112, this, Group.PLAYER, Direction.STOP, Direction.UP);
        initSprite();
        refresh.start();
    }

    /**
     * 私有 初始化sprite的方法
     */
    private void initSprite() {
        for (int i = 0; i < 6; i++) {
            Spirit spirit = new Spirit(200 + i * 80, 100, this, Group.ENEMY, Direction.STOP, Direction.DOWN);
            spirits.add(spirit);
        }

        for (int i = 0; i < 17; i++) {
            Obstacle obstacle1 = new Obstacle(0 + i * 56, 200);
            Obstacle obstacle2 = new Obstacle(0 + i * 56, 200 + 56);
            obstacles.add(obstacle1);
            obstacles.add(obstacle2);
        }

        for (int i = 0; i < 2; i++) {
            Rock rock = new Rock(0 + i * (960 - 112), 540 - 112);
            rocks.add(rock);
        }

        for (int i = 0; i < 1; i++) {
            Tree tree = new Tree(((960 - 112) / 2 + i * 56), 540 - 112);
            trees.add(tree);
        }
    }

    public void clear(Stage stage) {

        stage.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, keyProcess);
        stage.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, keyProcess);
        refresh.stop();
        self = null;
        spirits.clear();
        bullets.clear();
        obstacles.clear();
        explodes.clear();
        rocks.clear();
        trees.clear();
    }

    private class Refresh extends AnimationTimer {

        @Override
        public void handle(long now) {
            /**
             * 只有在running才循环刷新paint()
             */
            if (running) {
                paint();
            }
        }

    }

    private class KeyProcess implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {

            KeyCode keyCode = event.getCode();

            if (event.getEventType() == KeyEvent.KEY_RELEASED) {

                /**
                 * 暂停
                 */
                if (keyCode.equals(keyCode.P)) {
                    pauseOrContinue();
                }

                if (self != null) {
                    self.released(keyCode);
                }

            } else if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                if (self != null) {
                    self.pressed(keyCode);
                }
            }
        }

    }

    public void pauseOrContinue() {
        if (running) {
            running = false;
        } else {
            running = true;
        }
    }

}
