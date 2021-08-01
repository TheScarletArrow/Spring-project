package ru.scarletarrow.bootmap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.scarletarrow.bootmap.dao.OperatorRepository;
import ru.scarletarrow.bootmap.entity.MESSAGE_TYPE;
import ru.scarletarrow.bootmap.entity.Message;
import ru.scarletarrow.bootmap.entity.Operator;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
public class ManagementControllerOperators {

    @Autowired
    OperatorRepository operatorRepository;

    @GetMapping("/")
    public List<Operator> findAllOperators(){
        return  operatorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Object getOperatorById(@PathVariable int id){
        var findById = operatorRepository.findById(id);
        if (findById.isPresent()) return findById.get();
        else return new Message(MESSAGE_TYPE.ERROR,"No operator with id "+ id);
    }
    @PostMapping("/")
    public Object addOperator(@RequestBody Operator operator){
        return operatorRepository.save(operator);
    }
}
