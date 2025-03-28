module com.example.knk_2025 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.knk_2025 to javafx.fxml;
    exports com.example.knk_2025;
}