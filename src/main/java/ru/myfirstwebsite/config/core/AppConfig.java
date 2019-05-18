package ru.myfirstwebsite.config.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.myfirstwebsite.config.swagger.SwaggerConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan
@EnableAspectJAutoProxy
@EnableTransactionManagement(proxyTargetClass = true)
@Import({DatabaseConfig.class, JdbcTemplateConfig.class, SwaggerConfig.class})
public class AppConfig {
}
