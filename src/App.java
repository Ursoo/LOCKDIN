import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        RandomPasswordGenerator pass_gen = new RandomPasswordGenerator();
        RandomPasswordGenerator pass_gen2 = new RandomPasswordGenerator(24);

        System.out.println(pass_gen.getPasswordLen());
        System.out.println(pass_gen.getGeneratedPass());
        System.out.println(pass_gen2.getPasswordLen());
        System.out.print(pass_gen2.getGeneratedPass());
    }
}
