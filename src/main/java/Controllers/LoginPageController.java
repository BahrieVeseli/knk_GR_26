package Controllers;


import Services.AuthentificationService;
import Services.SceneManager;
import Services.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Perdoruesit;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

        @FXML
        private Button close;

        @FXML
        private Button loginbtn;

        @FXML
        private AnchorPane main_form;

        @FXML
        private PasswordField password;

        @FXML
        private TextField username;
        @FXML
        private Button signupbtn;

        private final AuthentificationService authentificationService = new AuthentificationService();

        private double x = 0;
        private double y = 0;

        @FXML
        public void loginAdmin() {
            Alert alert;

            String userInput = username.getText();
            String passwordInput = password.getText();

            if (userInput.isEmpty() || passwordInput.isEmpty()) {
                showError("Please fill all blank fields");
                return;
            }

            try {
                Perdoruesit user = authentificationService.authenticate(userInput, passwordInput);
                if (user != null) {
                    SessionManager.getInstance().setCurrentUser(user);
                    showInfo("Successfully Logged In!");

                    loginbtn.getScene().getWindow().hide();
                    openDashboard();
                } else {
                    showError("Wrong Username or Password");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showError("An unexpected error occurred: " + e.getMessage());
            }
        }

        private void openDashboard() throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/knk_2025/dashboard.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
                stage.setOpacity(0.8);
            });

            root.setOnMouseReleased((MouseEvent event) -> stage.setOpacity(1));

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }

        private void showError(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        private void showInfo(String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
        @FXML
        private void handleSignUpRedirect(ActionEvent event) {
            try {
                signupbtn.getScene().getWindow().hide();

                //hap login
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/knk_2025/signup1.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Login");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        @FXML
        public void close() {
            System.exit(0);
        }

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            // Initialization logic if needed
        }

        public void handleLoginClick(ActionEvent actionEvent) {
            loginAdmin();
        }

}
