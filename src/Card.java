public class Card
{
    private final short wert, symbol;

    private static final String[] symbole = { "Herz", "Pik", "Karo", "Kreuz" };
    private static final String[] werte = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Bube", "Dame", "König", "Ass" };

    /**
     * erstellt eine KArte
     * @param symbol wert für symbol (Herz, Pik, Karo, Kreuz)
     * @param wert wert für werte (2,3,4,5,6,7,8,9,10,Bube,Dame,König,Ass)
     */
    Card(short symbol, short wert)
    {
        this.wert = wert;
        this.symbol = symbol;
    }

    /**
     * @return Card als String
     */
    @Override
    public String toString() {
        return "C{" +  symbole[symbol] + "(" + werte[wert] + ")}";
    }

    /**
     * @return wert vom wert
     */
    public short getWert() {
        return wert;
    }

    /**
     * @return wert vom symbol
     */
    public short getSymbol() {
        return symbol;
    }
}

