package ru.scarletarrow.bootmap.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import ru.scarletarrow.bootmap.entity.Operator;

import java.util.List;

public interface OperatorRepository extends JpaRepository<Operator, Integer> {
    public List<Operator> getOperatorById(int id);
}
