
public class PasswordVaultManager{
    
    private String vault_path;

    public PasswordVaultManager() {
        this.vault_path = "./";
    }

    public PasswordVaultManager(String path) {
        this.vault_path = path;
    }

    public String getVaultPath(){
        return this.vault_path;
    }

    public void setVaultPath(String path){
        this.vault_path = path;
    }

    public void createVault(){

    }

    public void  updateVault(){

    }

    public void deleteVault(){

    }
}
