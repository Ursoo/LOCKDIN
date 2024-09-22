import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class SecurityManager{
    private final static String transformation = "AES/CBC/PKCS5Padding";
    private Cipher cipher;
    private SecretKey secret_key;
    
    public SecurityManager(){
        try{
        this.cipher = Cipher.getInstance(transformation);
        this.secret_key = KeyGenerator.getInstance(transformation).generateKey();
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (NoSuchPaddingException e){
            e.printStackTrace();
        }
    
    }

    public void encrypt_content(String content){
        
    }
}