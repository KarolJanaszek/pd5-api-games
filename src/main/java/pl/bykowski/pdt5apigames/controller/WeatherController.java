package pl.bykowski.pdt5apigames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bykowski.pdt5apigames.model.Location;
import pl.bykowski.pdt5apigames.model.Weather;
import pl.bykowski.pdt5apigames.model.WeatherConsolidated;
import pl.bykowski.pdt5apigames.service.WeatherService;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public String getWeather(Model model,
                             @RequestParam(required = false, defaultValue = "") String q,
                             @RequestParam(required = false, defaultValue = "") String id) {
        LocalDate now = LocalDate.now();

        if (q.isEmpty()) {
            model.addAttribute("loc", new ArrayList<Location>());
        } else {
            model.addAttribute("loc", weatherService.findLocationByName(q));
            model.addAttribute("q", q);
        }

        if (id.isEmpty()) {
            model.addAttribute("details", new ArrayList<WeatherConsolidated>());
            model.addAttribute("locate", "");
        } else {
            Weather weather = weatherService.findWeatherById(id);
            WeatherConsolidated consolidated = weather.getConsolidatedWeather().stream().findFirst().get();
            model.addAttribute("details", consolidated);
            model.addAttribute("locate", weather.getTitle());

            String imgUrl = "https://www.metaweather.com/static/img/weather/" + consolidated.getWeatherStateAbbr() + ".svg";
            model.addAttribute("imgUrl", imgUrl);
        }
        return "home";
    }
}
