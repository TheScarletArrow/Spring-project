package ru.scarletarrow.bootmap.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.scarletarrow.bootmap.dao.LocationRepository;
import ru.scarletarrow.bootmap.entity.Location;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Slf4j
@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        log.info("Getting all Locations...");
        List<Location> all = locationRepository.findAll();
        if (all.size()==0) log.info("No locations found");
        else log.info(String.format("Found %d locations", all.size()));
        return all;

    }

    @Override
    public void saveLocation(Location location) {
        this.locationRepository.save(location);
        log.info("Saving location "+ location.toString());

    }

    @Override
    public Location getLocationById(int id) {
        log.info("Getting location with id "+ id);
        Optional<Location> optionalLocation = locationRepository.findById(id);
        Location location;
        if (optionalLocation.isPresent()) {location=optionalLocation.get(); log.info("Got location "+ location);}
        else{ log.info("No such location"); throw new IllegalArgumentException("No such location");}
        return location;
    }

    @Override
    public void deleteLocationById(int id) {
        log.info("Deleting location with id "+ id);
        locationRepository.deleteById(id);
        log.info("Location with id " + id + " was deleted");
    }

    @Override
    public Page<Location> findPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);

        return locationRepository.findAll(pageable);
    }


    @Override
    public List<Location> getByTypeId(int type_id) {
        return locationRepository.findByTypeidEqualsAndTypeidNotNull(type_id);
    }
}
