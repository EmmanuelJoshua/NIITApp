/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagementSystem;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
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
public class StudentInformationController implements Initializable {

    private static final String FULL_NAME = "([A-Z][a-zA-Z'-]+\\s){1,2}([a-zA-z'-]){2,}";
    private static final String PHONE = "\\d{11}";
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    ObservableList<studentDetails> list = FXCollections.observableArrayList();

    Image checkImg = new Image("/ManagementSystem/images/checked.png");
    Image errorImg = new Image("/ManagementSystem/images/cross.png");
    Image warnImg = new Image("/ManagementSystem/images/warning.png");
    Image lolImg = new Image("/ManagementSystem/images/glass.png");

    @FXML
    private TableView<studentDetails> studentInfo;

    @FXML
    private TableColumn<studentDetails, String> nameCol;

    @FXML
    private TableColumn<studentDetails, String> phoneCol;

    @FXML
    private TableColumn<studentDetails, String> dobCol;

    @FXML
    private TableColumn<studentDetails, String> genderCol;

    @FXML
    private TableColumn<studentDetails, String> emailCol;

    @FXML
    private TableColumn<studentDetails, String> programCol;

    @FXML
    private TableColumn<studentDetails, String> cityCol;

    @FXML
    private TableColumn<studentDetails, String> centreCol;

    @FXML
    private TableColumn<studentDetails, String> semesterCol;

    @FXML
    private TableColumn<studentDetails, String> studentIDCol;

    @FXML
    private TableColumn<studentDetails, String> regIDCol;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane exitPane;

    @FXML
    private Pane addStudentsPane;

    @FXML
    private Pane viewStudentsPane;

    @FXML
    private Text registeredLbl;

    @FXML
    private Text maleLbl;

    @FXML
    private Text femaleLbl;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField emailID;

    @FXML
    private JFXComboBox program;

    @FXML
    private JFXComboBox admissionStatus;

    @FXML
    private JFXComboBox paymentStatus;

    @FXML
    private JFXTextField amountPaid;

    @FXML
    private JFXTextField balance;

    @FXML
    private JFXTextField city;

    @FXML
    private JFXTextField centre;

    @FXML
    private JFXTextField semesterNo;

    @FXML
    private JFXTextField feePayable;

    @FXML
    private JFXTextField feeTotal;

    @FXML
    private JFXDatePicker D_O_B;

    @FXML
    private JFXTextArea address;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXComboBox search_Opt;

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXComboBox searchField2;

    @FXML
    private JFXComboBox searchField3;

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
    public void addStud_StudInfo(ActionEvent event) {
        addStudentsPane.setVisible(true);
        viewStudentsPane.setVisible(false);
    }

    @FXML
    public void viewStud_StudInfo(ActionEvent event) {
        viewStudentsPane.setVisible(true);
        addStudentsPane.setVisible(false);
    }

    String genderSelect;
    Random rand = new Random();

    @FXML
    public void submitDetails(ActionEvent event) {
        if (gender.getSelectedToggle() == male) {
            genderSelect = "Male";
        } else if (gender.getSelectedToggle() == female) {
            genderSelect = "Female";
        } else {
            genderSelect = null;
        }
//        int array[] ={Math.random()};
        String reg = "122213";
        String iD = "2343243";

        if (validationCheck()) {
            String sql1 = "INSERT INTO PaymentInformation VALUES('"
                    + name.getText() + "','"
                    + admissionStatus.getValue().toString() + "','"
                    + amountPaid.getText() + "','"
                    + paymentStatus.getValue().toString() + "','"
                    + program.getValue().toString() + "')";
            String sql2 = "INSERT INTO StudentInformation VALUES('"
                    + name.getText() + "','"
                    + phone.getText() + "','"
                    + D_O_B.getValue().toString() + "','"
                    + genderSelect + "','"
                    + emailID.getText() + "','"
                    + program.getValue().toString() + "','"
                    + city.getText() + "','"
                    + centre.getText() + "','"
                    + semesterNo.getText() + "','"
                    + iD + "','"
                    + reg + "')";
            int val1 = Database.insertData(sql1);
            int val2 = Database.insertData(sql2);
            if (val1 != 0 && val2 != 0) {
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
        studentInfo.getItems().clear();
        list.removeAll(list);
        loadtabledata();

    }

    @FXML
    public void search(ActionEvent event) {
//        where = " WHERE fullname = '" + nameSearch.getText() + "'";
//        studentInfo.getItems().clear();
//        list.removeAll(list);
//        String sql = "SELECT * FROM StudentInformation" + where;
//        ResultSet rs = Database.executeQuery(sql);
//        try {
//            while (rs.next()) {
//                String name1 = rs.getString("fullname");
//                String phone1 = rs.getString("phone");
//                String dob1 = rs.getString("DOB");
//                String gender1 = rs.getString("gender");
//                String email1 = rs.getString("emailID");
//                String course1 = rs.getString("course");
//                String city1 = rs.getString("city");
//                String centre1 = rs.getString("centre");
//                String semester1 = rs.getString("semesterNO");
//                String studentID = rs.getString("studentID");
//                String regID = rs.getString("registrationNo");
//                list.add(new studentDetails(name1, phone1, dob1, gender1, email1, course1, city1, centre1, semester1, studentID, regID));
//            }
//            rs.close();
//        } catch (SQLException ex) {
//
//        }
//        studentInfo.getItems().setAll(list);
        if (searchField.isVisible()) {
            where = " WHERE fullname LIKE '" + searchField.getText() + "%' OR fullname LIKE '%"+searchField.getText()+"'";
            studentInfo.getItems().clear();
            list.removeAll(list);
            String sql = "SELECT * FROM StudentInformation" + where;
            ResultSet rs = Database.executeQuery(sql);
            try {
                while (rs.next()) {
                    String name1 = rs.getString("fullname");
                    String phone1 = rs.getString("phone");
                    String dob1 = rs.getString("DOB");
                    String gender1 = rs.getString("gender");
                    String email1 = rs.getString("emailID");
                    String course1 = rs.getString("course");
                    String city1 = rs.getString("city");
                    String centre1 = rs.getString("centre");
                    String semester1 = rs.getString("semesterNO");
                    String studentID = rs.getString("studentID");
                    String regID = rs.getString("registrationNo");
                    list.add(new studentDetails(name1, phone1, dob1, gender1, email1, course1, city1, centre1, semester1, studentID, regID));
                }
                rs.close();
            } catch (SQLException ex) {

            }
            studentInfo.getItems().setAll(list);
        } else if (searchField2.isVisible()) {
            where = " WHERE gender = '" + searchField2.getValue().toString() + "'";
            studentInfo.getItems().clear();
            list.removeAll(list);
            String sql = "SELECT * FROM StudentInformation" + where;
            ResultSet rs = Database.executeQuery(sql);
            try {
                while (rs.next()) {
                    String name1 = rs.getString("fullname");
                    String phone1 = rs.getString("phone");
                    String dob1 = rs.getString("DOB");
                    String gender1 = rs.getString("gender");
                    String email1 = rs.getString("emailID");
                    String course1 = rs.getString("course");
                    String city1 = rs.getString("city");
                    String centre1 = rs.getString("centre");
                    String semester1 = rs.getString("semesterNO");
                    String studentID = rs.getString("studentID");
                    String regID = rs.getString("registrationNo");
                    list.add(new studentDetails(name1, phone1, dob1, gender1, email1, course1, city1, centre1, semester1, studentID, regID));
                }
                rs.close();
            } catch (SQLException ex) {

            }
            studentInfo.getItems().setAll(list);
        } else if (searchField3.isVisible()) {
            where = " WHERE course = '" + searchField3.getValue().toString() + "'";
            studentInfo.getItems().clear();
            list.removeAll(list);
            String sql = "SELECT * FROM StudentInformation" + where;
            ResultSet rs = Database.executeQuery(sql);
            try {
                while (rs.next()) {
                    String name1 = rs.getString("fullname");
                    String phone1 = rs.getString("phone");
                    String dob1 = rs.getString("DOB");
                    String gender1 = rs.getString("gender");
                    String email1 = rs.getString("emailID");
                    String course1 = rs.getString("course");
                    String city1 = rs.getString("city");
                    String centre1 = rs.getString("centre");
                    String semester1 = rs.getString("semesterNO");
                    String studentID = rs.getString("studentID");
                    String regID = rs.getString("registrationNo");
                    list.add(new studentDetails(name1, phone1, dob1, gender1, email1, course1, city1, centre1, semester1, studentID, regID));
                }
                rs.close();
            } catch (SQLException ex) {

            }
            studentInfo.getItems().setAll(list);
        }
    }

    @FXML
    public void delete(ActionEvent event) {
        try {
            studentDetails stuDet = studentInfo.getSelectionModel().getSelectedItem();
            String sql = "DELETE FROM StudentInformation WHERE fullname = '" + stuDet.getName() + "' and emailID = '" + stuDet.getEmailID() + "'";
            if (Database.insertData(sql) > 0) {
                studentInfo.getItems().clear();
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
                        .title("FAILED")
                        .text("Deletion Failed")
                        .position(Pos.BOTTOM_RIGHT)
                        .hideAfter(Duration.seconds(5));
                notify.show();
            }
        } catch (Exception e) {
            Notifications notify = Notifications.create()
                    .darkStyle()
                    .graphic(new ImageView(warnImg))
                    .title("WARNING")
                    .text("No Selection Made")
                    .position(Pos.BOTTOM_RIGHT)
                    .hideAfter(Duration.seconds(5));
            notify.show();
        }
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
        } else if (search_Opt.getValue().equals("Course")) {
            searchField.setVisible(false);
            searchField2.setVisible(false);
            searchField3.setVisible(true);
        }
    }

    @FXML
    public void handleProgram(ActionEvent event) {
        if (program.getValue().equals("Tally")) {
            semesterNo.setText("6 Months");
            feeTotal.setText("₦126,000");
        } else if (program.getValue().equals("MMS - SW")) {
            semesterNo.setText("3 Years");
            feeTotal.setText("₦189,750");
        } else if (program.getValue().equals("MMS - IM")) {
            semesterNo.setText("3 Years");
            feeTotal.setText("₦189,750");
        } else if (program.getValue().equals("Web Development")) {
            semesterNo.setText("8 Months");
            feeTotal.setText("₦241,500");
        } else if (program.getValue().equals("Web Design")) {
            semesterNo.setText("6 Months");
            feeTotal.setText("₦155,250");
        } else if (program.getValue().equals("Java")) {
            semesterNo.setText("7 Months");
            feeTotal.setText("₦244,950");
        } else if (program.getValue().equals("DTP")) {
            semesterNo.setText("3 Months");
            feeTotal.setText("₦45,000");
        } else if (program.getValue().equals("MIS")) {
            semesterNo.setText("6 Months");
            feeTotal.setText("₦151,800");
        } else if (program.getValue().equals("Hardware & Networking")) {
            semesterNo.setText("6 Months");
            feeTotal.setText("₦186,300");
        } else if (program.getValue().equals("Comptia A+")) {
            semesterNo.setText("7 Months");
            feeTotal.setText("₦81,650");
        } else if (program.getValue().equals("Comptia N+")) {
            semesterNo.setText("7 Months");
            feeTotal.setText("₦81,650");
        } else if (program.getValue().equals("Security+")) {
            semesterNo.setText("7 Months");
            feeTotal.setText("₦79,925");
        } else if (program.getValue().equals("Digital Marketing")) {
            semesterNo.setText("4 Months");
            feeTotal.setText("₦117,600");
        } else if (program.getValue().equals("Big Data")) {
            semesterNo.setText("5 Months");
            feeTotal.setText("₦126,000");
        } else if (program.getValue().equals("Data Analytics")) {
            semesterNo.setText("6 Months");
            feeTotal.setText("₦210,600");
        } else if (program.getValue().equals("Project Management")) {
            semesterNo.setText("3 Months");
            feeTotal.setText("₦81,650");
        } else if (program.getValue().equals("Python")) {
            semesterNo.setText("8 Months");
            feeTotal.setText("₦80,000");
        } else if (program.getValue().equals(".Net")) {
            semesterNo.setText("6 Months");
            feeTotal.setText("₦244,950");
        } else if (program.getValue().equals("Linux & Oracle")) {
            semesterNo.setText("5 Months");
            feeTotal.setText("₦310,500");
        } else if (program.getValue().equals("Mobile App Development")) {
            semesterNo.setText("6 Months");
            feeTotal.setText("₦151,800");
        } else if (program.getValue().equals("ITIL")) {
            semesterNo.setText("4 Months");
            feeTotal.setText("₦55,200");
        } else if (program.getValue().equals("Enterprise Java with DevOps")) {
            semesterNo.setText("3 Months");
            feeTotal.setText("₦55,000");
        }
    }

    public void popuplateComboBoxes() {
        ObservableList courses = FXCollections.observableArrayList(
                "Tally",
                "MMS - SW",
                "MMS - IM",
                "Web Development",
                "Web Design",
                "Java",
                "DTP",
                "MIS",
                "Hardware & Networking",
                "Comptia A+",
                "Comptia N+",
                "Security+",
                "Digital Marketing",
                "Big Data",
                "Data Analytics",
                "Project Management",
                "Python",
                ".Net",
                "Linux & Oracle",
                "Mobile App Development",
                "ITIL",
                "Enterprise Java with DevOps");
        program.setItems(courses);
        searchField3.setItems(courses);
        ObservableList admission = FXCollections.observableArrayList("Admitted", "Admission Pending", "Not Admitted");
        admissionStatus.setItems(admission);
        ObservableList payment = FXCollections.observableArrayList("Fully Paid", "Pending Payment");
        paymentStatus.setItems(payment);
        ObservableList searchOptions = FXCollections.observableArrayList(
                "Name", "Gender", "Course");
        search_Opt.setItems(searchOptions);
        ObservableList genderOptions = FXCollections.observableArrayList(
                "Male", "Female");
        searchField2.setItems(genderOptions);
    }

    public void validators() {
        ValidationSupport validation1 = new ValidationSupport();
        validation1.registerValidator(name, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation2 = new ValidationSupport();
        validation2.registerValidator(name, Validator.createRegexValidator("Provide Correct First Name", FULL_NAME, Severity.ERROR));
        ValidationSupport validation3 = new ValidationSupport();
        validation3.registerValidator(emailID, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation4 = new ValidationSupport();
        validation4.registerValidator(emailID, Validator.createRegexValidator("Provide Correct Email", EMAIL_PATTERN, Severity.ERROR));
        ValidationSupport validation5 = new ValidationSupport();
        validation5.registerValidator(address, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation6 = new ValidationSupport();
        validation6.registerValidator(phone, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation7 = new ValidationSupport();
        validation7.registerValidator(phone, Validator.createRegexValidator("Provide Correct Phone Number", PHONE, Severity.ERROR));
        ValidationSupport validation8 = new ValidationSupport();
        validation8.registerValidator(D_O_B, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation9 = new ValidationSupport();
        validation9.registerValidator(city, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation10 = new ValidationSupport();
        validation10.registerValidator(centre, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation11 = new ValidationSupport();
        validation11.registerValidator(semesterNo, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation12 = new ValidationSupport();
        validation12.registerValidator(program, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation13 = new ValidationSupport();
        validation13.registerValidator(amountPaid, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation14 = new ValidationSupport();
        validation14.registerValidator(admissionStatus, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation15 = new ValidationSupport();
        validation15.registerValidator(paymentStatus, Validator.createEmptyValidator("Input Required", Severity.WARNING));
    }

    public boolean validationCheck() {
        if ("".equals(name.getText())) {
            return false;
        }

        if ("".equals(phone.getText())) {
            return false;
        }

        if ("".equals(D_O_B.getValue().toString())) {
            return false;
        }

        if ("".equals(program.getValue().toString())) {
            return false;
        }

        if ("".equals(paymentStatus.getValue().toString())) {
            return false;
        }

        if ("".equals(admissionStatus.getValue().toString())) {
            return false;
        }

        if ("".equals(city.getText())) {
            return false;
        }

        if ("".equals(centre.getText())) {
            return false;
        }

        if ("".equals(emailID.getText())) {
            return false;
        }

        if ("".equals(semesterNo.getText())) {
            return false;
        }

        if ("".equals(amountPaid.getText())) {
            return false;
        }

        if (!Pattern.matches(EMAIL_PATTERN, emailID.getText())) {
            return false;
        }

        if (!Pattern.matches(PHONE, phone.getText())) {
            return false;
        }

        if (!Pattern.matches(FULL_NAME, name.getText())) {
            return false;
        }
        return true;
    }

    public void initCols() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailID"));
        programCol.setCellValueFactory(new PropertyValueFactory<>("course"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        centreCol.setCellValueFactory(new PropertyValueFactory<>("centre"));
        semesterCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        studentIDCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        regIDCol.setCellValueFactory(new PropertyValueFactory<>("registrationID"));

    }

    public void loadtabledata() {
        String sql = "SELECT * FROM StudentInformation" + where;
        String sql2 = "SELECT count(gender) FROM StudentInformation";
        String sql3 = "SELECT count(gender) FROM StudentInformation where gender = 'Male'";
        String sql4 = "SELECT count(gender) FROM StudentInformation where gender = 'Female'";
        ResultSet rs = Database.executeQuery(sql);
        ResultSet rs1 = Database.executeQuery(sql2);
        ResultSet rs2 = Database.executeQuery(sql3);
        ResultSet rs3 = Database.executeQuery(sql4);

        try {
            while (rs.next()) {
                String name1 = rs.getString("fullname");
                String phone1 = rs.getString("phone");
                String dob1 = rs.getString("DOB");
                String gender1 = rs.getString("gender");
                String email1 = rs.getString("emailID");
                String course1 = rs.getString("course");
                String city1 = rs.getString("city");
                String centre1 = rs.getString("centre");
                String semester1 = rs.getString("semesterNO");
                String studentID = rs.getString("studentID");
                String regID = rs.getString("registrationNo");
                list.add(new studentDetails(name1, phone1, dob1, gender1, email1, course1, city1, centre1, semester1, studentID, regID));
            }
            rs.close();
            while (rs1.next()) {
                String num = rs1.getString("count(gender)");
                if (num.length() == 1) {
                    registeredLbl.setText("00" + num);
                } else if (num.length() == 2) {
                    registeredLbl.setText("0" + num);
                } else if (num.length() == 3) {
                    registeredLbl.setText(num);
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
        } catch (SQLException ex) {

        }
        studentInfo.getItems().setAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        popuplateComboBoxes();
        validators();
        initCols();
        loadtabledata();
        city.setText("Agbara");
        centre.setText("NIIT Agbara");
        feePayable.setText("₦45,000");
    }

    public static class studentDetails {

        private final SimpleStringProperty name;
        private final SimpleStringProperty phone;
        private final SimpleStringProperty DOB;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty emailID;
        private final SimpleStringProperty course;
        private final SimpleStringProperty city;
        private final SimpleStringProperty centre;
        private final SimpleStringProperty duration;
        private final SimpleStringProperty studentID;
        private final SimpleStringProperty registrationID;

        public studentDetails(String name, String phone, String DOB, String gender, String emailID, String course, String city, String centre, String duration, String studentID, String registrationID) {
            this.name = new SimpleStringProperty(name);
            this.phone = new SimpleStringProperty(phone);
            this.DOB = new SimpleStringProperty(DOB);
            this.gender = new SimpleStringProperty(gender);
            this.emailID = new SimpleStringProperty(emailID);
            this.course = new SimpleStringProperty(course);
            this.city = new SimpleStringProperty(city);
            this.centre = new SimpleStringProperty(centre);
            this.duration = new SimpleStringProperty(duration);
            this.studentID = new SimpleStringProperty(studentID);
            this.registrationID = new SimpleStringProperty(registrationID);
        }

        public String getName() {
            return name.get();
        }

        public String getPhone() {
            return phone.get();
        }

        public String getDOB() {
            return DOB.get();
        }

        public String getGender() {
            return gender.get();
        }

        public String getEmailID() {
            return emailID.get();
        }

        public String getCourse() {
            return course.get();
        }

        public String getCity() {
            return city.get();
        }

        public String getCentre() {
            return centre.get();
        }

        public String getDuration() {
            return duration.get();
        }

        public String getStudentID() {
            return studentID.get();
        }

        public String getRegistrationID() {
            return registrationID.get();
        }
    }

}
