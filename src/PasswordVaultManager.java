import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordVaultManager{

    private String vault_name;
    private File vault;

    public PasswordVaultManager() {
        this.vault_name = "passvault.txt";
        this.vault = new File(this.vault_name);
    }

    public PasswordVaultManager(String path) {
        this.vault_name = path;
        this.vault = new File(this.vault_name);
    }

    public String getVaultName(){
        return this.vault_name;
    }

    public void setVaultPath(String path){
        this.vault_name = path;
    }

    public File getVault(){
        return this.vault;
    }

    public void setVault(File file_obj){
        this.vault = file_obj;
    }

    public void createVault(){
        try{
            boolean createVaultResult = this.vault.createNewFile();

            if(createVaultResult){
                System.out.println("Password vault at" + this.vault.getAbsolutePath() + " successfully created.");
            } else {
                System.out.println("Password vault already exists.");
            }

        }catch(IOException exception){
            System.out.println("Failed creating the password vault due to error.");
            exception.printStackTrace();
        }

    }

    public void  updateVault (String[] values) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.vault));

        for(String value: values){
            writer.append(value);
            writer.newLine();
        }

        writer.close();
    }

    public void  updateVault (String value) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.vault.getAbsolutePath(), true));

        writer.append(value);

        writer.close();
    }

    public void deleteVault(){

    }
}
