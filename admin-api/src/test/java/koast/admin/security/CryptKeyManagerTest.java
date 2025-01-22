package koast.admin.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class CryptKeyManagerTest {
    @Test
    public void testGetInitKey() {
        String initKey = CryptKeyManager.getInitKey();
        log.info("---------- initKey = {}", initKey);
    }
}
