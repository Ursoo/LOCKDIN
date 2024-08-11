import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        RandomPasswordGenerator pass_gen = new RandomPasswordGenerator();
        RandomPasswordGenerator pass_gen2 = new RandomPasswordGenerator(24);

        System.out.println(pass_gen.get_passwordlen());
        System.out.println(pass_gen.get_generatedpass());
        System.out.println(pass_gen2.get_passwordlen());
        System.out.print(pass_gen2.get_generatedpass());
    }
}
