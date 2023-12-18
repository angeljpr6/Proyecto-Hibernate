package com.example.hibernate.controlador;

import com.example.hibernate.modelo.Usuario;
import com.example.hibernate.modelo.UsuarioManager;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    public void crearCuenta(MouseEvent mouseEvent) {
        String primeraCont = contCrear.getText();
        String segundaCont = cont2Crear.getText();
        if (primeraCont.equals(segundaCont)){
            Usuario usuario=new Usuario(nomCrear.getText(),primeraCont);
            UsuarioManager.insertUsuario(usuario);
        }
    }
}
