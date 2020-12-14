package pl.bykowski.pdt5apigames.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.bykowski.pdt5apigames.model.*;

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
        return coverSymbolToValueByBaseAndCurrency(currency.getRates(), game);
    }

    private Double coverSymbolToValueByBaseAndCurrency(CurrencyRates rates, CurrencyGame game) {
        switch (game.getRates()) {
            case GBP: return rates.getGBP();
            case HKD: return rates.getHKD();
            case IDR: return rates.getIDR();
            case ILS: return rates.getILS();
            case DKK: return rates.getDKK();
            case INR: return rates.getINR();
            case CHF: return rates.getCHF();
            case MXN: return rates.getMXN();
            case CZK: return rates.getCZK();
            case SGD: return rates.getSGD();
            case THB: return rates.getTHB();
            case HRK: return rates.getHRK();
            case MYR: return rates.getMYR();
            case NOK: return rates.getNOK();
            case CNY: return rates.getCNY();
            case BGN: return rates.getBGN();
            case PHP: return rates.getPHP();
            case SEK: return rates.getSEK();
            case PLN: return rates.getPLN();
            case ZAR: return rates.getZAR();
            case CAD: return rates.getCAD();
            case ISK: return rates.getISK();
            case BRL: return rates.getBRL();
            case RON: return rates.getRON();
            case NZD: return rates.getNZD();
            case TRY: return rates.getTRY();
            case JPY: return rates.getJPY();
            case RUB: return rates.getRUB();
            case KRW: return rates.getKRW();
            case USD: return rates.getUSD();
            case HUF: return rates.getHUF();
            case AUD: return rates.getAUD();
            default: throw new IllegalArgumentException();
        }
    }
}
