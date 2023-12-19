package com.utils.config;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class JasyptTest {

    /**
     * 암호화 하고 싶은 값들을 variable1 ~ 4에 넣고 테스트 수행 하면 암호화된 값들을 간단하게 콘솔에서 확인 가능
     */
    @Test
    void stringEncryptor() {
        String variable1 = "";
        String variable2 = "";
        String variable3 = "";
        String variable4 = "";

        System.out.println("variable1 -> ENC(" + jasyptEncoding(variable1) + ")");
        System.out.println("variable2 -> ENC(" + jasyptEncoding(variable2) + ")");
        System.out.println("variable3 -> ENC(" + jasyptEncoding(variable3) + ")");
        System.out.println("variable4 -> ENC(" + jasyptEncoding(variable4) + ")");
    }

    /**
     * key에 암호화에 사용할 키를 넣어서 들어오는 value를 암호화 한 후 return
     */
    public String jasyptEncoding(String value) {

        String key = "exampleKey";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);
        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC");
        return encryptor.encrypt(value);
    }

}
