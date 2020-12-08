package pl.bykowski.pdt5apigames.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.bykowski.pdt5apigames.model.Location;
import pl.bykowski.pdt5apigames.model.Weather;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class WeatherServiceImpl implements WeatherService {


    @Override
    public List<Location> findLocationByName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        Location[] locations = restTemplate.getForObject("https://www.metaweather.com/api/location/search/?query=" + name, Location[].class);
        return asList(locations);
    }

    @Override
    public Weather findWeatherById(String woeId) {
        RestTemplate restTemplate = new RestTemplate();
        Weather weather = restTemplate.getForObject("https://www.metaweather.com/api/location/" + woeId, Weather.class);
        return weather;
    }
}
