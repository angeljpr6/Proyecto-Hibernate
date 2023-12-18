module com.example.hibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;


    opens com.example.hibernate to javafx.fxml;

    exports com.example.hibernate;
    exports com.example.hibernate.controlador;
    opens com.example.hibernate.controlador to javafx.fxml;
    opens com.example.hibernate.modelo to java.persistence, java.sql,org.hibernate.orm.core;
    exports com.example.hibernate.modelo;
}