import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    /**
     * erstellt denck
     * @param anzahl gibt an wieviele standard decks im deck drin sind
     */
    public Deck(int anzahl) {

        deck = new ArrayList<>();

        for (int i = 0; i < anzahl ; i++) {
            //fügt jede karte zum deck hinzu
            for (short k = 0; k < 4; k++) {
                for (short l = 0; l < 13; l++) {
                    deck.add(new Card(k, l));
                }
            }
        }
        this.shuffle();
    }

    /**
     * @return anzahl der karten im deck
     */
    public int getDeckSize() {
        return deck.size();
    }

    /**
     * mischt das deck
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * entfernt die "oberste" Karte vom Deck
     * @return "oberste" Karte vom Deck
     */
    public Card drawFromDeck() {
        return deck.remove(0);
    }
}
