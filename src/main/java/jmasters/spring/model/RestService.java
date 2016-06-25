package jmasters.spring.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RestService<T> {

    JpaRepository<T, Long> repository();

    List<T> list();

    T get(Long id);

    T save(T entity);

    void remove(Long id);
}
