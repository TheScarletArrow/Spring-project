package ru.scarletarrow.bootmap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.scarletarrow.bootmap.dao.LocationRepository;
import ru.scarletarrow.bootmap.entity.Location;

import java.util.List;

@RestController
@RequestMapping("/management/api/locations")
public class ManagementController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ADMIN_TRAINEE')")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('student:write')")
    public void addNewLocation(@RequestBody Location location) {
        locationRepository.save(location);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteLocation(@PathVariable int id) {
        locationRepository.deleteById(id);
    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('student:write')")
    public Location updateLocation(@RequestBody Location location){
        locationRepository.save(location);
        return location;
    }
}
