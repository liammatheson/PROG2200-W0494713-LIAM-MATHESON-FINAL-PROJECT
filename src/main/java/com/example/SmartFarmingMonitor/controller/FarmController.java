package com.example.SmartFarmingMonitor.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SmartFarmingMonitor.model.FertilizationEvent;
import com.example.SmartFarmingMonitor.model.IrrigationEvent;
import com.example.SmartFarmingMonitor.model.MoistureSensor;
import com.example.SmartFarmingMonitor.model.NutrientSensor;
import com.example.SmartFarmingMonitor.model.TemperatureSensor;
import com.example.SmartFarmingMonitor.repo.FertilizationEventRepo;
import com.example.SmartFarmingMonitor.repo.IrrigationEventRepo;
import com.example.SmartFarmingMonitor.repo.MoistureSensorRepo;
import com.example.SmartFarmingMonitor.repo.NutrientSensorRepo;
import com.example.SmartFarmingMonitor.repo.TemperatureSensorRepo;
import com.example.SmartFarmingMonitor.service.AlertService;
import com.example.SmartFarmingMonitor.service.WeatherService;

import tools.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/dashboard")
public class FarmController {

    @Autowired
    private FertilizationEventRepo fertRepo;
    @Autowired
    private IrrigationEventRepo irrigateRepo;
    @Autowired
    private MoistureSensorRepo moistureRepo;
    @Autowired
    private NutrientSensorRepo nutrientRepo;
    @Autowired
    private TemperatureSensorRepo tempRepo;
    @Autowired
    private AlertService alertService;

    private final WeatherService weatherService;

    public FarmController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public String dashboard(Model model) {

        moistureRepo.findTopByOrderByTimeDesc().ifPresent(m -> model.addAttribute("moisture", m.getValue()));
        nutrientRepo.findTopByOrderByTimeDesc().ifPresent(m -> model.addAttribute("nutrients", m.getValue()));
        tempRepo.findTopByOrderByTimeDesc().ifPresent(m -> model.addAttribute("temperature", m.getValue()));

        // Latest 5 fertilization events
        model.addAttribute("fertilizations", fertRepo.findTop5ByOrderByTimeDesc());
        // Latest 5 irrigation events
        model.addAttribute("irrigations", irrigateRepo.findTop5ByOrderByTimeDesc());

        model.addAttribute("alerts", alertService.getAlerts());

        List<MoistureSensor> last10 = moistureRepo.findTop10ByOrderByTimeDesc();
        List<TemperatureSensor> last10Temps = tempRepo.findTop10ByOrderByTimeDesc();
        List<NutrientSensor> last10Nutrients = nutrientRepo.findTop10ByOrderByTimeDesc();

        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("moistureTimesJson",
        mapper.writeValueAsString(last10.stream().map(MoistureSensor::getTime).map(Object::toString).toList()));
        model.addAttribute("moistureValuesJson",
        mapper.writeValueAsString(last10.stream().map(MoistureSensor::getValue).toList()));

        model.addAttribute("temperatureTimesJson",
        mapper.writeValueAsString(last10Temps.stream().map(TemperatureSensor::getTime).map(Object::toString).toList()));
        model.addAttribute("temperatureValuesJson",
        mapper.writeValueAsString(last10Temps.stream().map(TemperatureSensor::getValue).toList()));

        model.addAttribute("nutrientTimesJson",
        mapper.writeValueAsString(last10Nutrients.stream().map(NutrientSensor::getTime).map(Object::toString).toList()));
        model.addAttribute("nutrientValuesJson",
        mapper.writeValueAsString(last10Nutrients.stream().map(NutrientSensor::getValue).toList()));



        // weather
        Map<String, Object> weatherData = weatherService.getCurrentWeather();
        model.addAttribute("weather", weatherData.get("current_weather"));

        return "dashboard2";
    }

    @PostMapping("/fertilize/add")
    public String addFertilization(@ModelAttribute FertilizationEvent event) {
        event.setTime(LocalDateTime.now());
        fertRepo.save(event);
        return "redirect:/dashboard";
    }

    @PostMapping("/irrigation/add")
    public String addIrrigation(@ModelAttribute IrrigationEvent event) {
        event.setTime(LocalDateTime.now());
        irrigateRepo.save(event);
        return "redirect:/dashboard";
    }

}
