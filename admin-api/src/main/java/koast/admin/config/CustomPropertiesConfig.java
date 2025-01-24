package koast.admin.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@Configuration
public class CustomPropertiesConfig {

    @Value("${masking-file-dir}")
    private String maskingFileDir;
}
