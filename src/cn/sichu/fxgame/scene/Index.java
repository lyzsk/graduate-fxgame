package cn.sichu.fxgame.scene;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * 主页
 * 
 * @author sichu
 * @date 2022/04/04
 */
public class Index {

    public static void load(Stage stage) {
        /*
         * 用反射的方法调用url,首先getScene()拿到场景对象，再把root放进根节点
         */
        try {
            Parent root = FXMLLoader.load(Index.class.getResource("/fxml/Index.fxml"));
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
