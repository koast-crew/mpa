package koast.admin.config;

import koast.admin.security.CryptKeyManager;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Bean
    public PooledPBEStringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(CryptKeyManager.getInitKey()); // 🔹 보안 키 설정
        config.setAlgorithm("PBEWithHmacSHA512AndAES_256"); // 🔹 강력한 암호화 알고리즘 사용
        config.setKeyObtentionIterations("10000"); // 🔹 더 높은 반복 횟수 적용 (보안 강화)
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // 🔹 랜덤 솔트 적용
        config.setStringOutputType("base64");

        encryptor.setConfig(config);
        return encryptor;
    }
}
