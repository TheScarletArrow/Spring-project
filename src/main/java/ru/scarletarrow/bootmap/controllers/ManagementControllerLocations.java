package ru.scarletarrow.bootmap.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.scarletarrow.bootmap.dao.LocationRepository;
import ru.scarletarrow.bootmap.entity.Location;
import ru.scarletarrow.bootmap.entity.Message;
import ru.scarletarrow.bootmap.service.LocationService;
import ru.scarletarrow.bootmap.service.LocationServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class ManagementControllerLocations {

    @Autowired
    private LocationRepository locationRepository;


    @GetMapping("/")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ADMIN_TRAINEE')")
    public List<Location> getAllLocations() {
        List<Location> all = locationRepository.findAll();
        return all;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('student:write')")
    public Object addNewLocation(@RequestBody Location location) {
        if (location.getLatitude() == 0 || location.getLongitude() == 0 || location.getId() == 0)
            return new Message("Data provided ia incorrect");
        locationRepository.save(location);
        return location;
    }

    @GetMapping("/{id}")
    public Object getLocation(@PathVariable int id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) return location;
        else return new Message(String.format("Location with id %d not found", id));
    }

    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('student:write')")
    @ResponseBody
    public Object deleteLocation(@PathVariable int id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            locationRepository.deleteById(id);

            return new Message(String.format("Location with id %d was deleted", id));
        } else return new Message("No such location found");

    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('student:write')")
    public Object updateLocation(@RequestBody Location location) {
        try {
            if (locationRepository.findById(location.getId()).isPresent()) {
                locationRepository.save(location);
                return location;
            } else return new Message("No such location found");
        } catch (EntityNotFoundException e) {
            return new Message("No such location found");
        }
    }

    @GetMapping("/typeid/{id}")
    public Object getByTypeId(@PathVariable int id)
    {
        List<Location> byTypeidEqualsAndTypeidNotNull = locationRepository.findByTypeidEqualsAndTypeidNotNull(id);
        if (byTypeidEqualsAndTypeidNotNull.size()>0)
        return byTypeidEqualsAndTypeidNotNull;
        else return new Message("No such locations with selected typeid");
    }

}
