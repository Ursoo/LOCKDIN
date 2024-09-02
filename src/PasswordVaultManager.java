import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class PasswordVaultManager{

    private String vault_name;
    private File vault;
    private BufferedReader file_reader;
    private BufferedReader user_input_reader;
    private BufferedWriter file_writer;

    public PasswordVaultManager() {
        this.vault_name = "passvault.txt";
        this.vault = new File(this.vault_name);
        try{
        this.file_writer = new BufferedWriter(new FileWriter(this.vault.getAbsolutePath(), true));
        this.file_reader = new BufferedReader(new FileReader(this.vault.getAbsolutePath()));
        this.user_input_reader =  new BufferedReader(new InputStreamReader(System.in));
     }catch(IOException e){
        e.printStackTrace();
    }
    }

    public ArrayList<String> listIdentifiers(){
        ArrayList<String> identifiers = new ArrayList<>();

        return identifiers;
    }

    public String getPasswordByIdentifier(String identifier){
        return "";
    }

    public void addNewPasswordToVault(){
        RandomPasswordGenerator pass_gen =  new RandomPasswordGenerator();
        String password = pass_gen.generate_password();

        Toolkit.getDefaultToolkit()
        .getSystemClipboard()
        .setContents(new StringSelection(password), null);

        System.out.println("Password copied to clipboard!");
        try{
        this.promptSavePassword(password);
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

    private void promptSavePassword(String password) throws IOException{
        System.out.print("Do you want to save to password vault (y/n)?: ");

        String user_input = user_input_reader.readLine();

        switch(user_input.toLowerCase()){
            case "y":
            case "yes": {
                System.out.print("Choose an identifier for your password (max 20 characters): ");
                String pass_id = this.user_input_reader.readLine();
                try{
                this.addPassword(password, user_input);
                } catch(IOException e){
                    System.out.print("An error ocurred while trying to save password.");
                }
                break;
            }
            case "n":
            case "no": break;
            default: {
                System.out.println("Only yes or no allowed.");
                promptSavePassword(password);
                break;
            }
        }
    }

    public void  addPassword (String pass_id, String value) throws IOException{

        file_writer.append(pass_id + ": " + value);
        file_writer.newLine();

        file_writer.close();
    }

    public void deleteVault(){

    }
}
