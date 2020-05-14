import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {

    private List<Card> cards;
    private short[] werte;
    private short[] symbole;
    private double value;

    public Hand(Card[] cardsTisch, Card[] cardsHand) {
        setNewHand(cardsTisch, cardsHand);
    }

    /**
     * Setzt neue Hand
     * @param cardsTisch Karten vom Tisch
     * @param cardsHand Karten vom Spieler
     */
    public void setNewHand(Card[] cardsTisch, Card[] cardsHand) {
        //prüft ob spieler 2 und tisch 5 karten hat
        if (cardsTisch.length == 5 && cardsHand.length == 2) {

            cards = new ArrayList<>();
            cards.addAll(Arrays.asList(cardsTisch));
            cards.addAll(Arrays.asList(cardsHand));

            werte = new short[cards.size()];
            symbole = new short[cards.size()];

            //speichert symbole und werte getrennt voneinander
            for (int i = 0; i < cards.size(); i++) {
                this.werte[i] = cards.get(i).getWert();
                this.symbole[i] = cards.get(i).getSymbol();
            }

            //sotiert die werte und symbole aufsteigend
            Arrays.sort(this.werte);
            Arrays.sort(this.symbole);

            //bestimmt den wert der Hand (und rundet auf 2stellen nach dem komma, wegen eines komischen bugs :D)
            value = (double) Math.round(getRanking() * 100) / 100;

        } else {
            System.err.println("Irgendwas lief falsch beim übergeben von den Karten");
            System.exit(0);
        }
    }

    /**
     * @return Hand als String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("H{[h: ");
        for (int i = 5; i < 7; i++) {
            sb.append(cards.get(i).toString()).append(", ");
        }
        sb.append("], [t: ");
        for (int i = 0; i < 5; i++) {
            sb.append(cards.get(i).toString()).append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }

    /**
     * Bestimmt den Wert der Hand, wobei die einer stelle den handnamen
     * und die nachkommastelle die highcard
     * @return wert der Hand
     */
    private double getRanking() {

        if (isRoyaleFlush() >= 9.0) {
            return isStraightFlush();
        } else if (isStraightFlush() > 8.0) {
            return isStraightFlush();
        } else if (isFourOfAKind() > 7.0) {
            return isFourOfAKind();
        } else if (isFullHouse() > 6.0) {
            return isFullHouse();
        } else if (isFlush() > 5.0) {
            return isFlush();
        } else if (isStraight() > 4.0) {
            return isStraight();
        } else if (isThreeOfAKind() > 3.0) {
            return isThreeOfAKind();
        } else if (isTwoPair() > 2.0) {
            return isTwoPair();
        } else if (isPair() > 1.0) {
            return isPair();
        } else {
            return werte[cards.size() - 1] * 0.01 + 0.02;
        }
    }

    /**
     * @return den wert der Hand
     */
    public double getValue() {
        return value;
    }

    /**
     * @return 1 + highcard, wenn ein paar vorhanden ist
     */
    private double isPair() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 1; i++) {
            //checkt, ob zwei gleiche karten im sortierten werte array gleich sind
            if (werte[i] == werte[i + 1])
                result = 1.02 + (werte[i] * 0.01);
        }
        return result;
    }

    /**
     * @return 2 + highcard, wenn zwei paare vorhanden ist
     */
    private double isTwoPair() {
        int counter = 0;
        double value = 0.00;
        double result = 0.00;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (werte[i] == werte[i + 1]) {
                //checkt, ob zwei gleiche karten im sortierten werte array gleich sind und zählt counter hoch
                value = (werte[i] * 0.01);
                counter++;
            }
        }

        if (counter == 2)
            result = 2.02 + value;

        return result;
    }

    /**
     * @return 3 + highcard, wenn ein drilling vorhanden ist
     */
    private double isThreeOfAKind() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 2; i++) {
            //checkt, ob zwei gleiche karten mit einer karte dazwischen im sortierten werte array gleich sind
            if (werte[i] == werte[i + 2])
                result = 3.02 + (werte[i] * 0.01);
        }
        return result;
    }

    /**
     * @return 4 + highcard, wenn eine straße vorhanden ist
     */
    private double isStraight() {
        double result = 0.00;
        int counter = 0;

        for (int i = 0; i < cards.size() - 1; i++) {
            //schaut, ob nächste karte eins größer als die jetzige ist
            int j = werte[i + 1] - werte[i];
            switch (j) {
                case 1:
                    //ist eins größer => counter wird hochgezählt
                    counter++;
                    result = 4.02 + (werte[i + 1] * 0.01);
                    break;
                case 0:
                    //sind gleich groß => wird ignoriert
                    break;
                default:
                    //weder gleich noch +1 => counter reset
                    counter = 0;
                    break;
            }
            //checckt, ob 5 karten aufeinander folgen
            if (counter < 4) {
                result = 0;
            }
        }
        return result;
    }

    /**
     * @return 5 + highcard, wenn ein flush vorhanden ist
     */
    private double isFlush() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 5; i++) {
            //checkt, ob zwei gleiche karten mit drei karten dazwischen im sortierten symbole array gleich sind
            if (symbole[i] == symbole[i + 4])
                result = 5.02 + (werte[i + 4] * 0.01);
        }
        return result;
    }

    /**
     * @return 6 + highcard, wenn ein fullHouse vorhanden ist
     */
    private double isFullHouse() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 5; i++) {
            if ((werte[i] == werte[i + 1] && werte[i + 2] == werte[i + 4]) ||
                    (werte[i] == werte[i + 2] && werte[i + 3] == werte[i + 4]))
                result = 6.02 + (werte[i + 5] * 0.01);
        }
        return result;
    }

    /**
     * @return 7 + highcard, wenn ein vierling vorhanden ist
     */
    private double isFourOfAKind() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 3; i++) {
            //checkt, ob zwei gleiche karten mit zwei karten dazwischen im sortierten werte array gleich sind
            if (werte[i] == werte[i + 3])
                result = 7.02 + (werte[i] * 0.01);
        }
        return result;
    }

    /**
     * @return 8 + highcard, wenn ein straightFlush vorhanden ist
     */
    private double isStraightFlush() {
        double result = 0.00;
        //überprüft, ob flush vorhanden
        if (isFlush() >= 5.02) {
            int[] symb = new int[4];

            //zählt die anzahl der verschiedenen symbole
            for (int i : symbole) {
                symb[i]++;
            }

            //bestimmt das symbol, welches den flush bestimmt
            int flush = -1;
            for (int i = 0; i < symb.length; i++) {
                if (symb[i] >= 5) {
                    flush = i;
                    break;
                }
            }
            List<Card> flushCards = new ArrayList<>();
            for (Card c : cards) {
                if (c.getSymbol() == flush)
                    flushCards.add(c);
            }

            //bestimmen der werte der flush karten + sotierung nach aufsteigendem wert
            short[] flushWerte = new short[flushCards.size()];
            for (int i = 0; i < flushCards.size(); i++) {
                flushWerte[i] = flushCards.get(i).getWert();
            }
            Arrays.sort(flushWerte);

            //gleiches wie bei der straße, aber nur mit den flush karten
            int counter = 0;

            for (int i = 0; i < flushCards.size() - 1; i++) {
                int j = flushWerte[i + 1] - flushWerte[i];
                switch (j) {
                    case 1:
                        counter++;
                        result = 8.02 + (flushWerte[i + 1] * 0.01);
                        break;
                    case 0:
                        break;
                    default:
                        counter = 0;
                        break;
                }
                if (counter < 4) {
                    result = 0;
                }
            }

        }
        return result;
    }
    /**
     * @return 9.14, wenn ein royaleFlush vorhanden ist
     */
    private double isRoyaleFlush() {
        double result = 0.00;
        if (isStraightFlush() == 8.13)
            result = 9.14;
        return result;
    }
}
