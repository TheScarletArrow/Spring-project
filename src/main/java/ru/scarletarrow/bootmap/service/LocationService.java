package ru.scarletarrow.bootmap.service;

import org.springframework.data.domain.Page;
import ru.scarletarrow.bootmap.entity.Location;

import java.util.List;

public interface LocationService {
    List<Location> getAllLocations();
    void saveLocation(Location location);
    Location getLocationById(int id);
    void deleteLocationById(int id);
    Page<Location> findPaginated(int pageNumber,int pageSize);
    public List<Location> getByTypeId(int id);


}
