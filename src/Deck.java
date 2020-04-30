import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    public Deck(int anzahl) {

        deck = new ArrayList<>();

        for (int i = 0; i < anzahl ; i++) {
            for (short k = 0; k < 4; k++) {
                for (short l = 0; l < 13; l++) {
                    deck.add(new Card(k, l));
                }
            }
        }
        this.shuffle();
    }

    public int getDeckSize() {
        return deck.size();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card drawFromDeck() {
        return deck.remove(0);
    }
}
