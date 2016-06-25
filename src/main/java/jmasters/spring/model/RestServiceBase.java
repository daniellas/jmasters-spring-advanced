package jmasters.spring.model;

import java.util.List;

public abstract class RestServiceBase<T> implements RestService<T> {

    @Override
    public List<T> list() {
        return repository().findAll();
    }

    @Override
    public T get(Long id) {
        return repository().findOne(id);
    }

    @Override
    public T save(T entity) {
        return repository().save(entity);
    }

    @Override
    public void remove(Long id) {
        repository().delete(id);
    }

}
