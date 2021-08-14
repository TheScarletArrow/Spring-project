package ru.scarletarrow.bootmap.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scarletarrow.bootmap.dao.TestRepository;
import ru.scarletarrow.bootmap.entity.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TestServiceImpl implements TestService{

    @Autowired
    TestRepository testRepository;

    @Override
    public Object getAllTests() {
        log.info("Getting all Tests...");
        List<Test> all = testRepository.findAll();
        if (all.size()==0) log.info("No tests found");
        else log.info(String.format("Found %d tests", all.size()));
        return all;
    }

    @Override
    public Test getTestById(String id) {
        log.info("Getting test with id "+id);
        Optional<Test> optionalTest = testRepository.findById(id);
        Test test;
        if (optionalTest.isPresent()){ test = optionalTest.get(); log.info("Got test " + test);}
        else {log.info("No such test"); throw new IllegalArgumentException("No such test");}
        return test;

    }

    @Override
    public Test setTest(Test test) {
        test.setTestId(UUID.randomUUID().toString());
        test.setDate(new Date(System.currentTimeMillis()).toString());
        return test;
    }

    @Override
    public Test saveTest(Test test) {

        log.info("Saving test + " +  test);
        return testRepository.save(test);
    }
}
