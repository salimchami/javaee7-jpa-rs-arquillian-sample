package com.slim.javaee.core.repository.impl;

import com.slim.javaee.core.repository.IJpaRepository;

/**
 * {@inheritDoc}
 */
@Stateless
@Transactional
public abstract class JpaRepository<T, P extends Serializable> implements IJpaRepository<T, P> {

    @PersistenceContext(unitName = "tcs", type = PersistenceContextType.TRANSACTION)
    protected EntityManager em;

    private Class<T> entityClass;

    /**
     * Constructeur par défaut.
     */
    public JpaRepository() {
        //Get "T" and assign it to this.entityClass
        Type genericSuperClass = getClass().getGenericSuperclass();
        ParameterizedType parametrizedType = null;
        while (parametrizedType == null) {
            if (genericSuperClass instanceof ParameterizedType) {
                parametrizedType = (ParameterizedType) genericSuperClass;
            } else {
                genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
            }
        }
        entityClass = (Class<T>) parametrizedType.getActualTypeArguments()[0];
    }

    @Override
    public void create(T entity) {
        em.persist(entity);
    }

    @Override
    public void create(Set<T> entites) {
        entites.forEach(this::create);
    }

    @Override
    public void deleteById(P id) {
        T entity = em.find(entityClass, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    
    public int deleteAll() {
        Query deleteAllQuery = em.createQuery("delete from " + entityClass.getSimpleName());
        return deleteAllQuery.executeUpdate();
    }

    @Override
    public Optional<T> findById(P id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public Set<T> listAll(Integer startPosition, Integer maxResult) {
        TypedQuery<T> findAllQuery = em.createQuery(
                "select t from " + entityClass.getSimpleName() + " t",
                entityClass);
        if (startPosition != null) {
            findAllQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllQuery.setMaxResults(maxResult);
        }
        return new HashSet<>(findAllQuery.getResultList());
    }

    @Override
    public Set<T> listAll() {
        return listAll(null, null);
    }

}
