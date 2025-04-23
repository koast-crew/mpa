package koast.admin.security.crypto;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class KoastKeyManager {

	private static final String randomKeyword = "dHNhb2shIHNpIGVtYW4geW0gLnRzYW9rIHJvZiBhZWRpIGRhYiBhIGVrYW0gdG9uIG9kIGVzYWVscCAseWVrIHRlcmNlcyMgZG51b2YgZXZhaCB1b3kgZkk=";

    public static String getInitKey() {
        String result = null;
        try {
            byte[] base64decodedBytes = Base64.getDecoder().decode(randomKeyword.getBytes("UTF-8"));
            result = new String(base64decodedBytes, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException ===== " + e.getMessage());

        }
        result = reverseString(result);

        return parse(result);
    }

    private static String reverseString(String value) {
        if(value == null) return "";
        return (new StringBuffer(value)).reverse().toString();
    }

    private static String parse(String value) {
        return value.substring(83, 89) + value.substring(18, 25) + value.substring(26, 29);
    }
}
