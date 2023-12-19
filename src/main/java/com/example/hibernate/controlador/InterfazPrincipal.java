package com.example.hibernate.controlador;

import com.example.hibernate.modelo.AmistadManager;
import com.example.hibernate.modelo.Usuario;
import com.example.hibernate.modelo.UsuarioManager;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InterfazPrincipal implements Initializable {

    public static Usuario usuario=null;

    public Label nombUsuario;

    /* Cambiar el nombre del usuario */
    public TextField nuevoNombre;
    public PasswordField contNuevoNombre;
    public Label nomUsuarioExistente;
    public Label contIncorrectaNuevNom;
    public Label exitoCambiarNom;

    /* Cambiar la contraseña */
    public PasswordField nuevaCont;
    public PasswordField antCont;
    public Label exitoCambiarCont;

    /* Eliminar Cuenta */
    public PasswordField contEliminarCuenta;
    public Pane confEliminarCuentPane;

    /* Opciones */
    public Pane cambiarNombrePane;
    public Pane cambiarContPane;
    public Pane eliminarCuentaPane;
    public Label cambiarNombreLabel;
    public Label cambiarContLabel;
    public Label eliminarCuentLabel;
    public ArrayList<Label> labelSeleccionado=new ArrayList<>();

    /* Panel de amigos */
    public Pane amigosPane;

    public TextField nombreAmigoAnadir;
    public TextField nombreAmigoElimin;
    public ListView listaAmigos;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelSeleccionado.add(cambiarNombreLabel);
        inicializarNombreUsuario();
    }

    public void inicializarNombreUsuario(){
        nombUsuario.setText(usuario.getNombre());
    }

    public boolean validarContra(String contra){
        String contUsuario=usuario.getContrasena();
        if (contra.equals(contUsuario)){
            return true;
        }else return false;
    }

    public void cambiarNombre(MouseEvent mouseEvent) {
        nomUsuarioExistente.setVisible(false);
        contIncorrectaNuevNom.setVisible(false);

        if (validarContra(contNuevoNombre.getText())){
            if (UsuarioManager.cambiarNombre(usuario.getId(), nuevoNombre.getText())) {
                usuario.setNombre(nuevoNombre.getText());
                inicializarNombreUsuario();
                exitoCambiarNom.setVisible(true);

            }else nomUsuarioExistente.setVisible(true);
        }else contIncorrectaNuevNom.setVisible(true);
    }

    public void cambiarContrasena(MouseEvent mouseEvent) {
        
        if (validarContra(antCont.getText())){
            UsuarioManager.cambiarContrasena(usuario.getId(), nuevaCont.getText());
            exitoCambiarCont.setVisible(true);
        }// TODO: 18/12/2023 Mostrar el error contraseña incorrecta 
    }

    public void eliminarCuent(MouseEvent mouseEvent) {
        confEliminarCuentPane.setVisible(true);
    }

    public void confEliminarCuenta(MouseEvent mouseEvent) throws IOException {
        UsuarioManager.eliminarUsuario(usuario);
        abrirIncio();

    }
    public void abrirIncio() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hibernate/inicio-sesion.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage inicioSesionStage= new Stage();
        inicioSesionStage.setTitle("Inicio Artista");
        inicioSesionStage.setResizable(false);
        inicioSesionStage.setScene(scene);
        inicioSesionStage.show();
        Stage myStage = (Stage) this.nombUsuario.getScene().getWindow();
        myStage.close();
    }

    public void cambiarSeleccionLabel(Label seleccionado,Label deseleccionado){
        deseleccionado.setStyle(seleccionado.getStyle()+"-fx-text-fill: gray;");
        seleccionado.setStyle(seleccionado.getStyle()+"-fx-text-fill: black;");
    }

    public void abrirCambioNombre(MouseEvent mouseEvent) {
        cambiarSeleccionLabel(cambiarNombreLabel,labelSeleccionado.get(0));
        labelSeleccionado.set(0,cambiarNombreLabel);

        cambiarNombrePane.setVisible(true);

        cambiarContPane.setVisible(false);
        eliminarCuentaPane.setVisible(false);
    }

    public void abrirCambioContra(MouseEvent mouseEvent) {
        cambiarSeleccionLabel(cambiarContLabel,labelSeleccionado.get(0));
        labelSeleccionado.set(0,cambiarContLabel);

        cambiarContPane.setVisible(true);

        eliminarCuentaPane.setVisible(false);
        cambiarNombrePane.setVisible(false);
    }

    public void abrirEliminarCuenta(MouseEvent mouseEvent) {
        cambiarSeleccionLabel(eliminarCuentLabel,labelSeleccionado.get(0));
        labelSeleccionado.set(0,eliminarCuentLabel);

        eliminarCuentaPane.setVisible(true);

        cambiarNombrePane.setVisible(false);
        cambiarContPane.setVisible(false);
    }

    public void abrirAmigos(MouseEvent mouseEvent) {
        amigosPane.setVisible(true);
        rellenarListaAmigos();
    }



    public void cerrarAmigos(MouseEvent mouseEvent) {
        amigosPane.setVisible(false);
    }

    public void rellenarListaAmigos(){
        listaAmigos.getItems().clear();
        List<Usuario> usuarios=UsuarioManager.obtenerAmigos(usuario.getId());
        for (Usuario u :
                usuarios) {
            listaAmigos.getItems().add(u.getNombre());
        }
    }

    public void anadirAmigo(MouseEvent mouseEvent) {
        String nombreAmigo=nombreAmigoAnadir.getText();
        List<Usuario> amigos = UsuarioManager.obtenerAmigos(usuario.getId());
        boolean existeAmigo=false;

        for (Usuario u :
                amigos) {
            if (nombreAmigo.equals(u.getNombre())){
                existeAmigo=true;
            }
        }

        if (!existeAmigo){
            Usuario amigo = UsuarioManager.buscarUsuarioPorNombre(nombreAmigo);
            AmistadManager.insertarAmistad(usuario,amigo);
            rellenarListaAmigos();
        }// TODO: 19/12/2023 mostrar error ya esta el amigo
    }

    public void eliminarAmigo(MouseEvent mouseEvent) {
        String nombreAmigo=nombreAmigoElimin.getText();
        List<Usuario> amigos = UsuarioManager.obtenerAmigos(usuario.getId());
        boolean existeAmigo=false;

        for (Usuario u :
                amigos) {
            if (nombreAmigo.equals(u.getNombre())){
                existeAmigo=true;
            }
        }

        if (existeAmigo){
            Usuario amigo = UsuarioManager.buscarUsuarioPorNombre(nombreAmigo);
            AmistadManager.eliminarAmistad(usuario, amigo);
            rellenarListaAmigos();
        }// TODO: 19/12/2023 mostrar error no existe ese amigo

    }
}
