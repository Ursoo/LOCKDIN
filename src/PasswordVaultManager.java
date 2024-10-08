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
    private static SecurityManager security_manager = new SecurityManager();

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

    public void listVaultItems(){
        //ArrayList<VaultItem> vault_items = this.getVaultItems();
        String decrypted_content="";
        try{
        decrypted_content = security_manager.decrypt(vault_name);}
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Title -- Username -- Password -- Url");
        System.out.print("\n");
        /*for(VaultItem item: vault_items){
            System.out.println(item.getTitle() + " -- " + item.getUsername() + " -- " + item.getPassword() + " -- " + item.getUrl());
        }*/
        System.out.println(decrypted_content);
    }

    private ArrayList<VaultItem> getVaultItems(){
        ArrayList<VaultItem> vault_items = new ArrayList<>();
        String line;
        try{
        while((line = this.file_reader.readLine()) != null){
            String[] line_elems = line.split("-");
            VaultItem item = new VaultItem(line_elems[0], line_elems[1], line_elems[2], line_elems[3]);
            vault_items.add(item);
        }} catch (IOException e) {
            System.out.print("Failed to read from vault due to error.");
            e.printStackTrace();
        }
        return vault_items;
    }

    public String getPasswordByIdentifier(String identifier){
        return "";
    }

    public void addPasswordToVault(){
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
                System.out.print("Insert title: ");
                String item_title = this.user_input_reader.readLine();
                System.out.print("Insert username: ");
                String item_username = this.user_input_reader.readLine();
                System.out.print("Insert url: ");
                String item_url = this.user_input_reader.readLine();
                VaultItem item = new VaultItem(item_title, item_username, password, item_url);
                try{
                this.storePassword(item);
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

    public void  storePassword (VaultItem new_item) throws IOException{
        String item_to_store = new_item.getTitle() + " - " + new_item.getUsername() + " - " + new_item.getPassword() + " - " + new_item.getUrl() + "\n";
        security_manager.encrypt_content(item_to_store, this.vault_name);

    }

    public void closeWriteBuffer(){
        try{
            this.file_writer.close();
        }catch (IOException e){
            System.out.print("Failed to close file writer.");
            e.printStackTrace();
        }
    }

    public void closeReadBuffer(){
        try{
            this.file_reader.close();
        }catch (IOException e){
            System.out.print("Failed to close file writer.");
            e.printStackTrace();
        }
    }

    public void closeInputBuffer(){
        try{
            this.user_input_reader.close();
        }catch (IOException e){
            System.out.print("Failed to close file writer.");
            e.printStackTrace();
        }
    }

    public void deleteVault(){
    }
}
