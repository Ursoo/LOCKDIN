import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SecurityManager{
    private final static String ENCRYPT_TRANSFORM = "AES/CBC/PKCS5Padding";
    private Cipher cipher;
    private SecretKey secret_key;

    public SecurityManager(){
        try{
        this.cipher = Cipher.getInstance(ENCRYPT_TRANSFORM);
        this.secret_key = KeyManager.generateSecretKey();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public void encrypt_content(String content, String file_name){
        try{
        this.cipher.init(Cipher.ENCRYPT_MODE, secret_key);
        }catch(InvalidKeyException e){
            e.printStackTrace();
        }
        byte[] init_vector = this.cipher.getIV();

        try{
            FileOutputStream file_out = new FileOutputStream(file_name);
            CipherOutputStream cipher_out = new CipherOutputStream(file_out, cipher);
            file_out.write(init_vector);
            cipher_out.write(content.getBytes());
            cipher_out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String decrypt(String fileName) throws Exception{
    String content;

    try (FileInputStream fileIn = new FileInputStream(fileName)) {
        byte[] fileIv = new byte[16];
        fileIn.read(fileIv);
        cipher.init(Cipher.DECRYPT_MODE, this.secret_key, new IvParameterSpec(fileIv));

        try (
                CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                InputStreamReader inputReader = new InputStreamReader(cipherIn);
                BufferedReader reader = new BufferedReader(inputReader)
            ) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            content = sb.toString();
        }

    }
    return content;
}

}