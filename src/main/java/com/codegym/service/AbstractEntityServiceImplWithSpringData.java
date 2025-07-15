package com.codegym.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractEntityServiceImplWithSpringData<E, I extends Serializable> {
    protected abstract JpaRepository<E, I> repository();

    public List<E> findAll() {
        return repository().findAll();
    }

    public Optional<E> findOne(I id) {
        return repository().findById(id);
    }

    public E save(E entity) {
        return repository().save(entity);
    }

    public List<E> saveAll(List<E> entities) {
        return repository().saveAll(entities);
    }

    public boolean exists(I id) {
        return repository().existsById(id);
    }

    public List<E> findAllById(List<I> ids) {
        return repository().findAllById(ids);
    }

    public long count() {
        return repository().count();
    }

    public void delete(I id) {
        repository().deleteById(id);
    }

    public void delete(E entity) {
        repository().delete(entity);
    }

    public void deleteAll(List<E> entities) {
        repository().deleteAll(entities);
    }

    public void deleteAll() {
        repository().deleteAll();
    }
}
