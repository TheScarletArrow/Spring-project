package ru.scarletarrow.bootmap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.scarletarrow.bootmap.dao.LocationRepository;
import ru.scarletarrow.bootmap.dao.OperatorRepository;
import ru.scarletarrow.bootmap.entity.Location;
import ru.scarletarrow.bootmap.entity.Operator;

import java.util.List;

@RestController
@RequestMapping("/api/v1/api/")
public class APIController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @GetMapping("/locations")
    private List<Location> showAllLocations() {
        return locationRepository.findAll();
    }

    @PostMapping("/locations")
    @ResponseBody
    private Location saveLocation(@RequestBody Location location){
        locationRepository.save(location);
        return location;
    }


    @GetMapping("/operators")
    private List<Operator> showAllOperators() {
        return operatorRepository.findAll();
    }

//    @GetMapping("/locations/type-id/{id}")
//    private List<Location> showLocationsWithLocTypeId(@PathVariable int id) {
//        return locationRepository.findByTypeidEqualsAndTypeidNotNull(id);
//    }

}
