package cn.sichu.fxgame.controller;

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

    }

    @FXML
    void mouseEnteredStartGame(MouseEvent event) {
        // startGame.setOpacity(0.5);
    }

    @FXML
    void mouseExitedStartGame(MouseEvent event) {
        // startGame.setOpacity(1);
    }

}
