package com.utils.config;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    /**
     * jasypt.encryptor.password=xxxx로 java 띄울때 환경변수로 같이 세팅해주고,
     * 그 값으로 암호화한 값을 application.yaml에 저장하면 서버 뜨면서 복호화해서 사용할 수 있음
     */
    @Value("${jasypt.encryptor.password}")
    private String encryptKey;


    /**
     * yaml파일에서 설정해둔 코드값들이나 하드코딩 되어 있는 값들을 암호화할 수 있도록 설정하는 config입니다.
     * 간단하게 암호화할 수 있도록 test code도 작성해두었습니다.
     */
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptKey);
        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC"); // 여러 알고리즘이 존재
        return encryptor;
    }

}
