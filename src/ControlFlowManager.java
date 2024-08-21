import java.util.Scanner;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class ControlFlowManager {
    private int choice;
    private Scanner input_reader =  new Scanner(System.in);
    private RandomPasswordGenerator pass_gen =  new RandomPasswordGenerator();
    private PasswordVaultManager vault_manager = new PasswordVaultManager();

    public ControlFlowManager(){
        while(this.run());
    }

    private boolean run(){

        System.out.println("Select an option:");
        System.out.println("1. Create a new password");
        System.out.println("2. Create a password vault");
        System.out.println("3. Exit");
        System.out.println("Your choice (must be one of the numbers): ");
        this.choice = input_reader.nextInt();

        if(this.choice == 1){
            this.createPassword();
        } else if(this.choice == 2){
            //
        }
        else if(this.choice == 3){
            input_reader.close();
            return false;
        }

        return true;
    }

    private void createPassword(){
        String password = pass_gen.generate_password();

        System.out.println(password);

        Toolkit.getDefaultToolkit()
        .getSystemClipboard()
        .setContents(new StringSelection(password), null);

        System.out.println("Password copied to clipboard!");
        System.out.print("Do you want to save to password vault (y/n)?: ");

        String user_input = input_reader.nextLine();

        switch(user_input.toLowerCase()){
            case "y":
            case "yes": {
                System.out.println("Selection:" + user_input);
                break;}
            case "n":
            case "no":{
                System.out.println("Selection:" + user_input);
                break;}
        }


    }
}
