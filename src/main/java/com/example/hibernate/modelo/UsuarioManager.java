package com.example.hibernate.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioManager {

    public static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("Persistencia");

    public static void insertUsuario(Usuario usuario){
        EntityManager manager = managerFactory.createEntityManager();

        manager.getTransaction().begin();
        manager.persist(usuario);
        manager.getTransaction().commit();

        manager.close();
    }
}
