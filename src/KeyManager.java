import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class KeyManager {
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String KEYSTORE_TYPE = "PKCS12";
    private static KeyStore key_vault;
    private static final int KEY_SIZE = 256;
    private static String key_vault_name = "key_vault.p12";
    private static char[] key_vault_password = "parola123".toCharArray();

    public KeyManager(){
        try{
            key_vault = KeyStore.getInstance(KEYSTORE_TYPE);
        }catch(KeyStoreException e){
            e.printStackTrace();
        }
    }

    public static SecretKeySpec generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);

        keygen.init(KEY_SIZE);

        byte[] secret_key = keygen.generateKey().getEncoded();

        SecretKeySpec generated_skey = new SecretKeySpec(secret_key, ENCRYPTION_ALGORITHM);

        return generated_skey;
    }

    public static void storeKey(SecretKey key, String key_name) {

        if(!checkKeyVaultExists())
            createKeyVault(key_vault_password);

        loadKeyVault(key_vault_password);

        KeyStore.SecretKeyEntry key_entry = new KeyStore.SecretKeyEntry(key);
    }

    private static void loadKeyVault(char[] key_vault_password){
        try{
            FileInputStream keystore_file  = new FileInputStream(key_vault_name);
            key_vault.load(keystore_file, key_vault_password);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static boolean checkKeyVaultExists(){
        try{
            File key_vault_file = new File(key_vault_name);
            if(!key_vault_file.isFile())
                return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    private static void createKeyVault(char[] password){
        try{
            key_vault.load(null, null);

            FileOutputStream keyvault_file = new FileOutputStream(key_vault_name);
            key_vault.store(keyvault_file, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            createKeyVault("parola123".toCharArray());
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(checkKeyVaultExists());
    }

}
