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

    public void setNewHand(Card[] cardsTisch, Card[] cardsHand) {
        if (cardsTisch.length == 5 && cardsHand.length == 2) {

            cards = new ArrayList<>();
            cards.addAll(Arrays.asList(cardsTisch));
            cards.addAll(Arrays.asList(cardsHand));

            werte = new short[cards.size()];
            symbole = new short[cards.size()];

            for (int i = 0; i < cards.size() ; i++) {
                this.werte[i] = cards.get(i).getWert();
                this.symbole[i] = cards.get(i).getSymbol();
            }

            Arrays.sort(this.werte);
            Arrays.sort(this.symbole);

            value = getRanking();

        }else {
            System.err.println("Irgendwas lief falsch beim übergeben von den Karten");
            System.exit(0);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("H{[h: ");
        for (int i = 5; i < 7 ; i++) {
            sb.append(cards.get(i).toString()).append(", ");
        }
        sb.append("], [t: ");
        for (int i = 0; i < 5; i++) {
            sb.append(cards.get(i).toString()).append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }

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
        }   else if (isPair() > 1.0) {
            return isPair();
        } else {
            return cards.get(cards.size() - 1).getWert() * 0.01;
        }
    }

    public double getValue() {
        return value;
    }

    private double isPair() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 1 ; i++) {
            if (werte[i] == werte[i+1])
                result = 1.0 + (werte[i] * 0.01);
        }
        return result;
    }

    private double isTwoPair() {
        int counter = 0;
        double value = 0.00;
        double result = 0.00;
        for (int i = 0; i < cards.size() - 1 ; i++) {
            if (werte[i] == werte[i+1]) {
                value = (werte[i] * 0.01);
                counter++;
            }
        }

        if (counter == 2)
            result = 2.0 + value;

        return result;
    }

    private double isThreeOfAKind() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 2 ; i++) {
            if (werte[i] == werte[i+2])
                result = 3.0 + (werte[i] * 0.01);
        }
        return result;
    }

    private double isStraight() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 6 ; i++) {
            if (werte[i] + 5 == werte[i + 4])
                result = 4.0 + (werte[i+4] * 0.01);
        }
        return result;
    }

    private double isFlush() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 6 ; i++) {
            if (symbole[i] == symbole[i + 4])
                result = 5.0 + (werte[i+4] * 0.01);
        }
        return result;
    }

    private double isFullHouse() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 6 ; i++) {
            if ((werte[i] + 1 == werte[i + 1] && werte[i+2] + 2 == werte[i + 4]) ||
                    (werte[i] + 2 == werte[i+2] && werte[i+3] + 1 == werte[i + 4]))
                result = 6.0 + (werte[i+5] * 0.01);
        }
        return result;
    }

    private double isFourOfAKind() {
        double result = 0.00;
        for (int i = 0; i < cards.size() - 3 ; i++) {
            if (werte[i] == werte[i+3])
                result = 7.0 + (werte[i] * 0.01);
        }
        return result;
    }

    private double isStraightFlush() {
        double result = 0.00;
        if (isStraight() > 4.0 && isFlush() > 5.0) {
            result = 8.0 + isStraight() - 4.0;
        }
        return result;
    }

    private double isRoyaleFlush() {
        double result = 0.00;
        if (isStraightFlush() == 8.13) {
            result = 9.0;
        }
        return result;
    }
}
