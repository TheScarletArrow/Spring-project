package ru.scarletarrow.bootmap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.scarletarrow.bootmap.dao.LocationRepository;
import ru.scarletarrow.bootmap.entity.Location;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    LocationRepository locationRepository;

    @GetMapping("/locations")
    public String getAllLocations(Model model){
        List<Location> allLocations = locationRepository.findAll();
        model.addAttribute("allLocations", allLocations);
            return "locations";
    }
}
