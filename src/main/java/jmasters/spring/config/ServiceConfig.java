package jmasters.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import jmasters.spring.model.TeachersService;

@Configuration
@ComponentScan(basePackageClasses = { TeachersService.class })
public class ServiceConfig {

}
