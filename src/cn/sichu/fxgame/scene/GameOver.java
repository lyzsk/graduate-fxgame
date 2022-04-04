package cn.sichu.fxgame.scene;

import java.io.IOException;

import cn.sichu.fxgame.controller.GameOverController;
import cn.sichu.fxgame.utils.SoundEffect;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class GameOver {
    public static void load(Stage stage, boolean success) {

        try {
            SoundEffect.play("/sounds/lose.mp3");

            FXMLLoader fxmlLoader = new FXMLLoader(Index.class.getResource("/fxml/GameOver.fxml"));
            Parent root = fxmlLoader.load();
            GameOverController gameOverController = fxmlLoader.getController();
            if (success) {
                SoundEffect.play("/sounds/win.mp3");
                gameOverController.flagSuccess();

            }

            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
