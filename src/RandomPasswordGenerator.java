import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class RandomPasswordGenerator {

    private StringBuilder password;
    private SecureRandom random_generator;
    private int password_length = 12;
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_+=<>?";
    private static final String[] CHARSETS_ARRAY = {UPPERCASE, LOWERCASE, DIGITS, SYMBOLS};
    private static final ArrayList<String> REQUIRED_CHARSETS = new ArrayList<String>(Arrays.asList(CHARSETS_ARRAY)) ;

    public RandomPasswordGenerator (){
        this.password = new StringBuilder();
        this.random_generator = new SecureRandom();
    }

    public RandomPasswordGenerator (int length){
        this.password = new StringBuilder();
        this.random_generator = new SecureRandom();
        this.password_length = length;
    }

    public int getPasswordLen(){
        return this.password_length;
    }

    public String generate_password(){
        String generated_password="";
        ArrayList<String> temp_charsets = new ArrayList<String>(REQUIRED_CHARSETS);
        int charset_index;
        String character_set;
        char random_char;

        for(int i = 0; i < this.password_length; i++){

            if(temp_charsets.size() != 0){ //this block ensures password contains at least one of each char type
                charset_index = random_generator.nextInt(temp_charsets.size());
                character_set = temp_charsets.get(charset_index);
                temp_charsets.remove(charset_index);
            }
            else
            {
                charset_index = random_generator.nextInt(REQUIRED_CHARSETS.size());
                character_set = REQUIRED_CHARSETS.get(charset_index);
            }

            random_char = character_set.charAt(random_generator.nextInt(character_set.length()));
            password.append(random_char);


        }
        generated_password = this.password.toString();
        return generated_password;
    }
}
