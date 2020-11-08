import java.math.BigInteger;

public class Simualtion{

    /**
     * Führt rounds viele Games mit einer bestimmten anzahl von playern und decks pro game aus
     * @param rounds anzahl der Spiele, die simuliert werden
     * @param players anzahl der Spiler, pro Spiel
     * @param decks anzahl der Decks pro Spiel
     */
    Simualtion(BigInteger rounds, int players, int decks) {

        if (rounds.compareTo(BigInteger.ZERO) != 0 && players > 0 && decks > 0) {

            do {
                rounds = rounds.subtract(BigInteger.ONE);
                System.out.println(rounds.toString());
                Game g = new Game(players, decks);

                //System.out.println("\n\n");

            }while (rounds.compareTo(BigInteger.ZERO) != 0);
        }
    }
}
