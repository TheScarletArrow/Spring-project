package ru.scarletarrow.bootmap.controllers;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.scarletarrow.bootmap.dao.LocationRepository;
import ru.scarletarrow.bootmap.entity.Location;
import ru.scarletarrow.bootmap.service.LocationService;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    LocationService locationService;

    @GetMapping("/locations")
    public String getAllLocations(Model model){
        List<Location> allLocations = locationService.getAllLocations();
        model.addAttribute("allLocations", allLocations);
            return "locations";
    }

    @RequestMapping("/addNewLocation")
    public String addNewLocation(Model model){
        Location location = new Location();
        model.addAttribute("location", location);
        return "location-info";
    }

    @PostMapping("/saveLocation")
    public String saveLocation(@ModelAttribute("location" ) Location location){
        locationService.saveLocation(location);
        return "redirect:/locations/";
    }

    @RequestMapping("/UpdateLocation/{id}")
    public String updateLocation(@PathVariable (value = "id") int id, Model model){
        Location location = locationService.getLocationById(id);
        model.addAttribute("location", location);
        return "update-location";

    }

    @GetMapping("/DeleteLocation/{id}")
    public String deleteLocation(@PathVariable int id){
        locationService.deleteLocationById(id);
        return "redirect:/locations/";
    }
}
