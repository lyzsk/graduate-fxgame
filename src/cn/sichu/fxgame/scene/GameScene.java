package cn.sichu.fxgame.scene;

import cn.sichu.fxgame.Director;
import cn.sichu.fxgame.sprite.Background;
import cn.sichu.fxgame.sprite.Spirit;
import cn.sichu.fxgame.utils.Direction;
import cn.sichu.fxgame.utils.Group;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    private Spirit self = new Spirit((960 - 112) >> 1, 540 - 112, this, Group.PLAYER, Direction.STOP, Direction.UP);

    /**
     * 绘制所有内容
     */
    private void paint() {
        background.paint(graphicsContext);
        self.paint(graphicsContext);
    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane(canvas);
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyReleased(keyProcess);
        stage.getScene().setOnKeyPressed(keyProcess);
        running = true;
        refresh.start();
    }

    public void clear(Stage stage) {
        stage.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, keyProcess);
        refresh.stop();
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
                if (keyCode.equals(keyCode.SPACE)) {
                    pauseOrContinue();
                }

                self.released(keyCode);

            } else if (event.getEventType() == KeyEvent.KEY_PRESSED) {

                self.pressed(keyCode);

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
