/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagementSystem;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author Spark
 */
public class StudentEnquiriesController implements Initializable {

    private static final String NAME = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}";
    private static final String PHONE = "\\d{11}";
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    ObservableList<EnquiriesDetails> list = FXCollections.observableArrayList();

    Image checkImg = new Image("/ManagementSystem/images/checked.png");
    Image errorImg = new Image("/ManagementSystem/images/cross.png");
    Image warnImg = new Image("/ManagementSystem/images/warning.png");
    Image lolImg = new Image("/ManagementSystem/images/glass.png");

    @FXML
    private TableView<EnquiriesDetails> enquiriesInfo;

    @FXML
    private TableColumn<EnquiriesDetails, String> dateCol;

    @FXML
    private TableColumn<EnquiriesDetails, String> nameCol;

    @FXML
    private TableColumn<EnquiriesDetails, String> dobCol;

    @FXML
    private TableColumn<EnquiriesDetails, String> genderCol;

    @FXML
    private TableColumn<EnquiriesDetails, String> phoneCol;

    @FXML
    private TableColumn<EnquiriesDetails, String> emailCol;

    @FXML
    private TableColumn<EnquiriesDetails, String> educationCol;

    @FXML
    private TableColumn<EnquiriesDetails, String> employCol;

    @FXML
    private TableColumn<EnquiriesDetails, String> schoolCol;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane exitPane;

    @FXML
    private Pane addEnquiriesPane;

    @FXML
    private Pane viewEnquiriesPane;

    @FXML
    private Text enquiryLbl;

    @FXML
    private Text maleLbl;

    @FXML
    private Text femaleLbl;

    @FXML
    private JFXTextField surname;

    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField institution;

    @FXML
    private JFXDatePicker dateOfEnquiry;

    @FXML
    private JFXDatePicker D_O_B;

    @FXML
    private JFXComboBox education;

    @FXML
    private JFXComboBox employmentStatus;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;
    
    @FXML
    private JFXCheckBox secSchool;
    
    @FXML
    private JFXCheckBox ins;

    public String where = "";

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
    public void addEnq_StudEnq(ActionEvent event) {
        addEnquiriesPane.setVisible(true);
        viewEnquiriesPane.setVisible(false);
    }

    @FXML
    public void viewEnq_StudEnq(ActionEvent event) {
        viewEnquiriesPane.setVisible(true);
        addEnquiriesPane.setVisible(false);
    }

    String genderSelect;

    @FXML
    public void submitDetails(ActionEvent event) {

        if (gender.getSelectedToggle() == male) {
            genderSelect = "Male";
        } else if (gender.getSelectedToggle() == female) {
            genderSelect = "Female";
        } else {
            genderSelect = null;
        }

        if (validationCheck()) {
            String sql = "INSERT INTO StudentEnquiries VALUES('"
                    + dateOfEnquiry.getValue().toString() + "','"
                    + surname.getText() + " " + firstname.getText() + "','"
                    + D_O_B.getValue().toString() + "','"
                    + genderSelect + "','"
                    + phone.getText() + "','"
                    + email.getText() + "','"
                    + education.getValue().toString() + "','"
                    + employmentStatus.getValue().toString() + "','"
                    + institution.getText() + "')";
            int val = Database.insertData(sql);
            if (val != 0) {
                Notifications notify = Notifications.create()
                        .darkStyle()
                        .graphic(new ImageView(checkImg))
                        .title("SUCCESS")
                        .text("Insertion Succesful")
                        .position(Pos.BOTTOM_RIGHT)
                        .hideAfter(Duration.seconds(5));
                notify.show();
            } else {
                Notifications notify = Notifications.create()
                        .darkStyle()
                        .graphic(new ImageView(errorImg))
                        .title("ERROR")
                        .text("Insertion Failed")
                        .position(Pos.BOTTOM_RIGHT)
                        .hideAfter(Duration.seconds(5));
                notify.show();
            }
        } else {
            Notifications notify = Notifications.create()
                    .darkStyle()
                    .graphic(new ImageView(warnImg))
                    .title("WARNING")
                    .text("Insert Data Correctly!")
                    .position(Pos.BOTTOM_RIGHT)
                    .hideAfter(Duration.seconds(5));
            notify.show();
        }

    }

    @FXML
    public void refresh(ActionEvent event) {
        where = "";
        enquiriesInfo.getItems().clear();
        list.removeAll(list);
        loadtabledata();

    }

    public boolean validationCheck() {

        if ("".equals(surname.getText())) {
            return false;
        }
        if ("".equals(firstname.getText())) {
            return false;
        }

        if ("".equals(phone.getText())) {
            return false;
        }

        if ("".equals(email.getText())) {
            return false;
        }

        if (D_O_B.getValue().toString().isEmpty()) {
            return false;
        }

        if ("".equals(institution.getText())) {
            return false;
        }

        if (dateOfEnquiry.getValue().toString().isEmpty()) {
            return false;
        }

        if ("".equals(education.getValue().toString())) {
            return false;
        }

        if ("".equals(employmentStatus.getValue().toString())) {
            return false;
        }
        if (!Pattern.matches(EMAIL_PATTERN, email.getText())) {
            return false;
        }

        if (!Pattern.matches(PHONE, phone.getText())) {
            return false;
        }

        if (!Pattern.matches(NAME, surname.getText())) {
            return false;
        }

        if (!Pattern.matches(NAME, firstname.getText())) {
            return false;
        }
        return true;
    }

    @FXML
    public void delete(ActionEvent event) {
        try {
            EnquiriesDetails enqDet = enquiriesInfo.getSelectionModel().getSelectedItem();
            String sql = "DELETE FROM StudentEnquiries WHERE fullname = '" + enqDet.getFullName() + "' and emailID = '" + enqDet.getEmailAddress() + "'";
            if (Database.insertData(sql) > 0) {
                enquiriesInfo.getItems().clear();
                list.removeAll(list);
                loadtabledata();
                Notifications notify = Notifications.create()
                        .darkStyle()
                        .graphic(new ImageView(checkImg))
                        .title("SUCCESS")
                        .text("Deletion Succesful")
                        .position(Pos.BOTTOM_RIGHT)
                        .hideAfter(Duration.seconds(5));
                notify.show();
            } else {
                Notifications notify = Notifications.create()
                        .darkStyle()
                        .graphic(new ImageView(errorImg))
                        .title("ERROR")
                        .text("Deletion Failed")
                        .position(Pos.BOTTOM_RIGHT)
                        .hideAfter(Duration.seconds(5));
                notify.show();
            }
        } catch (Exception e) {
            Notifications notify = Notifications.create()
                    .darkStyle()
                    .graphic(new ImageView(errorImg))
                    .title("ERROR")
                    .text("No Selection Made")
                    .position(Pos.BOTTOM_RIGHT)
                    .hideAfter(Duration.seconds(5));
            notify.show();
        }
    }

    @FXML
    public void search(ActionEvent event) {
        where = " WHERE fullname = '" /*+ nameSearch.getText()*/ + "'";
        enquiriesInfo.getItems().clear();
        list.removeAll(list);
        String sql = "SELECT * FROM StudentEnquiries" + where;
        ResultSet rs = Database.executeQuery(sql);
        try {
            String DOF = rs.getString("dateOfEnq");
            String fullname1 = rs.getString("fullname");
            String dob1 = rs.getString("DOB");
            String gender1 = rs.getString("gender");
            String phone1 = rs.getString("phone");
            String email1 = rs.getString("emailID");
            String education1 = rs.getString("educationLevel");
            String employ1 = rs.getString("employmentStatus");
            String school1 = rs.getString("school");
            list.add(new StudentEnquiriesController.EnquiriesDetails(DOF, fullname1, dob1, gender1, phone1, email1, education1, employ1, school1));
            rs.close();
        } catch (SQLException ex) {

        }
        enquiriesInfo.getItems().setAll(list);
    }

    @FXML
    public void update(ActionEvent event) {
        Notifications notify = Notifications.create()
                        .darkStyle()
                        .graphic(new ImageView(lolImg))
                        .title("SORRY")
                        .text("Feature will be Available Soon!")
                        .position(Pos.BOTTOM_RIGHT)
                        .hideAfter(Duration.seconds(5));
                notify.show();
    }

    public void populateComboBoxes() {
        ObservableList educationFill = FXCollections.observableArrayList(
                "SSCE", "NCE", "OND", "HND", "Under Graduation",
                "Degree", "Post Graduation", "Master Degree", "PHD", "Others");
        education.setItems(educationFill);

        ObservableList current = FXCollections.observableArrayList(
                "Employed", "Self Employed", "Unemployed", "Studying");
        employmentStatus.setItems(current);

    }

    public void initCols() {

        dateCol.setCellValueFactory(new PropertyValueFactory<>("enquiryDate"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        educationCol.setCellValueFactory(new PropertyValueFactory<>("educationLevel"));
        employCol.setCellValueFactory(new PropertyValueFactory<>("employmentStatus"));
        schoolCol.setCellValueFactory(new PropertyValueFactory<>("school"));

    }

    public void validators() {
        ValidationSupport validation1 = new ValidationSupport();
        validation1.registerValidator(surname, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation2 = new ValidationSupport();
        validation2.registerValidator(surname, Validator.createRegexValidator("Provide Correct First Name", NAME, Severity.ERROR));
        ValidationSupport validation3 = new ValidationSupport();
        validation3.registerValidator(firstname, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation4 = new ValidationSupport();
        validation4.registerValidator(firstname, Validator.createRegexValidator("Provide Correct First Name", NAME, Severity.ERROR));
        ValidationSupport validation5 = new ValidationSupport();
        validation5.registerValidator(phone, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation6 = new ValidationSupport();
        validation6.registerValidator(phone, Validator.createRegexValidator("Provide Correct Number", PHONE, Severity.ERROR));
        ValidationSupport validation7 = new ValidationSupport();
        validation7.registerValidator(email, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation8 = new ValidationSupport();
        validation8.registerValidator(email, Validator.createRegexValidator("Input Required", EMAIL_PATTERN, Severity.ERROR));
        ValidationSupport validation9 = new ValidationSupport();
        validation9.registerValidator(education, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation10 = new ValidationSupport();
        validation10.registerValidator(employmentStatus, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation11 = new ValidationSupport();
        validation11.registerValidator(institution, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation12 = new ValidationSupport();
        validation12.registerValidator(dateOfEnquiry, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation13 = new ValidationSupport();
        validation13.registerValidator(D_O_B, Validator.createEmptyValidator("Input Required", Severity.WARNING));
    }

    public void loadtabledata() {
        String sql = "SELECT * FROM StudentEnquiries " + where;
        String sql2 = "SELECT count(gender) FROM StudentEnquiries";
        String sql3 = "SELECT count(gender) FROM StudentEnquiries where gender = 'Male'";
        String sql4 = "SELECT count(gender) FROM StudentEnquiries where gender = 'Female'";
        ResultSet rs = Database.executeQuery(sql);
        ResultSet rs1 = Database.executeQuery(sql2);
        ResultSet rs2 = Database.executeQuery(sql3);
        ResultSet rs3 = Database.executeQuery(sql4);
        try {
            while (rs.next()) {
                String DOF = rs.getString("dateOfEnq");
                String fullname1 = rs.getString("fullname");
                String dob1 = rs.getString("DOB");
                String gender1 = rs.getString("gender");
                String phone1 = rs.getString("phone");
                String email1 = rs.getString("emailID");
                String education1 = rs.getString("educationLevel");
                String employ1 = rs.getString("employmentStatus");
                String school1 = rs.getString("school");
                list.add(new EnquiriesDetails(DOF, fullname1, dob1, gender1, phone1, email1, education1, employ1, school1));
            }
            while (rs1.next()) {
                String num = rs1.getString("count(gender)");
                if (num.length() == 1) {
                    enquiryLbl.setText("00" + num);
                } else if (num.length() == 2) {
                    enquiryLbl.setText("0" + num);
                } else if (num.length() == 3) {
                    enquiryLbl.setText(num);
                }
            }
            while (rs2.next()) {
                String num = rs2.getString("count(gender)");
                if (num.length() == 1) {
                    maleLbl.setText("00" + num);
                } else if (num.length() == 2) {
                    maleLbl.setText("0" + num);
                } else if (num.length() == 3) {
                    maleLbl.setText(num);
                }
            }
            while (rs3.next()) {
                String num = rs3.getString("count(gender)");
                if (num.length() == 1) {
                    femaleLbl.setText("00" + num);
                } else if (num.length() == 2) {
                    femaleLbl.setText("0" + num);
                } else if (num.length() == 3) {
                    femaleLbl.setText(num);
                }

            }
            rs.close();
        } catch (SQLException ex) {

        }
        enquiriesInfo.getItems().setAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateComboBoxes();
        initCols();
        validators();
        loadtabledata();

    }

    public static class EnquiriesDetails {

        private final SimpleStringProperty enquiryDate;
        private final SimpleStringProperty fullName;
        private final SimpleStringProperty DOB;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty phone;
        private final SimpleStringProperty emailAddress;
        private final SimpleStringProperty educationLevel;
        private final SimpleStringProperty employmentStatus;
        private final SimpleStringProperty school;

        public EnquiriesDetails(String enquiryDate, String fullName, String DOB, String gender, String phone, String emailAddress, String educationLevel, String employmentStatus, String school) {
            this.enquiryDate = new SimpleStringProperty(enquiryDate);
            this.fullName = new SimpleStringProperty(fullName);
            this.DOB = new SimpleStringProperty(DOB);
            this.gender = new SimpleStringProperty(gender);
            this.phone = new SimpleStringProperty(phone);
            this.emailAddress = new SimpleStringProperty(emailAddress);
            this.educationLevel = new SimpleStringProperty(educationLevel);
            this.employmentStatus = new SimpleStringProperty(employmentStatus);
            this.school = new SimpleStringProperty(school);

        }

        public String getEnquiryDate() {
            return enquiryDate.get();
        }

        public String getFullName() {
            return fullName.get();
        }

        public String getDOB() {
            return DOB.get();
        }

        public String getGender() {
            return gender.get();
        }

        public String getPhone() {
            return phone.get();
        }

        public String getEmailAddress() {
            return emailAddress.get();
        }

        public String getEducationLevel() {
            return educationLevel.get();
        }

        public String getEmploymentStatus() {
            return employmentStatus.get();
        }

        public String getSchool() {
            return school.get();
        }
    }
}
