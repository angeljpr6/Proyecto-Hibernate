module com.example.hibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hibernate to javafx.fxml;
    exports com.example.hibernate;
}