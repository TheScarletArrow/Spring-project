package ru.scarletarrow.bootmap.service;

import ru.scarletarrow.bootmap.entity.Location;

import java.util.List;

public interface LocationService {
    List<Location> getAllLocations();
    void saveLocation(Location location);
    Location getLocationById(int id);
    void deleteLocationById(int id);

}
