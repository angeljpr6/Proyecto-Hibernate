package com.example.hibernate.controlador;

import com.example.hibernate.modelo.Usuario;
import com.example.hibernate.modelo.UsuarioManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesion {

    /* Atributos de Iniciar Sesion */
    public TextField nomIniciar;
    public PasswordField contIniciar;

    /* Atributos de Crear Cuenta */
    public TextField nomCrear;
    public PasswordField contCrear;
    public PasswordField cont2Crear;

    /**
     * Inicia Sesion con los datos introducidos
     * @param mouseEvent
     */
    public void iniciarSesion(MouseEvent mouseEvent) {
    }

    /**
     * Crea una cuenta con los datos introducidos
     * @param mouseEvent
     */
    public void crearCuenta(MouseEvent mouseEvent) throws IOException {
        String primeraCont = contCrear.getText();
        String segundaCont = cont2Crear.getText();
        if (primeraCont.equals(segundaCont)){
            Usuario usuario=new Usuario(nomCrear.getText(),primeraCont);
            if (UsuarioManager.insertUsuario(usuario)){

                InterfazPrincipal.usuario=usuario;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hibernate/interfaz-principal.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage intPrincipalStage = new Stage();
                intPrincipalStage.setTitle("Inicio Artista");
                intPrincipalStage.setResizable(false);
                intPrincipalStage.setScene(scene);
                intPrincipalStage.show();
                Stage myStage = (Stage) this.contIniciar.getScene().getWindow();
                myStage.close();
            }else ;// TODO: 18/12/2023 error nombreUsuario
        }
    }
}
