
import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

public class Main {


    public static void main(String[] args) {
        String[] handStrings = {
            "High Card: ", "Pair: ", "Two Pair: ", "Three of a kind: ", "Straight: ",
            "Flush: ", "Full House: ", "Four of a kind: ", "Straight Flush: ", "Royal Flush: "
        };
        BigInteger[] distribution;

        //bestimmung, wie lange die simulation dauert
        LocalDateTime start = LocalDateTime.now();
        new Simualtion(new BigInteger("10000000000"), 1, 1);
        System.out.println(Duration.between(start, LocalDateTime.now()).toString());

        distribution = Game.distribution;

        for (int i = 0; i < distribution.length; i++) {
            System.out.println(handStrings[i] + distribution[i]);
        }
    }
}
