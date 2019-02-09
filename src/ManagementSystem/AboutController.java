/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagementSystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Spark
 */
public class AboutController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane exitPane;

    @FXML
    void exitAction(MouseEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    void hoverEnterEffect(MouseEvent event) {
        exitPane.setVisible(true);
    }

    @FXML
    void hoverExitEffect(MouseEvent event) {
        exitPane.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
