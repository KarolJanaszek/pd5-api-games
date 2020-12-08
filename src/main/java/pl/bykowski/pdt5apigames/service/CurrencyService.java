package pl.bykowski.pdt5apigames.service;

import pl.bykowski.pdt5apigames.model.Currency;
import pl.bykowski.pdt5apigames.model.CurrencyGame;
import pl.bykowski.pdt5apigames.model.Symbol;

public interface CurrencyService {
    Currency getExchangeRates(Symbol symbol);
    Double getWinValueForGame(CurrencyGame game);
}
