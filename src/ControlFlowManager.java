import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControlFlowManager {
    private static String choice;
    private static BufferedReader user_input_reader =  new BufferedReader(new InputStreamReader(System.in));;


    public static void run(){

        while(true){
        displayMainMenu();
        try{
        choice = user_input_reader.readLine();
        }catch (IOException e){
            System.out.println("Error occurred while trying to execute your choice");
            e.printStackTrace();
        }

        if(choice.toLowerCase().equals("quit") || choice.toLowerCase().equals("q"))
            break;

        switch (choice) {
            case "1":
                addPasswordToVault();
                break;
            case "3":
                listItems();
                break;
            default:
                System.out.println("Invalid selection! Please select one of the available options!");
                break;
        }
    }

    }

    private static void displayMainMenu(){
        String[] menu_options = {"1. Create a new password", "2. Create a password vault", "3. Open vault", "4. Help"};
        System.out.println("======== LockdIN v.0.0.1 ========");
        for(String option: menu_options){
            System.out.println(option);
        }
        System.out.println("Insert q or quit to quit");
        System.out.print("Your choice: ");
    }

    private static void addPasswordToVault(){

        PasswordVaultManager vault_manager = new PasswordVaultManager();

        vault_manager.addPasswordToVault();
        //vault_manager.closeInputBuffer();
        vault_manager.closeWriteBuffer();

    }

    private static void listItems(){

        PasswordVaultManager vault_manager = new PasswordVaultManager();

        vault_manager.listVaultItems();
        vault_manager.closeReadBuffer();
    }

}
