package ru.scarletarrow.bootmap.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.scarletarrow.bootmap.entity.Location;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    public List<Location> findByTypeidEqualsAndTypeidNotNull(int id);

}
