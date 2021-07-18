package ru.scarletarrow.bootmap.controllers;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
//        List<Location> allLocations = locationService.getAllLocations();
//        model.addAttribute("allLocations", allLocations);
//            return "locations";
        return findPaginated(1, model);
    }

    @RequestMapping("locations/addNewLocation")
    public String addNewLocation(Model model){
        Location location = new Location();
        model.addAttribute("location", location);
        return "location-info";
    }

    @PostMapping("locations/saveLocation")
    public String saveLocation(@ModelAttribute("location" ) Location location){
        locationService.saveLocation(location);
        return "redirect:/locations";
    }

    @RequestMapping("locations/UpdateLocation/{id}")
    public String updateLocation(@PathVariable (value = "id") int id, Model model){
        Location location = locationService.getLocationById(id);
        model.addAttribute("location", location);
        return "update-location";

    }

    @GetMapping("locations/DeleteLocation/{id}")
    public String deleteLocation(@PathVariable int id){
        locationService.deleteLocationById(id);
        return "redirect:/locations";
    }


    @GetMapping("locations/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int PageNumber, Model model){
        int pageSize = 5;

        Page<Location> locationPage = locationService.findPaginated(PageNumber, pageSize);
        List<Location> allLocations = locationPage.getContent();

        model.addAttribute("currentPage", PageNumber);
        model.addAttribute("totalPages", locationPage.getSize());
        model.addAttribute("totalLocations", locationPage.getTotalElements());
        model.addAttribute("allLocations", allLocations);
        return "locations";
    }
}
