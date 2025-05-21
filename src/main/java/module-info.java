module com.example.knk_2025 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.knk_2025 to javafx.fxml;
    opens Controllers to javafx.fxml;


    opens Services;
    exports Services;
    opens Repository;
    exports Repository;
    opens models;
    exports models;

    exports com.example.knk_2025;
    exports Controllers;
}