package support.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "engenhariaDeSoftware.demo.domain",
    repositoryBaseClass = CustomRepositoryImpl.class
)
public class RepositoryConfig {
} 