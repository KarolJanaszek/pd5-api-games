package pl.bykowski.pdt5apigames.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.bykowski.pdt5apigames.model.Currency;
import pl.bykowski.pdt5apigames.model.CurrencyGame;
import pl.bykowski.pdt5apigames.model.Symbol;
import pl.bykowski.pdt5apigames.model.Weather;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Override
    public Currency getExchangeRates(Symbol symbol) {
        RestTemplate restTemplate = new RestTemplate();
        Currency currency = restTemplate.getForObject("https://api.ratesapi.io/api/latest?base=" + symbol, Currency.class);
        return currency;
    }

    @Override
    public Double getWinValueForGame(CurrencyGame game) {
        Currency currency = getExchangeRates(game.getBase());
        return coverSymbolToValueByBaseAndCurrency(currency, game);
    }

    private Double coverSymbolToValueByBaseAndCurrency(Currency currency, CurrencyGame game) {
        switch (game.getRates()) {
            case GBP: return currency.getRates().getGBP();
            case HKD: return currency.getRates().getHKD();
            case IDR: return currency.getRates().getIDR();
            case ILS: return currency.getRates().getILS();
            case DKK: return currency.getRates().getDKK();
            case INR: return currency.getRates().getINR();
            case CHF: return currency.getRates().getCHF();
            case MXN: return currency.getRates().getMXN();
            case CZK: return currency.getRates().getCZK();
            case SGD: return currency.getRates().getSGD();
            case THB: return currency.getRates().getTHB();
            case HRK: return currency.getRates().getHRK();
            case MYR: return currency.getRates().getMYR();
            case NOK: return currency.getRates().getNOK();
            case CNY: return currency.getRates().getCNY();
            case BGN: return currency.getRates().getBGN();
            case PHP: return currency.getRates().getPHP();
            case SEK: return currency.getRates().getSEK();
            case PLN: return currency.getRates().getPLN();
            case ZAR: return currency.getRates().getZAR();
            case CAD: return currency.getRates().getCAD();
            case ISK: return currency.getRates().getISK();
            case BRL: return currency.getRates().getBRL();
            case RON: return currency.getRates().getRON();
            case NZD: return currency.getRates().getNZD();
            case TRY: return currency.getRates().getTRY();
            case JPY: return currency.getRates().getJPY();
            case RUB: return currency.getRates().getRUB();
            case KRW: return currency.getRates().getKRW();
            case USD: return currency.getRates().getUSD();
            case HUF: return currency.getRates().getHUF();
            case AUD: return currency.getRates().getAUD();
            default: return null;
        }
    }
}
