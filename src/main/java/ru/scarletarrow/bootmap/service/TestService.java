package ru.scarletarrow.bootmap.service;

import ru.scarletarrow.bootmap.entity.Test;

public interface TestService {
    Object getAllTests();
    Test getTestById(String id);
    Object saveTest(Test test);
    Test setTest(Test test);
}
