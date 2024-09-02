import java.util.Scanner;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.awt.Toolkit;

public class ControlFlowManager {
    private static int choice;
    private static Scanner input_reader =  new Scanner(System.in);
    private static PasswordVaultManager vault_manager = new PasswordVaultManager();


    public static void run(){

        while(true){
        System.out.println("Select an option:");
        System.out.println("1. Create a new password");
        System.out.println("2. Create a password vault");
        System.out.println("3. List all identifiers");
        System.out.println("4. Exit");
        System.out.print("Your choice (must be one of the numbers): ");
        choice = input_reader.nextInt();

        switch (choice) {
            case 1:
                addPasswordToVault();
                break;
            default:
                break;
        }
    }

    }

    private static void addPasswordToVault(){
    }

}
