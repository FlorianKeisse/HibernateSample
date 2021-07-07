package com.demo.hibernate.school.dao;



import com.demo.hibernate.school.data.EMFactory;
import com.demo.hibernate.school.model.Module;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class ModuleDAOimp implements ModuleDAO {

    private static ModuleDAO moduleDAO;
    private EntityManagerFactory emf;

    public ModuleDAOimp() {
        emf = EMFactory.getEMF();
    }

    public static ModuleDAO getInstance() {
        if (moduleDAO == null)
            moduleDAO = new ModuleDAOimp();

        return moduleDAO;
    }

    @Override
    public void addModule(Module module) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(module);
        em.getTransaction().commit();
    }

    @Override
    public Module getModuleById(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Module.class, id);
    }

    @Override
    public Set<Module> getAllModules() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("FROM Module m");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void updateModule(Module module) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(module);
        em.getTransaction().commit();
    }

    @Override
    public void deleteModule(Module module) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Module.class, module.getId()));
        em.getTransaction().commit();
    }
}
