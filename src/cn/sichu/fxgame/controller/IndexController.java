package cn.sichu.fxgame.controller;

import cn.sichu.fxgame.Director;
import cn.sichu.fxgame.utils.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author sichu
 * @date 2022/04/04
 */
public class IndexController {

    @FXML
    private ImageView startGame;

    @FXML
    void mouseClickedStartGame(MouseEvent event) {
        Director.getInstance().gameStart();
        SoundEffect.play("/sounds/button.wav");
    }

    @FXML
    void mouseEnteredStartGame(MouseEvent event) {
        startGame.setOpacity(0.5);
        SoundEffect.play("/sounds/isshoniikimashou.mp3");
    }

    @FXML
    void mouseExitedStartGame(MouseEvent event) {
        startGame.setOpacity(1);
    }

}
