/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagementSystem;

import com.jfoenix.controls.JFXTextField;
import impl.org.controlsfx.i18n.SimpleLocalizedStringProperty;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Spark
 */
public class PaymentController implements Initializable {

    ObservableList<PaymentInfo> list = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<PaymentInfo> PaymentInfo;

    @FXML
    private TableColumn<PaymentInfo, String> nameCol;

    @FXML
    private TableColumn<PaymentInfo, String> statusCol;

    @FXML
    private TableColumn<PaymentInfo, String> amountCol;

    @FXML
    private TableColumn<PaymentInfo, String> paymentCol;

    @FXML
    private TableColumn<PaymentInfo, String> courseCol;

    @FXML
    private TableColumn<PaymentInfo, String> balanceCol;

    @FXML
    private Pane exitPane;

    @FXML
    private Pane feeDetails;

    @FXML
    private Pane addPayment;

    @FXML
    private JFXTextField nameSearch;
    String where = "";

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
    public void feeDetails_Payment(ActionEvent event) {
        feeDetails.setVisible(true);
        addPayment.setVisible(false);
    }

    @FXML
    public void addPayment_Payment(ActionEvent event) {
        addPayment.setVisible(true);
        feeDetails.setVisible(false);
    }

    @FXML
    public void delete(ActionEvent event) {

    }

    @FXML
    public void refresh(ActionEvent event) {

    }

    @FXML
    public void search(ActionEvent event) {

    }

    @FXML
    public void update(ActionEvent event) {

    }

    public void initCols() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("admissionStatus"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

    public void loadTableData() {
        String sql = "SELECT * FROM PaymentInformation " + where;
        ResultSet rs = Database.executeQuery(sql);
        try {
            while (rs.next()) {
                String name1 = rs.getString("fullname");
                String status1 = rs.getString("admissionStatus");
                String amount1 = rs.getString("amountPaid");
                String payment1 = rs.getString("paymentStatus");
                String course1 = rs.getString("course");
                list.add(new PaymentInfo(name1, status1, amount1, payment1, course1, ""));
            }
            rs.close();
        } catch (SQLException e) {
            
        }
        PaymentInfo.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCols();
        loadTableData();
    }

    public static class PaymentInfo {

        private final SimpleStringProperty name;
        private final SimpleStringProperty admissionStatus;
        private final SimpleStringProperty amountPaid;
        private final SimpleStringProperty paymentStatus;
        private final SimpleStringProperty course;
        private final SimpleStringProperty balance;

        public PaymentInfo(String name, String admissionStatus, String amountPaid, String paymentStatus, String course, String balance) {
            this.name = new SimpleStringProperty(name);
            this.admissionStatus = new SimpleStringProperty(admissionStatus);
            this.amountPaid = new SimpleStringProperty(amountPaid);
            this.paymentStatus = new SimpleStringProperty(paymentStatus);
            this.course = new SimpleStringProperty(course);
            this.balance = new SimpleStringProperty(balance);
        }

        public String getName() {
            return name.get();
        }

        public String getAdmissionStatus() {
            return admissionStatus.get();
        }

        public String getAmountPaid() {
            return amountPaid.get();
        }

        public String getPaymentStatus() {
            return paymentStatus.get();
        }

        public String getCourse() {
            return course.get();
        }

        public String getBalance() {
            return balance.get();
        }
    }
}
