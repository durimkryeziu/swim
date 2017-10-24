package org.prnhs.javaee.swim.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "org.prnhs.javaee.swim.core.dao")
@EntityScan(basePackages = {"org.prnhs.javaee.swim.core.entity"})
public class CoreConfig {

}
