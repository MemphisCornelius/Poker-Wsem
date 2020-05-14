public class Simualtion {

    /**
     * Führt rounds viele Games mit einer bestimmten anzahl von playern und decks pro game aus
     * @param rounds anzahl der Spiele, die simuliert werden
     * @param players anzahl der Spiler, pro Spiel
     * @param decks anzahl der Decks pro Spiel
     */
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
