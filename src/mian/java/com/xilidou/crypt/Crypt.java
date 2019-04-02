package com.xilidou.crypt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Component
@Slf4j
public class Crypt {

    private static SecretKeySpec secretKey;

    private static byte[] key;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        String myKey = "123456";
        key = myKey.getBytes("UTF-8");
        key = sha.digest(key);
        key = Arrays.copyOf(key,16);
        secretKey = new SecretKeySpec(key,"AES");
    }

    public String ecrypt(byte[] bytes){

        try {

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes1 = cipher.doFinal(bytes);

            log.info(Arrays.toString(bytes1));
            return Arrays.toString(bytes1);
        }catch (Exception e){
            log.error("error",e);
        }

        return null;
    }

    public String decrypt(byte[] bytes){

        try {

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] bytes1 = cipher.doFinal(bytes);
            log.info(Arrays.toString(bytes1));
            return Arrays.toString(bytes1);
        }catch (Exception e){
            log.error("error",e);
        }

        return null;
    }

}
