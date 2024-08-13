
public class App {
    public static void main(String[] args) throws Exception {
        RandomPasswordGenerator pass_gen = new RandomPasswordGenerator(24);

        PasswordVaultManager vault_manager = new PasswordVaultManager();

        String value;

        for(int i = 0; i< 10; i++){
            vault_manager.updateVault(""+i);
    }

    }
}
