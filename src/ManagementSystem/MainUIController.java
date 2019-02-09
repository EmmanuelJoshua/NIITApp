/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagementSystem;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainUIController implements Initializable {

    public static String adminID;

    @FXML
    private AnchorPane rootPane;

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
    private ImageView slider;

    @FXML
    private ImageView minimise;

    @FXML
    private ImageView exit;
    int counter = 0;

    @FXML
    private void hoverEnterMain(MouseEvent event) {
        Image image = new Image("/ManagementSystem/images/close4.png");
        exit.setImage(image);

    }

    @FXML
    private void hoverExitMain(MouseEvent event) {
        Image image = new Image("/ManagementSystem/images/close5.png");
        exit.setImage(image);
    }

    @FXML
    private void exitAction(MouseEvent event) {
        System.exit(0);
    }

    public void closeStage() {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    public void loadWindow(String path, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(path));

            Stage stage = new Stage(StageStyle.UNDECORATED);

            stage.setTitle(title);

            stage.setScene(new Scene(parent));
            stage.initOwner(((Stage) rootPane.getScene().getWindow()));
            stage.initModality(Modality.WINDOW_MODAL);

            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
//            AlertMaker.showErrorMessage(ex);
        }
    }

    @FXML
    public void StudentInformationEvent(ActionEvent event) {
        loadWindow("StudentInformation.fxml", "Student Information");
    }

    @FXML
    public void StudentEnquiriesEvent(ActionEvent event) {
        loadWindow("StudentEnquiries.fxml", "Enquiries");
    }

    @FXML
    public void employeeEvent(ActionEvent event) {
        loadWindow("Employee.fxml", "Employee");
    }

    @FXML
    public void paymentEvent(ActionEvent event) {
        loadWindow("Payment.fxml", "Payment");
    }

    @FXML
    public void settingsEvent(ActionEvent event) {
        loadWindow("Settings.fxml", "Settings");
    }

    @FXML
    public void aboutEvent(ActionEvent event) {
        loadWindow("About.fxml", "About");
    }

    @FXML
    public void minimiseEvent(MouseEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void logOut(ActionEvent event) {

        closeStage();
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));

            String path = "/ManagementSystem/images/homeA.png";

            Image img = new Image(path);

            Stage stage = new Stage(StageStyle.UNDECORATED);

            Scene scene = new Scene(root);
            stage.getIcons().add(img);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Login");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void slideShow() {

        Image image1 = new Image("/ManagementSystem/images/home1.jpg");
        Image image2 = new Image("/ManagementSystem/images/home2.jpg");
        Image image3 = new Image("/ManagementSystem/images/home3.jpg");

        FadeTransition transition = new FadeTransition(Duration.seconds(1), slider);
        transition.setDelay(Duration.seconds(6));
//        transition.setFromValue(1);
//        transition.setToValue(0);

        transition.setOnFinished((ActionEvent event) -> {
            counter++;
            switch (counter % 3) {
                case 0:
                    slider.setImage(image1);
                    break;
                case 1:
                    slider.setImage(image2);
                    break;
                case 2:
                    slider.setImage(image3);
                    break;
                default:
                    break;
            }
            transition.playFromStart();
        });
        transition.play();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slideShow();
        if (adminID.equals("NIIT_Amos")) {
            employeeInfo.setVisible(true);
        } else {
            employeeInfo.setVisible(false);
            paymentStatus.setLayoutX(583);
            paymentStatus.setLayoutY(262);
            settings.setLayoutX(583);
            settings.setLayoutY(465);
            about.setLayoutX(1009);
            about.setLayoutY(193);
        }
    }

}
