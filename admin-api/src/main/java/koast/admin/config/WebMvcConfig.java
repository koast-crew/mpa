package koast.admin.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import koast.admin.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/**") // 전체 경로 대상
                .excludePathPatterns(
                        "/login/**",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                );
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // 1. LocalDateTime → "yyyy-MM-dd HH:mm:ss" 포맷 직렬화
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        javaTimeModule.addSerializer(java.time.LocalDateTime.class, new LocalDateTimeSerializer(formatter));
        mapper.registerModule(javaTimeModule);

        // 2. 날짜를 timestamp 대신 문자열로 출력 (ISO 아님)
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 4. JSON에 정의되지 않은 필드 무시 (역직렬화 안전성 확보)
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 5. 기본은 camelCase 유지. snake_case 필요 시 아래 줄 주석 해제
        // mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        return mapper;
    }
}

