package cn.sichu.fxgame;

import cn.sichu.fxgame.scene.GameScene;
import cn.sichu.fxgame.scene.Index;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author sichu
 * @date 2022/04/04
 */
public class Director {

    private static Director instance = new Director();

    public static final double WIDTH = 960;
    public static final double HEIGHT = 540;

    private Stage stage;
    private GameScene gameScene = new GameScene();

    private Director() {

    }

    public static Director getInstance() {
        return instance;
    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("FXGAME");
        stage.getIcons().add(new Image("images/icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        this.stage = stage;
        toIndex(stage);
        stage.show();
    }

    public void gameStart() {
        gameScene.init(stage);
    }

    public void gameOver() {

    }

    /**
     * 调用toIndex()跳转到主页
     */
    public void toIndex(Stage stage) {
        Index.load(stage);
    }
}
