package com.example.hibernate.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AmistadManager {
    public static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("Persistencia");

    public static boolean insertarAmistad(Usuario u1,Usuario u2){
        try {
            Amistad amistad = new Amistad(u1,u2);
            EntityManager manager = managerFactory.createEntityManager();

            manager.getTransaction().begin();
            manager.persist(amistad);
            manager.getTransaction().commit();

            manager.close();
        }catch (java.lang.RuntimeException e){
            return false;
        }return true;
    }

    public static Amistad buscarAmistad(Usuario u1,Usuario u2) {
        EntityManager manager = managerFactory.createEntityManager();

        String jpql = "SELECT a FROM Amistad a " +
                "WHERE (:u1 = a.usuario1 AND :u2 = a.usuario2) OR (:u1 = a.usuario2 AND :u2 = a.usuario1)";
        TypedQuery<Amistad> query = manager.createQuery(jpql, Amistad.class);
        query.setParameter("u1", u1);
        query.setParameter("u2", u2);

        Amistad amistad = null;
        try {
            amistad = query.getSingleResult();
        } catch (Exception ex) {
            // Manejo de excepciones o errores si no se encuentra el usuario
            ex.printStackTrace();
        } finally {
            manager.close();
        }

        return amistad;
    }

    public static void eliminarAmistad(Usuario u1,Usuario u2){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Amistad amistad = buscarAmistad(u1,u2);
        if (amistad != null) {

            amistad = manager.merge(amistad);
            manager.remove(amistad);
        }
        manager.getTransaction().commit();
        manager.close();
    }

}
