package jmasters.spring.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jmasters.spring.model.RestService;

public abstract class RestControllerBase<T> {

    protected abstract RestService<T> service();

    @RequestMapping
    public List<T> list() {
        return service().list();
    }

    @RequestMapping("/{id}")
    public T get(@PathVariable Long id) {
        return service().get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public T save(@RequestBody T entity) {
        return service().save(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        service().remove(id);
    }

}
