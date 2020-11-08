import java.math.BigDecimal;
import java.math.BigInteger;

public class Game{

    private final Deck deck;
    private final Hand[] hands;
    private final int players;

    private int counter = 1; // zählt die Runden
    public static BigInteger[] distribution = new BigInteger[10]; //verteilung der handkarten


    /**
     * @param player anzahl der Spieler im Spiel
     * @param decks anzahl der Decks im Spiel
     */
    public Game(int player, int decks) {
        deck = new Deck(decks);
        hands = new Hand[player];
        this.players = player;
        iterate();
    }


    /**
     *Führt eine Runde aus
     */
    private void iterate() {
        //prüft, ob noch genügend Karten im Deck sind
        if (deck.getDeckSize() >= 2*players + 5) {
            //System.out.println("Round " + counter + ":");
            //zieht 5 karten vom Deck für den Tisch
            Card[] cardsTisch = new Card[]{deck.drawFromDeck(), deck.drawFromDeck(), deck.drawFromDeck(), deck.drawFromDeck(), deck.drawFromDeck()};

            //zieht zwei Karten für jeden Spieler und stellt diese mit den Karten vom Deck in eine Hand
            for (int i = 0; i < players; i++) {
                hands[i] = new Hand(cardsTisch, new Card[]{deck.drawFromDeck(), deck.drawFromDeck()});
            }

            for (Hand h : hands) {
                if (distribution[(int) h.getValue()] == null) {
                    distribution[(int) h.getValue()] = new BigInteger("0");
                }
                distribution[(int) h.getValue()] = distribution[(int) h.getValue()].add(BigInteger.ONE);

            }
            counter++;
            iterate();
        }
    }

    public static BigInteger[] getDistribution() {
        return distribution;
    }
}
