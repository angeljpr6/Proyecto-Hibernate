package com.example.hibernate.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioManager {

    public static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("Persistencia");

    public static boolean insertUsuario(Usuario usuario){
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

    public static boolean updateUsuario(int id, String nuevoNombre){
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

    public static Usuario getUsuario(int id){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Usuario usuario = manager.find(Usuario.class, id);
        manager.getTransaction().commit();
        manager.close();
        return usuario;
    }

    public static void deleteUsuario(Usuario usuario){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Usuario usuario1 = manager.find(Usuario.class, usuario.getId());
        manager.remove(usuario1);
        manager.getTransaction().commit();
        manager.close();
    }

}
