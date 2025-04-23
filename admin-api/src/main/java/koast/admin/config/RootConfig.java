package koast.admin.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@Slf4j
@Configuration
@ComponentScan(
        basePackages = {"koast.admin.service", "koast.admin.repository"},
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class) },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = RestController.class) })
@MapperScan(basePackages="koast.admin.repository")
@PropertySource("classpath:admin-${spring.profiles.active}.properties")
public class RootConfig {
    private final DataSourceProperties dataSourceProperties;

    public RootConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    public DataSource dataSource() {
        String decryptedUrl = dataSourceProperties.getDecryptedUrl();
        String decryptedUsername = dataSourceProperties.getDecryptedUsername();
        String decryptedPassword = dataSourceProperties.getDecryptedPassword();

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dataSourceProperties.getDriverClassName());
        hikariConfig.setJdbcUrl(decryptedUrl);
        hikariConfig.setUsername(decryptedUsername);
        hikariConfig.setPassword(decryptedPassword);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/**/*.xml"));

        return sessionFactoryBean.getObject();
    }

    /**
     * TODO 여기를 이렇게 구현해야 동시 접속자 문제가 생기지 않는다고 함
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
