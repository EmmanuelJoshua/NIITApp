/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niitapp;

import niitapp.validation.Validator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Spark
 */
public class AppDesignController implements Initializable {
    
    @FXML
    private AnchorPane rootpane;
    
    @FXML
    private Label errorMessage;
    
    @FXML
    private JFXTextField user_name;
    
    @FXML
    private JFXPasswordField pass_word;
    
    @FXML
    private void nextScene(ActionEvent event) {
        Image img = new Image("/niitapp/images/checked.png");
        if (user_name.getText().isEmpty() && pass_word.getText().isEmpty()) {
            errorMessage.setText("Username and Password is Empty");
        } else if (!userLogin(user_name.getText(), pass_word.getText())) {
            errorMessage.setText("Invalid Username and Password");
        } else {
            Notifications pushNotify = Notifications.create()
                    .title("Successful Login")
                    .graphic(new ImageView(img))
                    .text("Password Verified. Logged in as Admin.")
                    .hideAfter(Duration.seconds(10))
                    .position(Pos.BOTTOM_RIGHT);
            pushNotify.show();
            nextScene();
        }
    }
    
    private boolean userLogin(String username, String password) {
        if (Validator.validate(username, password)) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public void nextScene() {
        try {
            Parent view;
            view = (AnchorPane) FXMLLoader.load(getClass().getResource("MainUI.fxml"));
            
            Scene MainUI = new Scene(view);
            
            Stage stage = (Stage) rootpane.getScene().getWindow();
            stage.setTitle("NIIT Home");
            stage.setScene(MainUI);
            stage.centerOnScreen();
        } catch (IOException ex) {
            Logger.getLogger(AppDesignController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
