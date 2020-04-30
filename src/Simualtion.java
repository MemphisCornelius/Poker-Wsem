public class Simualtion {

    Simualtion(int rounds, int players, int decks) {

        if (rounds > 0 && players > 0 && decks > 0) {
            for (int i = 1; i <= rounds; i++) {
                System.out.println("Game " + i + ":");
                new Game(players, decks);
                System.out.println("\n\n");
            }
        }
    }
}
