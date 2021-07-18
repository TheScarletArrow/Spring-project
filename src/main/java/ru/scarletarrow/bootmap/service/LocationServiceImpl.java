package ru.scarletarrow.bootmap.service;

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

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public void saveLocation(Location location) {
        this.locationRepository.save(location);
    }

    @Override
    public Location getLocationById(int id) {
        Optional<Location> optionalLocation = locationRepository.findById(id);
        Location location;
        if (optionalLocation.isPresent()) location=optionalLocation.get();
        else throw new RuntimeException("No such location");
        return location;
    }

    @Override
    public void deleteLocationById(int id) {
        locationRepository.deleteById(id);
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
