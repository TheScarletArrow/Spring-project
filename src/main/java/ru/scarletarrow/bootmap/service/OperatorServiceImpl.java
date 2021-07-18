package ru.scarletarrow.bootmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.scarletarrow.bootmap.dao.OperatorRepository;
import ru.scarletarrow.bootmap.entity.Operator;

import java.util.List;

public class OperatorServiceImpl implements OperatorService{
    @Autowired
    OperatorRepository operatorRepository;
    @Override
    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();

    }
}
