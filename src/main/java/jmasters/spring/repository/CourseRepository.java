package jmasters.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jmasters.spring.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);

    List<Course> findByNameContains(String name);

    @Query("select c.name,count(c) from Course c group by c.name")
    List<Object[]> findCountByName();

    @Query("select c from Course c where c.id<:id")
    List<Course> findBelowId(@Param("id") Long id);
}
