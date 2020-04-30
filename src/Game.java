public class Game {

    private final Deck deck;
    private final Hand[] hands;

    private int counter = 1;

    public Game(int player, int decks) {

        deck = new Deck(decks);
        hands = new Hand[player];
        iterate(player);
    }

    private void iterate(int players) {
        System.out.println("Round " + counter + ":");
        Card[] cardsTisch = new Card[]{deck.drawFromDeck(), deck.drawFromDeck(), deck.drawFromDeck(), deck.drawFromDeck(), deck.drawFromDeck()};

        for (int i = 0; i < players; i++) {
            hands[i] = new Hand(cardsTisch, new Card[]{deck.drawFromDeck(), deck.drawFromDeck()});
            System.out.println("p" + i + 1 + ": " + hands[i].getValue() + " " + hands[i].toString());
        }
        counter++;
        if (deck.getDeckSize() >= 2*players + 5) {
            System.out.println();
            iterate(players);
        }
    }
}
