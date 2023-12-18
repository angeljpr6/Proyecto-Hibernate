module com.example.hibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;


    opens com.example.hibernate to javafx.fxml;
    opens com.example.hibernate.modelo to unnamed;

    exports com.example.hibernate;
    exports com.example.hibernate.controlador;
    opens com.example.hibernate.controlador to javafx.fxml;
}