/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagementSystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Spark
 */
public class SettingsController implements Initializable {

    Image lolImg = new Image("/ManagementSystem/images/glass.png");

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane exitPane;

    @FXML
    private void exitAction(MouseEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void hoverEnterEffect(MouseEvent event) {
        exitPane.setVisible(true);
    }

    @FXML
    private void hoverExitEffect(MouseEvent event) {
        exitPane.setVisible(false);
    }

    @FXML
    private void apply(ActionEvent event) {
        Notifications notify = Notifications.create()
                .darkStyle()
                .graphic(new ImageView(lolImg))
                .title("SORRY")
                .text("Lmao...That doesn't work yet!")
                .position(Pos.BOTTOM_RIGHT)
                .hideAfter(Duration.seconds(5));
        notify.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
