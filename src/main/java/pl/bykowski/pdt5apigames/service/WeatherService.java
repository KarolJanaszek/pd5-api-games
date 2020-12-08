package pl.bykowski.pdt5apigames.service;

import pl.bykowski.pdt5apigames.model.Location;
import pl.bykowski.pdt5apigames.model.Weather;

import java.util.List;

public interface WeatherService {
    List<Location> findLocationByName(String name);
    Weather findWeatherById(String woeId);
}
