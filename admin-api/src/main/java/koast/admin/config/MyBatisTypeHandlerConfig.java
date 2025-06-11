package koast.admin.config;

import koast.admin.common.mybatis.typehandler.GenericEnumTypeHandler;
import koast.admin.domain.user.UserStatus;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis enum ↔ String 변환 전역 설정
 */
@Configuration
public class MyBatisTypeHandlerConfig {

    @Bean
    public ConfigurationCustomizer typeHandlerConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();

                registry.register(UserStatus.class, new GenericEnumTypeHandler<>(UserStatus.class));
            }
        };
    }
}