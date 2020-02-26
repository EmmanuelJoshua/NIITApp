/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niitapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import static javafx.scene.paint.Color.BLACK;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainUIController implements Initializable {

    @FXML
    private StackPane dialogPane;

    @FXML
    private JFXButton studentEnquiries;

    @FXML
    private JFXButton employeeInfo;

    @FXML
    private JFXButton studentInfo;

    @FXML
    private JFXButton paymentStatus;

    @FXML
    private JFXButton settings;

    @FXML
    private JFXButton about;

    @FXML
    private JFXButton logout;

    @FXML
    public void logOut(ActionEvent event) {
//        Text header = new Text("CONFIRM");
//        header.setFill(studentEnquiries.getTextFill());
//        JFXDialogLayout content = new JFXDialogLayout();
//        content.setHeading(header);
//        content.setBody(new Text("Are You Sure You Want To Log Out?"));
//        JFXDialog dialog = new JFXDialog(dialogPane, content, JFXDialog.DialogTransition.CENTER);
//        JFXButton button1 = new JFXButton("Yes");
//        JFXButton button2 = new JFXButton("No");
//        button1.setButtonType(JFXButton.ButtonType.RAISED);
//        button2.setButtonType(JFXButton.ButtonType.RAISED);
//        button1.setOnAction((eventA) -> {
//            Parent view;
//            try {
//                view = (AnchorPane) FXMLLoader.load(getClass().getResource("FlightServiceFXML.fxml"));
//                Scene flightScene = new Scene(view);
//
//                Stage stage = (Stage) mainPane.getScene().getWindow();
//                stage.setScene(flightScene);
//                stage.centerOnScreen();
//            } catch (IOException ex) {
//                Logger.getLogger(BookingAppController.class.getName()).log(Level.SEVERE, null, ex);
//            }

//        });
//        button2.setOnAction((eventA) -> {
//            dialog.close();
//        });
//        content.setActions(button1, button2);
//        dialog.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
