package com.ese.model.dao;

import com.ese.utils.Utils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDAO<T, ID extends Serializable> {
    @Resource private SessionFactory sessionFactory;
    @Resource protected Logger log;
    @Resource protected Logger moLogger;
    @Resource protected Logger mtLogger;
    private Class<T> entityClass;

    @PostConstruct
    private void init() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class<T> getEntityClass() {
        return entityClass;
    }

    private Session getSession() throws Exception {
        return sessionFactory.getCurrentSession();
    }

    public void persist(T entity) throws Exception {
        getSession().persist(entity);
    }

    public void persist(List<T> entityList) throws Exception {
        for (T entity: entityList) {
            persist(entity);
        }
    }

    public T update(T entity) throws Exception {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    public T findByID(ID id) throws Exception {
        return (T) getSession().load(getEntityClass(), id);
    }

    public List<T> findAll() throws Exception {
        log.debug("-- DAO {}", findByCriteria());
        System.out.println("--DAO " + findByCriteria());
        return findByCriteria();
    }

    protected List<T> findByCriteria(Criterion... criterions) throws Exception {
        Criteria crit = getCriteria();
        for (Criterion c : criterions) {
            crit.add(c);
        }
        return Utils.safetyList(crit.list());
    }

    public void delete(T entity) throws Exception {
        getSession().delete(entity);
    }

    public void delete(List<T> entityList) throws Exception {
        for (T entity: entityList) {
            delete(entity);
        }
    }

    protected Criteria getCriteria() throws Exception {
        return getSession().createCriteria(getEntityClass());
    }

    protected void flush() throws Exception {
        getSession().flush();
    }

    public boolean isRecordExist(Criterion... criterions) throws Exception {
        List<T> list = findByCriteria(criterions);
        return list.size()>0;
    }

    protected List<T> findBySQL(String sql, Object... params) throws Exception {
        Query query = getSession().createSQLQuery(sql);
        for(int i=0; i<params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return Utils.safetyList(query.list());
    }
}