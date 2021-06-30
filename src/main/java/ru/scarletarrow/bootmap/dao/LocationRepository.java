package ru.scarletarrow.bootmap.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.scarletarrow.bootmap.entity.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    public List<Location> findByTypeidEqualsAndTypeidNotNull(int id);
}
