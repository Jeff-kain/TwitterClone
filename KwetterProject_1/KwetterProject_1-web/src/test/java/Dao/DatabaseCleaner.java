/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Domain.Heart;
import Domain.Kweet;
import Domain.User;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

/**
 *
 * @author jeffrey
 */
public class DatabaseCleaner {
    private static final Class<?>[] ENTITY_TYPES = {
        Kweet.class,
        User.class
    };
    
    private final EntityManager em;

    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    public void clean() {
        em.getTransaction().begin();

        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        em.getTransaction().commit();
    }

    private void deleteEntities(Class<?> entityType) {
        em.createQuery("delete from " + getEntityName(entityType)).executeUpdate();
    }

    protected String getEntityName(Class<?> model) {
        EntityType et = em.getMetamodel().entity(model);
        return et.getName();
    }
}