package cn.sichu.fxgame;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application把窗口传给Director
 * 
 * @author sichu
 * @date 2022/04/04
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Director.getInstance().init(primaryStage);

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
