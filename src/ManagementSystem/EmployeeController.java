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
import com.jfoenix.controls.JFXTextArea;
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
public class EmployeeController implements Initializable {

    private static final String NAME = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}";
    private static final String PHONE = "\\d{11}";
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    ObservableList<EmployeeInfo> list = FXCollections.observableArrayList();

    Image checkImg = new Image("/ManagementSystem/images/checked.png");
    Image errorImg = new Image("/ManagementSystem/images/cross.png");
    Image lolImg = new Image("/ManagementSystem/images/glass.png");

    @FXML
    private TableView<EmployeeInfo> employeeInfo;

    @FXML
    private TableColumn<EmployeeInfo, String> nameCol;

    @FXML
    private TableColumn<EmployeeInfo, String> dobCol;

    @FXML
    private TableColumn<EmployeeInfo, String> genderCol;

    @FXML
    private TableColumn<EmployeeInfo, String> phoneCol;

    @FXML
    private TableColumn<EmployeeInfo, String> emailCol;

    @FXML
    private TableColumn<EmployeeInfo, String> qualifyCol;

    @FXML
    private TableColumn<EmployeeInfo, String> skillsCol;

    @FXML
    private TableColumn<EmployeeInfo, String> maritalCol;

    @FXML
    private TableColumn<EmployeeInfo, String> bankCol;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane exitPane;

    @FXML
    private Pane addEmployeePane;

    @FXML
    private Pane viewEmployeePane;

    @FXML
    private Text employeeLbl;

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
    private JFXTextArea address;

    @FXML
    private JFXDatePicker DOB;

    @FXML
    private JFXComboBox qualify;

    @FXML
    private JFXComboBox marital;

    @FXML
    private JFXComboBox banks;

    @FXML
    private JFXComboBox searchField2;

    @FXML
    private JFXComboBox searchField3;

    @FXML
    private JFXTextField account;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXComboBox search_Opt;

    @FXML
    private JFXTextField qualify_others;

    @FXML
    private JFXTextField skills_others;

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXCheckBox javaDevOps;

    @FXML
    private JFXCheckBox python;

    @FXML
    private JFXCheckBox network;

    @FXML
    private JFXCheckBox web;

    @FXML
    private JFXCheckBox bigData;

    @FXML
    private JFXCheckBox advInt;

    @FXML
    private JFXCheckBox dataBase;

    @FXML
    private JFXCheckBox others;

    public String where = "";
    public String selecteditems = "";

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
    public void addEmployee_Employee(ActionEvent event) {
        addEmployeePane.setVisible(true);
        viewEmployeePane.setVisible(false);
    }

    @FXML
    public void viewEmployee_Employee(ActionEvent event) {
        viewEmployeePane.setVisible(true);
        addEmployeePane.setVisible(false);
    }

    @FXML
    void delete(ActionEvent event) {
        try {
            EmployeeInfo empDet = employeeInfo.getSelectionModel().getSelectedItem();
            String sql = "DELETE FROM EmployeeInformation WHERE fullname = '" + empDet.getName() + "' and emailID = '" + empDet.getEmailID() + "'";
            if (Database.insertData(sql) > 0) {
                employeeInfo.getItems().clear();
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
    void refresh(ActionEvent event) {
        where = "";
        employeeInfo.getItems().clear();
        list.removeAll(list);
        loadtabledata();
    }

    @FXML
    void update(ActionEvent event) {
        Notifications notify = Notifications.create()
                .darkStyle()
                .graphic(new ImageView(lolImg))
                .title("SORRY")
                .text("Feature will be Available Soon!")
                .position(Pos.BOTTOM_RIGHT)
                .hideAfter(Duration.seconds(5));
        notify.show();
    }

    @FXML
    void search(ActionEvent event) {

        if (searchField.isVisible()) {
            where = " WHERE fullname LIKE '" + searchField.getText() + "%' OR fullname LIKE '%" + searchField.getText() + "'";
            employeeInfo.getItems().clear();
            list.removeAll(list);
            String sql = "SELECT * FROM EmployeeInformation" + where;
            ResultSet rs = Database.executeQuery(sql);
            try {
                while (rs.next()) {
                    String name1 = rs.getString("fullname");
                    String dob1 = rs.getString("DOB");
                    String gender1 = rs.getString("gender");
                    String phone1 = rs.getString("phone");
                    String email1 = rs.getString("emailID");
                    String qualify1 = rs.getString("qualifications");
                    String skills1 = rs.getString("iTSkills");
                    String marital1 = rs.getString("maritalStatus");
                    String account1 = rs.getString("account");
                    list.add(new EmployeeInfo(name1, dob1, gender1, phone1, email1, qualify1, skills1, marital1, account1));
                }
                rs.close();
            } catch (SQLException ex) {

            }
            employeeInfo.getItems().setAll(list);
        } else if (searchField2.isVisible()) {
            where = " WHERE gender = '" + searchField2.getValue().toString() + "'";
            employeeInfo.getItems().clear();
            list.removeAll(list);
            String sql = "SELECT * FROM EmployeeInformation" + where;
            ResultSet rs = Database.executeQuery(sql);
            try {
                while (rs.next()) {
                    String name1 = rs.getString("fullname");
                    String dob1 = rs.getString("DOB");
                    String gender1 = rs.getString("gender");
                    String phone1 = rs.getString("phone");
                    String email1 = rs.getString("emailID");
                    String qualify1 = rs.getString("qualifications");
                    String skills1 = rs.getString("iTSkills");
                    String marital1 = rs.getString("maritalStatus");
                    String account1 = rs.getString("account");
                    list.add(new EmployeeInfo(name1, dob1, gender1, phone1, email1, qualify1, skills1, marital1, account1));
                }
                rs.close();
            } catch (SQLException ex) {

            }
            employeeInfo.getItems().setAll(list);
        } else if (searchField3.isVisible()) {
            where = " WHERE qualifications = '" + searchField3.getValue().toString() + "'";
            employeeInfo.getItems().clear();
            list.removeAll(list);
            String sql = "SELECT * FROM EmployeeInformation" + where;
            ResultSet rs = Database.executeQuery(sql);
            try {
                while (rs.next()) {
                    String name1 = rs.getString("fullname");
                    String dob1 = rs.getString("DOB");
                    String gender1 = rs.getString("gender");
                    String phone1 = rs.getString("phone");
                    String email1 = rs.getString("emailID");
                    String qualify1 = rs.getString("qualifications");
                    String skills1 = rs.getString("iTSkills");
                    String marital1 = rs.getString("maritalStatus");
                    String account1 = rs.getString("account");
                    list.add(new EmployeeInfo(name1, dob1, gender1, phone1, email1, qualify1, skills1, marital1, account1));
                }
                rs.close();
            } catch (SQLException ex) {

            }
            employeeInfo.getItems().setAll(list);
        }

    }

    String genderSelect;

    @FXML
    void submitDetails(ActionEvent event) {

        if (gender.getSelectedToggle() == male) {
            genderSelect = "Male";
        } else if (gender.getSelectedToggle() == female) {
            genderSelect = "Female";
        } else {
            genderSelect = null;
        }

        if (javaDevOps.isSelected()) {
            selecteditems += "Java DevOps ";
        }
        if (python.isSelected()) {
            selecteditems += " Python ";
        }
        if (network.isSelected()) {
            selecteditems += " Networking ";
        }
        if (web.isSelected()) {
            selecteditems += " Web Development ";
        }
        if (advInt.isSelected()) {
            selecteditems += " Advanced Intelligence";
        }
        if (bigData.isSelected()) {
            selecteditems += "Big Data";
        }
        if (dataBase.isSelected()) {
            selecteditems += " Database Management ";
        }
        if (others.isSelected()) {
            selecteditems += skills_others.getText();
        }

        if (validationCheck()) {
            String sql = "INSERT INTO EmployeeInformation VALUES('"
                    + surname.getText() + " " + firstname.getText() + "','"
                    + DOB.getValue().toString() + "','"
                    + genderSelect + "','"
                    + phone.getText() + "','"
                    + email.getText() + "','"
                    + qualify.getValue().toString() + "','"
                    + selecteditems + "','"
                    + marital.getValue().toString() + "','"
                    + account.getText() + "')";
            int val = Database.insertData(sql);
            if (val != 0) {
                selecteditems = "";
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
                    .graphic(new ImageView(errorImg))
                    .title("ERROR")
                    .text("Insert Data Correctly!")
                    .position(Pos.BOTTOM_RIGHT)
                    .hideAfter(Duration.seconds(5));
            notify.show();
        }
    }

    public void popuplateComboBoxes() {
        ObservableList qual = FXCollections.observableArrayList(
                "A+ Level", "O+ Level", "OND/HND", "NCE", "BSc", "PHD", "Others (Specify Below)");
        qualify.setItems(qual);
        searchField3.setItems(qual);
        ObservableList marital1 = FXCollections.observableArrayList(
                "Married", "Single", "Engaged", "Widowed");
        marital.setItems(marital1);
        ObservableList banksNa = FXCollections.observableArrayList(
                "Diamond Bank", "Zenith Bank", "Access Bank", "UBA", "GTBank", "Skye Bank", "Heritage Bank");
        banks.setItems(banksNa);
        ObservableList searchOptions = FXCollections.observableArrayList(
                "Name", "Gender", "Qualifications");
        search_Opt.setItems(searchOptions);
        ObservableList genderOptions = FXCollections.observableArrayList(
                "Male", "Female");
        searchField2.setItems(genderOptions);
    }

    @FXML
    public void handleQualifications() {
        if (qualify.getValue().equals("Others (Specify Below)")) {
            qualify_others.setDisable(false);
        } else {
            qualify_others.setDisable(true);
        }
    }

    @FXML
    public void handleSkills() {
        if (others.isSelected()) {
            skills_others.setDisable(false);
        } else {
            skills_others.setDisable(true);
        }
    }

    @FXML
    public void handleSearch(ActionEvent event) {
        if (search_Opt.getValue().equals("Name")) {
            searchField3.setVisible(false);
            searchField.setDisable(false);
            searchField2.setVisible(false);
            searchField.setVisible(true);
            searchField.setPromptText("Name");
        } else if (search_Opt.getValue().equals("Gender")) {
            searchField.setVisible(false);
            searchField3.setVisible(false);
            searchField2.setVisible(true);
        } else if (search_Opt.getValue().equals("Qualifications")) {
            searchField.setVisible(false);
            searchField2.setVisible(false);
            searchField3.setVisible(true);
        }
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
        validation5.registerValidator(email, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation6 = new ValidationSupport();
        validation6.registerValidator(email, Validator.createRegexValidator("Provide Correct Email", EMAIL_PATTERN, Severity.ERROR));
        ValidationSupport validation8 = new ValidationSupport();
        validation8.registerValidator(address, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation9 = new ValidationSupport();
        validation9.registerValidator(phone, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation10 = new ValidationSupport();
        validation10.registerValidator(phone, Validator.createRegexValidator("Provide Correct Phone Number", PHONE, Severity.ERROR));
        ValidationSupport validation11 = new ValidationSupport();
        validation11.registerValidator(DOB, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation12 = new ValidationSupport();
        validation12.registerValidator(qualify, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation14 = new ValidationSupport();
        validation14.registerValidator(marital, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation15 = new ValidationSupport();
        validation15.registerValidator(account, Validator.createEmptyValidator("Input Required", Severity.WARNING));
    }

    private boolean validationCheck() {
        if ("".equals(surname.getText())) {
            return false;
        }

        if ("".equals(firstname.getText())) {
            return false;
        }

        if ("".equals(email.getText())) {
            return false;
        }

        if ("".equals(address.getText())) {
            return false;
        }

        if ("".equals(phone.getText())) {
            return false;
        }

        if ("".equals(DOB.getValue().toString())) {
            return false;
        }

        if ("".equals(qualify.getValue().toString())) {
            return false;
        }
//
        if ("".equals(selecteditems)) {
            return false;
        }

        if ("".equals(marital.getValue().toString())) {
            return false;
        }

        if ("".equals(account.getText())) {
            return false;
        }
        if (!Pattern.matches(EMAIL_PATTERN, email.getText())) {
            return false;
        }
        if (!Pattern.matches(PHONE, phone.getText())) {
            return false;
        }

        if (!Pattern.matches(NAME, firstname.getText())) {
            return false;
        }

        if (!Pattern.matches(NAME, surname.getText())) {
            return false;
        }
        return true;

    }

    public void initCols() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailID"));
        qualifyCol.setCellValueFactory(new PropertyValueFactory<>("qualifications"));
        skillsCol.setCellValueFactory(new PropertyValueFactory<>("iTSkills"));
        maritalCol.setCellValueFactory(new PropertyValueFactory<>("maritalStatus"));
        bankCol.setCellValueFactory(new PropertyValueFactory<>("bankDetails"));

    }

    public void loadtabledata() {
        String sql = "SELECT * FROM EmployeeInformation" + where;
        String sql2 = "SELECT count(gender) FROM EmployeeInformation";
        String sql3 = "SELECT count(gender) FROM EmployeeInformation where gender = 'Male'";
        String sql4 = "SELECT count(gender) FROM EmployeeInformation where gender = 'Female'";
        ResultSet rs = Database.executeQuery(sql);
        ResultSet rs1 = Database.executeQuery(sql2);
        ResultSet rs2 = Database.executeQuery(sql3);
        ResultSet rs3 = Database.executeQuery(sql4);
        try {
            while (rs.next()) {
                String name1 = rs.getString("fullname");
                String dob1 = rs.getString("DOB");
                String gender1 = rs.getString("gender");
                String phone1 = rs.getString("phone");
                String email1 = rs.getString("emailID");
                String qualify1 = rs.getString("qualifications");
                String skills1 = rs.getString("iTSkills");
                String marital1 = rs.getString("maritalStatus");
                String account1 = rs.getString("account");
                list.add(new EmployeeInfo(name1, dob1, gender1, phone1, email1, qualify1, skills1, marital1, account1));
            }
            while (rs1.next()) {
                String num = rs1.getString("count(gender)");
                if (num.length() == 1) {
                    employeeLbl.setText("00" + num);
                } else if (num.length() == 2) {
                    employeeLbl.setText("0" + num);
                } else if (num.length() == 3) {
                    employeeLbl.setText(num);
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
        employeeInfo.getItems().setAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validators();
        popuplateComboBoxes();
        initCols();
        loadtabledata();
    }

    public static class EmployeeInfo {

        private final SimpleStringProperty name;
        private final SimpleStringProperty dateOfBirth;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty phone;
        private final SimpleStringProperty emailID;
        private final SimpleStringProperty qualifications;
        private final SimpleStringProperty iTSkills;
        private final SimpleStringProperty maritalStatus;
        private final SimpleStringProperty bankDetails;

        public EmployeeInfo(String name, String dateOfBirth, String gender, String phone, String emailID, String qualifications, String iTSkills, String maritalStatus, String bankDetails) {
            this.name = new SimpleStringProperty(name);
            this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
            this.gender = new SimpleStringProperty(gender);
            this.phone = new SimpleStringProperty(phone);
            this.emailID = new SimpleStringProperty(emailID);
            this.qualifications = new SimpleStringProperty(qualifications);
            this.iTSkills = new SimpleStringProperty(iTSkills);
            this.maritalStatus = new SimpleStringProperty(maritalStatus);
            this.bankDetails = new SimpleStringProperty(bankDetails);
        }

        public String getName() {
            return name.get();
        }

        public String getDateOfBirth() {
            return dateOfBirth.get();
        }

        public String getGender() {
            return gender.get();
        }

        public String getPhone() {
            return phone.get();
        }

        public String getEmailID() {
            return emailID.get();
        }

        public String getQualifications() {
            return qualifications.get();
        }

        public String getITSkills() {
            return iTSkills.get();
        }

        public String getMaritalStatus() {
            return maritalStatus.get();
        }

        public String getBankDetails() {
            return bankDetails.get();
        }
    }

}
