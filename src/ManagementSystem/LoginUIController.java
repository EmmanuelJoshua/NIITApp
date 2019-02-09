/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagementSystem;

import ManagementSystem.validation.Validator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Spark
 */
public class LoginUIController implements Initializable {
    
    Image errorImg= new Image("/ManagementSystem/images/cross.png");
    
    @FXML
    private JFXProgressBar progress;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXTextField user_name;

    @FXML
    private JFXPasswordField pass_word;

    @FXML
    private Pane exitPane;

    @FXML
    private void hoverEnterEffect(MouseEvent event) {
        exitPane.setVisible(true);
    }

    @FXML
    private void hoverExitEffect(MouseEvent event) {
        exitPane.setVisible(false);
    }

    @FXML
    private void exitAction(MouseEvent event) {
        System.exit(0);
    }

    public void closeStage() {
        ((Stage) rootpane.getScene().getWindow()).close();
    }

    @FXML
    private void nextScene(ActionEvent event) {
        if (user_name.getText().isEmpty() && pass_word.getText().isEmpty()) {
            Notifications notify=Notifications.create()
                        .darkStyle()
                        .graphic(new ImageView(errorImg))
                        .title("ERROR")
                        .text("Username and Password Empty")
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(3));
                notify.show();
        } else if (!userLogin(user_name.getText(), pass_word.getText())) {
           Notifications notify=Notifications.create()
                        .darkStyle()
                        .graphic(new ImageView(errorImg))
                        .title("ERROR")
                        .text("Invalid Login")
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(3));
                notify.show(); 
        } else {
            MainUIController.adminID = user_name.getText();
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            loginBtn.setMouseTransparent(true);
            progress.setVisible(true);
            pause.play();
            pause.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    closeStage();
                    next();
                }
            });
        }
    }

    private boolean userLogin(String username, String password) {
        if (Validator.validate(username, password)) {
            return true;
        } else {
            return false;
        }

    }

    public void next() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("MainUI.fxml"));

            String path = "/ManagementSystem/images/homeA.png";

            Image img = new Image(path);

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setTitle("Home");
            stage.getIcons().add(0, img);
            stage.setScene(new Scene(parent));
            stage.initOwner(((Stage) rootpane.getScene().getWindow()));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
