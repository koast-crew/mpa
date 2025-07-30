package koast.admin.security;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.security.Security;
import java.util.Set;

@Slf4j
public class JasyptTest {
    @Test
    public void testJasyptEncryption() {
        // 1. JasyptConfig만 로드한 Spring 컨텍스트 생성
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JasyptConfig.class);

        // 2. Bean 이름으로 가져오기 (JasyptConfig에서 @Bean("jasyptStringEncryptor") 선언됨)
        PooledPBEStringEncryptor encryptor = (PooledPBEStringEncryptor) context.getBean("jasyptStringEncryptor");

        log.info("-----------------------------------------");

        String url = "jdbc:postgresql://localhost:25432/mpa";
        String username = "postgres";
        String password = "postgres";

        String encryptedUrl = encryptor.encrypt(url);
        String encryptedUsername = encryptor.encrypt(username);
        String encryptedPassword = encryptor.encrypt(password);
        log.info("암호화 encryptedUrl = {}", encryptedUrl);
        log.info("암호화 encryptedUsername = {}", encryptedUsername);
        log.info("암호화 encryptedPassword = {}", encryptedPassword);

        String decryptedUrl = encryptor.decrypt(encryptedUrl);
        String decryptedUsername = encryptor.decrypt(encryptedUsername);
        String decryptedPassword = encryptor.decrypt(encryptedPassword);
        log.info("복호화 decryptedUrl = {}", decryptedUrl);
        log.info("복호화 decryptedUsername = {}", decryptedUsername);
        log.info("복호화 decryptedPassword = {}", decryptedPassword);
    }
}
