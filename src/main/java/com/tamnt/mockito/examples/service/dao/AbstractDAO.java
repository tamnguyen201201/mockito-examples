package com.tamnt.mockito.examples.service.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


@Repository
public class AbstractDAO {

    @Autowired
    protected SessionFactory sessionFactory;

    public <T> Serializable create(final T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    public <T> T update(final T entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    public <T> T merge(final T entity) {
        sessionFactory.getCurrentSession().merge(entity);
        return entity;
    }

    public <T> void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public <T> void delete(Serializable id, Class<T> entityClass) {
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery(" FROM " + entityClass.getName()).list();
    }

    @SuppressWarnings("rawtypes")
    public <T> List fetchAll(String query) {
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }

    public <T> Serializable upsert(final T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return true;
    }

}
