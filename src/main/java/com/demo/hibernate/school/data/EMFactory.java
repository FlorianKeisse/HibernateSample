package com.demo.hibernate.school.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EMFactory {
    private static EntityManagerFactory emf;

    private EMFactory() {
    }


    public static EntityManagerFactory getEMF() {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("florianDB");
        return emf;
    }

}
