package com.example.hibernate.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {

    public static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("Persistencia");

    public static boolean insertarUsuario(Usuario usuario){
        try {

            EntityManager manager = managerFactory.createEntityManager();

            manager.getTransaction().begin();
            manager.persist(usuario);
            manager.getTransaction().commit();

            manager.close();
        }catch (java.lang.RuntimeException e){
            return false;
        }return true;
    }

    public static boolean cambiarNombre(int id, String nuevoNombre){
        try {
            EntityManager manager = managerFactory.createEntityManager();

            manager.getTransaction().begin();
            Usuario usuario = manager.find(Usuario.class, id);
            usuario.setNombre(nuevoNombre);
            manager.getTransaction().commit();
            manager.close();
        }catch (java.lang.RuntimeException e){
            return false;
        }return true;
    }
    public static void cambiarContrasena(int id, String nuevaCont){

        EntityManager manager = managerFactory.createEntityManager();

        manager.getTransaction().begin();
        Usuario usuario = manager.find(Usuario.class, id);
        usuario.setContrasena(nuevaCont);
        manager.getTransaction().commit();
        manager.close();

    }

    /**
     * Segun un id busca todos los usuarios amigos del proietario del id
     * @param idUsuario
     * @return
     */
    public static List<Usuario> obtenerAmigos(int idUsuario){
        EntityManager manager = managerFactory.createEntityManager();
        List<Usuario> amigos = new ArrayList<>();

        manager.getTransaction().begin();
        Usuario usuario = manager.find(Usuario.class, idUsuario);
        manager.getTransaction().commit();

        if (usuario != null) {
            String jpql = "SELECT u FROM Usuario u " +
                    "JOIN Amistad a ON (u.id = a.usuario1.id OR u.id = a.usuario2.id) " +
                    "WHERE :idUsuario IN (a.usuario1.id, a.usuario2.id) " +
                    "AND u.id != :idUsuario";

            TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
            query.setParameter("idUsuario", idUsuario);

            amigos = query.getResultList();
        }

        manager.close();
        return amigos;
    }
    public static Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        EntityManager manager = managerFactory.createEntityManager();

        String jpql = "SELECT u FROM Usuario u WHERE u.nombre = :nombreUsuario";
        TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
        query.setParameter("nombreUsuario", nombreUsuario);

        Usuario usuarioEncontrado = null;
        try {
            usuarioEncontrado = query.getSingleResult();
        } catch (Exception ex) {
            // Manejo de excepciones o errores si no se encuentra el usuario
            ex.printStackTrace();
        } finally {
            manager.close();
        }

        return usuarioEncontrado;
    }

    public static Usuario getUsuario(int id){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Usuario usuario = manager.find(Usuario.class, id);
        manager.getTransaction().commit();
        manager.close();
        return usuario;
    }

    public static void eliminarUsuario(Usuario usuario){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Usuario usuario1 = manager.find(Usuario.class, usuario.getId());
        manager.remove(usuario1);
        manager.getTransaction().commit();
        manager.close();
    }

}
