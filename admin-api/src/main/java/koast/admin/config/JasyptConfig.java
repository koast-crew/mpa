package koast.admin.config;

import koast.admin.security.KoastKeyManager;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JasyptConfig {

    /**
     * TODO
     * SHA256_AES 계열이 보안이나 속도가 빠른데, 오류나 나면서 해결이 안되었음
     * @return
     */
    @Bean("jasyptStringEncryptor")
    public PooledPBEStringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(KoastKeyManager.getInitKey());
        config.setAlgorithm("PBEWithSHA1AndDESede");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");

        encryptor.setConfig(config);
        return encryptor;
    }
}
