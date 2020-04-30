public class Card
{
    private final short wert, symbol;

    private static final String[] symbole = { "Herz", "Pik", "Karo", "Kreuz" };
    private static final String[] werte = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Bube", "Dame", "König", "Ass" };

    Card(short symbol, short wert)
    {
        this.wert = wert;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "C{" +  symbole[symbol] + "(" + werte[wert] + ")}";
    }

    public short getWert() {
        return wert;
    }

    public short getSymbol() {
        return symbol;
    }
}

