package jmasters.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jmasters.spring.repository.CourseRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = { CourseRepository.class })
public class RepositoryConfig {

}
