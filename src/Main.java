import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDateTime;

public class Main {


    public static void main(String[] args) {

        try {
            //setzt den output von System.out auf output.txt
            PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        //bestimmung, wie lange die simulation dauert
        LocalDateTime start = LocalDateTime.now();
        new Simualtion(10,2,10);
        System.out.println(Duration.between(start, LocalDateTime.now()).toMillis() + "ms");
    }
}
