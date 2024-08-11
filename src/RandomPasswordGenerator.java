import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class RandomPasswordGenerator {

    private StringBuilder password;
    private SecureRandom random_generator;
    private int password_length = 12;
    private String generated_password;
    private static final String uppercase_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowercase_letters = "abcdefghijklmnopqrstuvwxyz";
    private static final String digits = "0123456789";
    private static final String symbols = "!@#$%^&*()-_+=<>?";
    private static final String[] chars_array = {uppercase_letters, lowercase_letters, digits, symbols};
    private static final ArrayList<String> valid_characters = new ArrayList<String>(Arrays.asList(chars_array)) ;

    public RandomPasswordGenerator (){
        this.password = new StringBuilder();
        this.random_generator = new SecureRandom();

        this.generated_password = generate_password();
    }

    public RandomPasswordGenerator (int length){
        this.password = new StringBuilder();
        this.random_generator = new SecureRandom();
        this.password_length = length;

        this.generated_password = generate_password();
    }

    public String get_generatedpass(){
        return this.generated_password;
    }

    public int get_passwordlen(){
        return this.password_length;
    }

    private String generate_password(){
        ArrayList<String> temp_charsets = new ArrayList<String>(valid_characters);
        int charset_index;
        String character_set;
        char random_char;

        for(int i = 0; i < this.password_length; i++){

            if(temp_charsets.size() != 0){
                charset_index = random_generator.nextInt(temp_charsets.size());
                character_set = temp_charsets.get(charset_index);
                temp_charsets.remove(charset_index);
            }
            else
            {
                charset_index = random_generator.nextInt(valid_characters.size());
                character_set = valid_characters.get(charset_index);
            }

            random_char = character_set.charAt(random_generator.nextInt(character_set.length()));
            password.append(random_char);


        }
        return this.password.toString();
    }
}
