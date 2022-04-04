package cn.sichu.fxgame.controller;

import cn.sichu.fxgame.Director;
import cn.sichu.fxgame.utils.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author sichu
 * @date 2022/04/04
 */
public class GameOverController {

    @FXML
    private ImageView flag;

    @FXML
    private ImageView toIndex;

    @FXML
    void mouseClickedToIndex(MouseEvent event) {
        Director.getInstance().toIndex();
        SoundEffect.play("/sounds/button.wav");
    }

    @FXML
    void mouseEnteredToIndex(MouseEvent event) {
        SoundEffect.play("/sounds/isshoniikimashou.mp3");
    }

    @FXML
    void mouseExitedToIndex(MouseEvent event) {

    }

    public void flagSuccess() {
        flag.setImage(new Image("/images/bg2.png", true));
    }
}
