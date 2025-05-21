package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Dto.CreatePerdoruesitDto;
import Services.PerdoruesitService;
import models.Perdoruesit;


import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button signUpButton;

    @FXML
    private Button loginredirect;

    @FXML
    private AnchorPane mainForm;
    @FXML
    private Button close;

    private final PerdoruesitService userService = new PerdoruesitService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Optional: Add listeners or logic here
    }
    @FXML
    private void handleSignUp(ActionEvent event) {
        String usernameInput = username.getText().trim();
        String passwordInput = password.getText().trim();
        String confirmPasswordInput = confirmPassword.getText().trim();

        if (usernameInput.isEmpty() || passwordInput.isEmpty() || confirmPasswordInput.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Please fill all fields.");
            return;
        }

        if (!passwordInput.equals(confirmPasswordInput)) {
            showAlert(Alert.AlertType.ERROR, "Passwords do not match.");
            return;
        }

        try {
            PerdoruesitService perdoruesitService = new PerdoruesitService();

            if (perdoruesitService.existsByUsername(usernameInput)) {
                showAlert(Alert.AlertType.ERROR, "This username already exists.");
                return;
            }

            // Ruaj fjalëkalimin plaintext (siç e kërkove)
            CreatePerdoruesitDto dto = new CreatePerdoruesitDto(usernameInput, passwordInput, "user");

            perdoruesitService.createUser(dto); // metoda nuk është static

            showAlert(Alert.AlertType.INFORMATION, "Successfully registered!");
            handleLoginRedirect(null);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Registration failed: " + e.getMessage());
        }
    }
    @FXML
    public void close() {
        System.exit(0);
    }


    @FXML
    private void handleLoginRedirect(ActionEvent event) {
        try {
            loginredirect.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/knk_2025/Login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(type == Alert.AlertType.ERROR ? "Error Message" : "Information Message");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
