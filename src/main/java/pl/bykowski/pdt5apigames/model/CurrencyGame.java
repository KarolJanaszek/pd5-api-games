package pl.bykowski.pdt5apigames.model;

import java.util.Random;

public class CurrencyGame {
    private Symbol base;
    private Symbol rates;
    private boolean isEnded;
    private int moves;

    public CurrencyGame() {
        this.base = getRandomSymbol();
        this.rates = getOtherRandomSymbol(this.base);
        this.moves = 0;
        this.isEnded = false;
    }

    //TODO naprawić ze static
//    public void registryNextMove() {
//        this.moves = moves++;
//    }

    public String showInfo() {
        return "Game with base: " + base + " and rates: " + rates + ". Actual moves: " + moves;
    }

    public void wined() {
        this.isEnded = true;
    }

    private Symbol getRandomSymbol() {
        Random r = new Random();
        Symbol[] symbols = Symbol.values();
        return symbols[r.nextInt(symbols.length)];
    }

    private Symbol getOtherRandomSymbol(Symbol otherSymbol) {
        Random r = new Random();
        Symbol[] symbols = Symbol.values();
        Symbol symbol;
        do {
            symbol = symbols[r.nextInt(symbols.length)];
        } while (symbol.equals(otherSymbol));

        return symbol;
    }

    public Symbol getBase() {
        return base;
    }

    public void setBase(Symbol base) {
        this.base = base;
    }

    public Symbol getRates() {
        return rates;
    }

    public void setRates(Symbol rates) {
        this.rates = rates;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
}
