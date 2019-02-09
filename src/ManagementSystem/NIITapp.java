/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagementSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Spark
 */
public class NIITapp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));

        String path = "/ManagementSystem/images/homeA.png";

        Image img = new Image(path);

        Scene scene = new Scene(root);
        stage.getIcons().add(img);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Login");
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
