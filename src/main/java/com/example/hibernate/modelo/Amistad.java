package com.example.hibernate.modelo;

import javax.persistence.*;

@Entity
@Table(name = "amistad")
public class Amistad {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id_1")
    private Usuario usuario1;

    @ManyToOne
    @JoinColumn(name = "usuario_id_2")
    private Usuario usuario2;

    public Amistad(Usuario usuario1, Usuario usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
    }

    public Amistad(){

    }
}
