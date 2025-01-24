package koast.admin.config;

import lombok.Getter;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class DataSourceProperties {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String encryptedUrl;

    @Value("${spring.datasource.username}")
    private String encryptedUsername;

    @Value("${spring.datasource.password}")
    private String encryptedPassword;

    private final PooledPBEStringEncryptor encryptor;

    public DataSourceProperties(PooledPBEStringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String getDecryptedUrl() {
        return decrypt(encryptedUrl);
    }

    public String getDecryptedUsername() {
        return decrypt(encryptedUsername);
    }

    public String getDecryptedPassword() {
        return decrypt(encryptedPassword);
    }

    private String decrypt(String value) {
        if (value != null) {
            return encryptor.decrypt(value);
        }
        return value;
    }
}